package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.exception.ClienteException;
import eterea.core.service.kotlin.exception.FeriadoException;
import eterea.core.service.kotlin.exception.ProductoSkuException;
import eterea.core.service.kotlin.exception.VoucherException;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.extern.Product;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.ProgramaDiaDto;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.OrderNoteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class VouchersService {

    private final EmpresaService empresaService;
    private final NegocioService negocioService;
    private final OrderNoteService orderNoteService;
    private final VoucherService voucherService;
    private final ClienteService clienteService;
    private final FeriadoService feriadoService;
    private final ProductoSkuService productoSkuService;
    private final ReservaContextService reservaContextService;
    private final VoucherProductoService voucherProductoService;
    private final ReservaService reservaService;

    private record PersonType(int cantidad, String descripcion) {

    }

    public VouchersService(EmpresaService empresaService, NegocioService negocioService, OrderNoteService orderNoteService, VoucherService voucherService, ClienteService clienteService, FeriadoService feriadoService, ProductoSkuService productoSkuService, ReservaContextService reservaContextService, VoucherProductoService voucherProductoService, ReservaService reservaService) {
        this.empresaService = empresaService;
        this.negocioService = negocioService;
        this.orderNoteService = orderNoteService;
        this.voucherService = voucherService;
        this.clienteService = clienteService;
        this.feriadoService = feriadoService;
        this.productoSkuService = productoSkuService;
        this.reservaContextService = reservaContextService;
        this.voucherProductoService = voucherProductoService;
        this.reservaService = reservaService;
    }

    @Transactional
    public ProgramaDiaDto importOneFromWeb(Long orderNumberId) {
        Empresa empresa = empresaService.findTop();
        Negocio negocio = negocioService.findByNegocioId(empresa.getNegocioId());
        OrderNote orderNote = orderNoteService.findByOrderNumberId(orderNumberId);
        if (!orderNote.getOrderStatus().equals("Completado")) {
            return new ProgramaDiaDto.Builder()
                    .errorMessage("Error: Order Note pendiente de PAGO")
                    .build();
        }
        try {
            log.debug("order_note={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(orderNote));
        } catch (JsonProcessingException e) {
            log.debug("order_note=NOT_FOUND");
        }
        // Verifica si ya hay un programa por el día registrado para esta orderNote
        try {
            voucherService.findByNumeroVoucher(String.valueOf(orderNumberId));
            return new ProgramaDiaDto.Builder()
                    .errorMessage("Error: Programa por el Día YA registrado")
                    .build();
        } catch (VoucherException e) {
            log.debug("looks good");
        }
        // Verifica por las dudas que no tenga productos
        if (orderNote.getProducts().isEmpty()) {
            return new ProgramaDiaDto.Builder()
                    .errorMessage("Error: reserva sin productos")
                    .build();
        }
        // Verifica (por ahora) que sea un producto sin adicionales
        if (orderNote.getProducts().size() > 1) {
            return new ProgramaDiaDto.Builder()
                    .errorMessage("Error: más de un producto en la reserva")
                    .build();
        }
        Product product = orderNote.getProducts().getFirst();
        // Factura parque termal
        if (product.getSku().equals("parque_termal")) {
            return facturaUnProducto(orderNote, product, negocio);
        }
        // Factura tarde terma spa
        if (product.getSku().equals("tarde_termaspa")) {
            return facturaUnProducto(orderNote, product, negocio);
        }
        // Factura terma spa full day
        if (product.getSku().equals("termaspa_fullday") && product.getServiciosAdicionales().isEmpty()) {
            return facturaUnProducto(orderNote, product, negocio);
        }
        // Si no puede facturar el sku
        return new ProgramaDiaDto.Builder()
                .errorMessage("Error: no corresponde a un producto facturable")
                .build();
    }

    @Transactional
    public Voucher registrarVoucher(Voucher voucher, List<VoucherProducto> voucherProductos) {
        voucher = voucherService.save(voucher);
        voucherProductos = saveVoucherProductos(voucher.getVoucherId(), voucherProductos);
        voucher.setProductos(cadenaResumen(voucherProductos));
        voucher = voucherService.save(voucher);

        if (voucher.getReservaId() == null) {
            Reserva reserva = generarReserva(voucher, voucherProductos);
            voucher.setReservaId(reserva.getReservaId());
        }

        ReservaContext reservaContext = new ReservaContext.Builder()
                .reservaId(voucher.getReservaId())
                .voucherId(voucher.getVoucherId())
                .orderNumberId(Long.valueOf(voucher.getNumeroVoucher()))
                .facturaPendiente((byte) 1)
                .envioPendiente((byte) 1)
                .build();
        reservaContextService.add(reservaContext);

        return voucher;
    }

    @Transactional
    public List<VoucherProducto> saveVoucherProductos(Long voucherId, List<VoucherProducto> voucherProductos) {
        voucherProductoService.deleteAllByVoucherId(voucherId);
        for (var voucherProducto : voucherProductos) {
            voucherProducto.setVoucherId(voucherId);
        }
        return voucherProductoService.saveAll(voucherProductos);
    }

    private List<PersonType> extractPaxs(String personTypes) {
        var types = new ArrayList<PersonType>();
        Pattern pattern = Pattern.compile("\\((\\d+)\\)\\s+([^()]+)");
        Matcher matcher = pattern.matcher(personTypes);

        while (matcher.find()) {
            types.add(new PersonType(Integer.parseInt(matcher.group(1)), matcher.group(2).trim()));
        }
        return types;
    }

    private String cadenaResumen(List<VoucherProducto> voucherProductos) {
        StringBuilder cadena = new StringBuilder();
        boolean primero = true;
        for (var voucherProducto : voucherProductos) {
            if (!primero) {
                cadena.append(" + ");
            }
            cadena.append(voucherProducto.getProducto().getNombre()).append(" x ").append(voucherProducto.getCantidadPaxs());

            primero = false;
        }
        return cadena.toString();
    }

    @Transactional
    public Reserva generarReserva(Voucher voucher, List<VoucherProducto> voucherProductos) {
        Reserva reserva = reservaService.copyFromVoucher(voucher);
        reserva.setNegocioId(empresaService.findTop().getNegocioId());
        reserva.setUsuario("admin");
        reserva = reservaService.add(reserva);

        voucher.setReservaId(reserva.getReservaId());
        voucher = voucherService.update(voucher, voucher.getVoucherId());

        reservaService.generarReservaArticulo(reserva, voucherProductos);

        return reserva;
    }

    @Transactional
    public ProgramaDiaDto facturaUnProducto(OrderNote orderNote, Product product, Negocio negocio) {
        String fullName = orderNote.getBillingLastName().toUpperCase() + ", " + orderNote.getBillingFirstName().toUpperCase();
        var cliente = determinaCliente(orderNote, fullName, negocio);
        OffsetDateTime fechaServicio = product.getBookingStart();
        byte lunes = 0;
        byte martes = 0;
        byte miercoles = 0;
        byte jueves = 0;
        byte viernes = 0;
        byte sabado = 0;
        byte domingo = 0;
        byte feriado = 0;
        try {
            feriadoService.findByFecha(fechaServicio);
            feriado = 1;
        } catch (FeriadoException e) {
            switch (fechaServicio.getDayOfWeek().getValue()) {
                case 1:    // 1 Lunes
                    lunes = 1;
                    break;
                case 2:
                    martes = 1;
                    break;
                case 3:
                    miercoles = 1;
                    break;
                case 4:
                    jueves = 1;
                    break;
                case 5:
                    viernes = 1;
                    break;
                case 6:
                    sabado = 1;
                    break;
                case 7:
                    domingo = 1;
                    break;
            }
        }
        // Determina productos
        Producto productoPaxMayor = null;
        Producto productoPaxMenor = null;
        try {
            ProductoSku productoSku = productoSkuService.findBySku(product.getSku(), lunes, martes, miercoles, jueves, viernes, sabado, domingo, feriado);
            try {
                log.debug("producto_sku={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(productoSku));
            } catch (JsonProcessingException e) {
                log.debug("producto_sku=NOT_FOUND");
            }
            productoPaxMayor = productoSku.getProductoPaxMayor();
            productoPaxMenor = productoSku.getProductoPaxMenor();
        } catch (ProductoSkuException e) {
            return new ProgramaDiaDto.Builder()
                    .errorMessage("Error: SKU sin asociación de Productos")
                    .build();
        }
        // Determina paxs
        int paxsMenor = 0;
        int paxsMayor = 0;
        for (PersonType personType : extractPaxs(product.getPersonTypes())) {
            log.debug("personType={}", personType);
            if (personType.descripcion().contains("Niño")) {
                paxsMenor = personType.cantidad();
            }
            if (personType.descripcion().contains("Adulto")) {
                paxsMayor = personType.cantidad();
            }
        }
        // Determina productos
        var voucherProductos = determinaProductos(paxsMayor, paxsMenor, productoPaxMayor, productoPaxMenor);
        //
        Voucher voucher = new Voucher.Builder()
                .fechaToma(orderNote.getCompletedDate())
                .fechaServicio(fechaServicio)
                .fechaVencimiento(product.getBookingStart())
                .nombrePax(fullName)
                .contacto(orderNote.getBillingPhone())
                .paxs(paxsMayor + paxsMenor)
                .productos(cadenaResumen(voucherProductos))
                .tieneVoucher((byte) 1)
                .clienteId(cliente.getClienteId())
                .observaciones("backend")
                .confirmado((byte) 1)
                .pagaCacheuta((byte) 0)
                .hotelId(475)
                .paxsReales(paxsMayor + paxsMenor)
                .proveedorId(130)
                .numeroVoucher(String.valueOf(orderNote.getOrderNumberId()))
                .usuario("admin")
                .reservaOrigenId(3)
                .ventaInternet((byte) 1)
                .cliente(cliente)
                .build();

        voucher = registrarVoucher(voucher, voucherProductos);

        return new ProgramaDiaDto.Builder()
                .vouchers(Collections.singletonList(voucher))
                .errorMessage("")
                .build();

    }

    private List<VoucherProducto> determinaProductos(int paxsMayor, int paxsMenor, Producto productoPaxMayor, Producto productoPaxMenor) {
        var voucherProductos = new ArrayList<VoucherProducto>();
        if (paxsMayor > 0) {
            voucherProductos.add(new VoucherProducto.Builder()
                    .productoId(productoPaxMayor.getProductoId())
                    .cantidadPaxs(paxsMayor)
                    .producto(productoPaxMayor)
                    .build());
        }
        if (paxsMenor > 0) {
            voucherProductos.add(new VoucherProducto.Builder()
                    .productoId(productoPaxMenor.getProductoId())
                    .cantidadPaxs(paxsMenor)
                    .producto(productoPaxMenor)
                    .build());
        }
        return voucherProductos;
    }

    @Transactional
    public Cliente determinaCliente(OrderNote orderNote, String fullName, Negocio negocio) {
        // Verifica cliente
        try {
            return clienteService.findByNumeroDocumento(orderNote.getBillingDniPasaporte());
        } catch (ClienteException e) {
            Long clienteId = 1 + clienteService.findLast().getClienteId();
            return clienteService.add(new Cliente.Builder()
                    .clienteId(clienteId)
                    .nombre(fullName)
                    .negocioId(negocio.getNegocioId())
                    .razonSocial(fullName)
                    .nombreFantasia(fullName)
                    .domicilio(orderNote.getBillingAddress())
                    .telefono(orderNote.getBillingPhone())
                    .email(orderNote.getBillingEmail())
                    .numeroMovil(orderNote.getBillingPhone())
                    .posicionIva(2)
                    .numeroDocumento(orderNote.getBillingDniPasaporte())
                    .nacionalidad(orderNote.getBillingCountry())
                    .clienteCategoriaId(0)
                    .build());
        }
    }

}
