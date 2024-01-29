/**
 *
 */
package eterea.core.api.rest.service.facade;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.api.rest.exception.ClienteException;
import eterea.core.api.rest.exception.ProgramaDiaException;
import eterea.core.api.rest.kotlin.exception.FeriadoException;
import eterea.core.api.rest.kotlin.exception.ProductoSkuException;
import eterea.core.api.rest.kotlin.exception.VoucherException;
import eterea.core.api.rest.kotlin.extern.OrderNote;
import eterea.core.api.rest.kotlin.extern.Product;
import eterea.core.api.rest.kotlin.model.*;
import eterea.core.api.rest.kotlin.model.dto.ProgramaDiaDTO;
import eterea.core.api.rest.model.Producto;
import eterea.core.api.rest.service.*;
import eterea.core.api.rest.service.extern.OrderNoteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 */
@Service
@Slf4j
public class ProgramaDiaService {

    private final VoucherService voucherService;

    private final ReservaOrigenService reservaOrigenService;

    private final ClienteMovimientoService clienteMovimientoService;

    private final OrderNoteService orderNoteService;

    private final ClienteService clienteService;

    private final EmpresaService empresaService;

    private final NegocioService negocioService;

    private final FeriadoService feriadoService;

    private final ProductoSkuService productoSkuService;

    private final VoucherProductoService voucherProductoService;

    private final ReservaService reservaService;

    private final MakeFacturaService makeFacturaService;

    private final ReservaContextService reservaContextService;

    private record PersonType(int cantidad, String descripcion) {

    }

    @Autowired
    public ProgramaDiaService(VoucherService voucherService, ReservaOrigenService reservaOrigenService, ClienteMovimientoService clienteMovimientoService, OrderNoteService orderNoteService, ClienteService clienteService, EmpresaService empresaService, NegocioService negocioService, FeriadoService feriadoService, ProductoSkuService productoSkuService, VoucherProductoService voucherProductoService, ReservaService reservaService, MakeFacturaService makeFacturaService, ReservaContextService reservaContextService) {
        this.voucherService = voucherService;
        this.reservaOrigenService = reservaOrigenService;
        this.clienteMovimientoService = clienteMovimientoService;
        this.orderNoteService = orderNoteService;
        this.clienteService = clienteService;
        this.empresaService = empresaService;
        this.negocioService = negocioService;
        this.feriadoService = feriadoService;
        this.productoSkuService = productoSkuService;
        this.voucherProductoService = voucherProductoService;
        this.reservaService = reservaService;
        this.makeFacturaService = makeFacturaService;
        this.reservaContextService = reservaContextService;
    }

