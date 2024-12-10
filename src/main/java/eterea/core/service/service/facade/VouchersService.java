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
import java.util.*;
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

    private record PersonType(int cantidad, String descripcion) {}

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
        log.debug("Processing importOneFromWeb");
        OrderNote orderNote = getOrderNoteById(orderNumberId);
        if (orderNote == null || !isOrderCompleted(orderNote)) {
            return createErrorResponse("Error: Order Note pendiente de PAGO");
        }

        logOrderNote(orderNote);

        if (isVoucherAlreadyRegistered(orderNumberId)) {
            return createErrorResponse("Error: Programa por el Día YA registrado");
        }

        if (Objects.requireNonNull(orderNote.getProducts()).isEmpty()) {
            return createErrorResponse("Error: reserva sin productos");
        }

        if (orderNote.getProducts().size() > 1) {
            return createErrorResponse("Error: más de un producto en la reserva");
        }

        Product product = orderNote.getProducts().getFirst();
        assert product != null;
        return processProduct(orderNote, product, negocioService.findByNegocioId(empresaService.findTop().getNegocioId()));
    }

    private OrderNote getOrderNoteById(Long orderNumberId) {
        log.debug("Processing getOrderNoteById");
        return orderNoteService.findByOrderNumberId(orderNumberId);
    }

    private boolean isOrderCompleted(OrderNote orderNote) {
        log.debug("Processing isOrderCompleted");
        return Arrays.asList("Completado", "Completed").contains(orderNote.getOrderStatus());
    }

    private void logOrderNote(OrderNote orderNote) {
        log.debug("Processing logOrderNote");
        try {
            log.debug("order_note={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(orderNote));
        } catch (JsonProcessingException e) {
            log.debug("order_note=NOT_FOUND");
        }
    }

    private boolean isVoucherAlreadyRegistered(Long orderNumberId) {
        log.debug("Processing isVoucherAlreadyRegistered");
        try {
            voucherService.findByNumeroVoucherAlreadyRegistered(String.valueOf(orderNumberId));
            return true;
        } catch (VoucherException e) {
            log.debug("Voucher not found, proceeding.");
            return false;
        }
    }

    private ProgramaDiaDto createErrorResponse(String message) {
        log.debug("Processing createErrorResponse");
        return new ProgramaDiaDto.Builder().errorMessage(message).build();
    }

    private ProgramaDiaDto processProduct(OrderNote orderNote, Product product, Negocio negocio) {
        log.debug("Processing processProduct");
        switch (product.getSku()) {
            case "parque_termal":
            case "tarde_termaspa":
                return facturaUnProducto(orderNote, 130, 475, product, negocio);
            case "termaspa_fullday":
                if (product.getServiciosAdicionales().isEmpty()) {
                    return facturaUnProducto(orderNote, 130, 475, product, negocio);
                }
                break;
            case "parque_termal_traslado":
                if (product.getServiciosAdicionales().isEmpty()) {
                    return facturaUnProducto(orderNote, 31, 475, product, negocio);
                }
                break;
        }
        return createErrorResponse("Error: no corresponde a un producto facturable");
    }

    @Transactional
    public Voucher registrarVoucher(Voucher voucher, List<VoucherProducto> voucherProductos) {
        log.debug("Processing registrarVoucher");
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
        log.debug("Processing saveVoucherProductos");
        voucherProductoService.deleteAllByVoucherId(voucherId);
        for (var voucherProducto : voucherProductos) {
            voucherProducto.setVoucherId(voucherId);
        }
        return voucherProductoService.saveAll(voucherProductos);
    }

    private List<PersonType> extractPaxs(String personTypes) {
        log.debug("Processing extractPaxs");
        var types = new ArrayList<PersonType>();
        Pattern pattern = Pattern.compile("\\((\\d+)\\)\\s+([^()]+)");
        Matcher matcher = pattern.matcher(personTypes);

        while (matcher.find()) {
            types.add(new PersonType(Integer.parseInt(matcher.group(1)), matcher.group(2).trim()));
        }
        return types;
    }

    private String cadenaResumen(List<VoucherProducto> voucherProductos) {
        log.debug("Processing cadenaResumen");
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
        log.debug("Processing generarReserva");
        Reserva reserva = reservaService.copyFromVoucher(voucher);
        reserva.setNegocioId(empresaService.findTop().getNegocioId());
        reserva.setUsuario("admin");
        reserva = reservaService.add(reserva);

        voucher.setReservaId(reserva.getReservaId());
        voucher = voucherService.update(voucher, voucher.getVoucherId());
        logVoucher(voucher);

        reservaService.generarReservaArticulo(reserva, voucherProductos);

        return reserva;
    }

    private void logVoucher(Voucher voucher) {
        log.debug("Processing logVoucher");
        try {
            log.debug("Voucher -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(voucher));
        } catch (JsonProcessingException e) {
            log.debug("Voucher error -> {}", e.getMessage());
        }
    }

    @Transactional
    public ProgramaDiaDto facturaUnProducto(OrderNote orderNote, Integer proveedorId, Integer hotelId, Product product, Negocio negocio) {
        log.debug("Processing facturaUnProducto");
        String fullName = orderNote.getBillingLastName().toUpperCase() + ", " + orderNote.getBillingFirstName().toUpperCase();
        var cliente = determinaCliente(orderNote, fullName, negocio);
        OffsetDateTime fechaServicio = product.getBookingStart();
        byte[] dias = new byte[7]; // Lunes a Domingo
        byte feriado = 0;

        try {
            feriadoService.findByFecha(fechaServicio);
            feriado = 1;
            assert fechaServicio != null;
            dias[fechaServicio.getDayOfWeek().getValue() - 1] = 0; // Marca el día correspondiente
        } catch (FeriadoException e) {
            assert fechaServicio != null;
            dias[fechaServicio.getDayOfWeek().getValue() - 1] = 1; // Marca el día correspondiente
        }

        Producto productoPaxMayor = null;
        Producto productoPaxMenor = null;
        Producto productoPaxInfante = null;

        try {
            ProductoSku productoSku = productoSkuService.findBySku(product.getSku(), dias[0], dias[1], dias[2], dias[3], dias[4], dias[5], dias[6], feriado);
            productoPaxMayor = productoSku.getProductoPaxMayor();
            productoPaxMenor = productoSku.getProductoPaxMenor();
            productoPaxInfante = productoSku.getProductoPaxInfante();
        } catch (ProductoSkuException e) {
            return createErrorResponse("Error: SKU sin asociación de Productos");
        }

        int paxsMenor = 0;
        int paxsMayor = 0;
        int paxsInfante = 0;
        for (PersonType personType : extractPaxs(product.getPersonTypes())) {
            log.debug("personType={}", personType);
            if (personType.descripcion().contains("Niño")) {
                paxsMenor = personType.cantidad();
            }
            if (personType.descripcion().contains("Adulto")) {
                paxsMayor = personType.cantidad();
            }
            if (personType.descripcion().contains("Infante")) {
                paxsInfante = personType.cantidad();
            }
        }

        var voucherProductos = determinaProductos(paxsMayor, paxsMenor, paxsInfante, productoPaxMayor, productoPaxMenor, productoPaxInfante);
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
                .hotelId(hotelId)
                .paxsReales(paxsMayor + paxsMenor)
                .proveedorId(proveedorId)
                .numeroVoucher(String.valueOf(orderNote.getOrderNumberId()))
                .usuario("admin")
                .reservaOrigenId(3)
                .ventaInternet((byte) 1)
                .cliente(cliente)
                .subeEn(product.getPuntoDeEncuentro())
                .build();

        voucher = registrarVoucher(voucher, voucherProductos);
        logVoucher(voucher);

        return new ProgramaDiaDto.Builder()
                .vouchers(Collections.singletonList(voucher))
                .errorMessage("")
                .build();
    }

    private List<VoucherProducto> determinaProductos(int paxsMayor, int paxsMenor, int paxsInfante, Producto productoPaxMayor, Producto productoPaxMenor, Producto productoPaxInfante) {
        log.debug("Processing determinaProductos");
        var voucherProductos = new ArrayList<VoucherProducto>();
        if (paxsMayor > 0 && productoPaxMayor != null) {
            voucherProductos.add(new VoucherProducto.Builder()
                    .productoId(productoPaxMayor.getProductoId())
                    .cantidadPaxs(paxsMayor)
                    .producto(productoPaxMayor)
                    .build());
        }
        if (paxsMenor > 0 && productoPaxMenor != null) {
            voucherProductos.add(new VoucherProducto.Builder()
                    .productoId(productoPaxMenor.getProductoId())
                    .cantidadPaxs(paxsMenor)
                    .producto(productoPaxMenor)
                    .build());
        }
        if (paxsInfante > 0 && productoPaxInfante != null) {
            voucherProductos.add(new VoucherProducto.Builder()
                    .productoId(productoPaxInfante.getProductoId())
                    .cantidadPaxs(paxsInfante)
                    .producto(productoPaxInfante)
                    .build());
        }
        logVoucherProductos(voucherProductos);
        return voucherProductos;
    }

    private void logVoucherProductos(ArrayList<VoucherProducto> voucherProductos) {
        log.debug("Processing logVoucherProductos");
        try {
            log.debug("VoucherProductos -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(voucherProductos));
        } catch (JsonProcessingException e) {
            log.debug("VoucherProductos - error -> {}", e.getMessage());
        }
    }

    @Transactional
    public Cliente determinaCliente(OrderNote orderNote, String fullName, Negocio negocio) {
        log.debug("Processing determinaCliente");
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