    public ProgramaDiaDTO findAllByFechaServicio(OffsetDateTime fechaServicio, Boolean soloConfirmados,
                                                 Boolean porNombrePax) {
        List<Voucher> vouchers = voucherService.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax);
        List<Long> reservaIds = vouchers.stream().map(Voucher::getReservaId)
                .filter(reservaId -> reservaId > 0).collect(Collectors.toList());
        List<ClienteMovimiento> clienteMovimientos = clienteMovimientoService.findAllByReservaIds(reservaIds);
        return new ProgramaDiaDTO.Builder()
                .vouchers(vouchers)
                .reservaOrigens(reservaOrigenService.findAll())
                .clienteMovimientos(clienteMovimientos)
                .build();
    }

    public ProgramaDiaDTO findByVoucherId(Long voucherId) {
        Voucher voucher = null;
        try {
            voucher = voucherService.findByVoucherId(voucherId);
        } catch (VoucherException e) {
            throw new ProgramaDiaException(voucherId);
        }
        List<Voucher> vouchers = new ArrayList<>();
        vouchers.add(voucher);
        ReservaOrigen reservaOrigen = reservaOrigenService.findByReservaOrigenId(voucher.getReservaOrigenId());
        List<ReservaOrigen> reservaOrigens = new ArrayList<>();
        reservaOrigens.add(reservaOrigen);
        List<ClienteMovimiento> clienteMovimientos = clienteMovimientoService
                .findAllByReservaId(voucher.getReservaId());
        return new ProgramaDiaDTO.Builder()
                .vouchers(vouchers)
                .reservaOrigens(reservaOrigens)
                .clienteMovimientos(clienteMovimientos)
                .build();
    }

    public void importManyCompletedFromWeb() {
        // Si el negocio no es agencia no hago nada
        if (empresaService.findTop().getNegocioId() != 54) {
            return;
        }
        for (OrderNote orderNote : orderNoteService.findAllCompletedByLastTwoDays()) {
            log.debug("importing order_note={}", orderNote.getOrderNumberId());
            ProgramaDiaDTO programaDiaDTO = importOneFromWeb(orderNote.getOrderNumberId());
            try {
                log.info("imported result={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(programaDiaDTO));
            } catch (JsonProcessingException e) {
                log.debug("something went wrong with order_note={}", orderNote.getOrderNumberId());
            }
            if (programaDiaDTO.getVouchers() != null) {
                Voucher voucher = programaDiaDTO.getVouchers().getFirst();
                boolean isFacturado = makeFacturaService.facturaReserva(voucher.getReservaId(), 853);
                if (!isFacturado) {
                    log.debug("error facturando reserva={}", voucher.getReservaId());
                }
            }
        }
    }

    @Transactional
    public ProgramaDiaDTO importOneFromWeb(Long orderNumberId) {
        Empresa empresa = empresaService.findTop();
        Negocio negocio = negocioService.findByNegocioId(empresa.getNegocioId());
        OrderNote orderNote = orderNoteService.findByOrderNumberId(orderNumberId);
        if (!orderNote.getOrderStatus().equals("Completado")) {
            return new ProgramaDiaDTO.Builder()
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
            return new ProgramaDiaDTO.Builder()
                    .errorMessage("Error: Programa por el Día YA registrado")
                    .build();
        } catch (VoucherException e) {
            log.debug("looks good");
        }
        // Verifica por las dudas que no tenga productos
        if (orderNote.getProducts().isEmpty()) {
            return new ProgramaDiaDTO.Builder()
                    .errorMessage("Error: reserva sin productos")
                    .build();
        }
        // Verifica  (por ahora) que sea sólo un sku=parque_termal
        if (orderNote.getProducts().size() > 1) {
            return new ProgramaDiaDTO.Builder()
                    .errorMessage("Error: más de un producto en la reserva")
                    .build();
        }
        Product product = orderNote.getProducts().getFirst();
        if (!product.getSku().equals("parque_termal")) {
            return new ProgramaDiaDTO.Builder()
                    .errorMessage("Error: producto no corresponde a parque_termal")
                    .build();
        }
        // Verifica cliente
        String fullName = orderNote.getBillingLastName().toUpperCase() + ", " + orderNote.getBillingFirstName().toUpperCase();
        Cliente cliente = null;
        try {
            cliente = clienteService.findByNumeroDocumento(orderNote.getBillingDniPasaporte());
        } catch (ClienteException e) {
            Long clienteId = 1 + clienteService.findLast().getClienteId();
            cliente = clienteService.add(new Cliente.Builder()
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
            return new ProgramaDiaDTO.Builder()
                    .errorMessage("Error: SKU sin asociación de Productos")
                    .build();
        }
        // Determina paxs
        int paxs = 0;
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
        var voucherProductos = new ArrayList<VoucherProducto>();
        if (paxsMayor > 0) {
            voucherProductos.add(new VoucherProducto.Builder()
                    .productoId(productoPaxMayor.getProductoId())
                    .cantidadPaxs(paxsMayor)
                    .producto(productoPaxMayor)
                    .build());
            paxs += paxsMayor;
        }
        if (paxsMenor > 0) {
            voucherProductos.add(new VoucherProducto.Builder()
                    .productoId(productoPaxMenor.getProductoId())
                    .cantidadPaxs(paxsMenor)
                    .producto(productoPaxMenor)
                    .build());
            paxs += paxsMenor;
        }
        //
        Voucher voucher = new Voucher.Builder()
                .fechaToma(orderNote.getCompletedDate())
                .fechaServicio(fechaServicio)
                .fechaVencimiento(product.getBookingStart())
                .nombrePax(fullName)
                .contacto(orderNote.getBillingPhone())
                .paxs(paxs)
                .productos(cadenaResumen(voucherProductos))
                .tieneVoucher((byte) 1)
                .clienteId(cliente.getClienteId())
                .observaciones("backend")
                .confirmado((byte) 1)
                .pagaCacheuta((byte) 0)
                .hotelId(475)
                .paxsReales(paxs)
                .proveedorId(130)
                .numeroVoucher(String.valueOf(orderNumberId))
                .usuario("admin")
                .reservaOrigenId(3)
                .ventaInternet((byte) 1)
                .cliente(cliente)
                .build();

        voucher = registrarVoucher(voucher, voucherProductos);

        return new ProgramaDiaDTO.Builder()
                .vouchers(Collections.singletonList(voucher))
                .errorMessage("")
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

    private Reserva generarReserva(Voucher voucher, List<VoucherProducto> voucherProductos) {
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
    public List<VoucherProducto> saveVoucherProductos(Long voucherId, List<VoucherProducto> voucherProductos) {
        voucherProductoService.deleteAllByVoucherId(voucherId);
        for (var voucherProducto : voucherProductos) {
            voucherProducto.setVoucherId(voucherId);
        }
        return voucherProductoService.saveAll(voucherProductos);
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

    private List<PersonType> extractPaxs(String personTypes) {
        var types = new ArrayList<PersonType>();
        Pattern pattern = Pattern.compile("\\((\\d+)\\)\\s+([^()]+)");
        Matcher matcher = pattern.matcher(personTypes);

        while (matcher.find()) {
            types.add(new PersonType(Integer.parseInt(matcher.group(1)), matcher.group(2).trim()));
        }
        return types;
    }

}
