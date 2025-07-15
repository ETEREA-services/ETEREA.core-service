package eterea.core.service.service.facade.reserva;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.exception.ClienteException;
import eterea.core.service.kotlin.exception.FeriadoException;
import eterea.core.service.kotlin.exception.ProductoSkuException;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.extern.Product;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.ProgramaDiaDto;
import eterea.core.service.model.ReservaContext;
import eterea.core.service.model.Track;
import eterea.core.service.service.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ProductsService {

    private final TrackService trackService;
    private final FeriadoService feriadoService;
    private final ProductoSkuService productoSkuService;
    private final ClienteService clienteService;
    private final VoucherService voucherService;
    private final ReservaContextService reservaContextService;
    private final ReservaService reservaService;
    private final EmpresaService empresaService;
    private final VoucherProductoService voucherProductoService;

    private record PersonType(int cantidad, String descripcion) {}

    public ProductsService(TrackService trackService, FeriadoService feriadoService, ProductoSkuService productoSkuService, ClienteService clienteService, VoucherService voucherService, ReservaContextService reservaContextService, ReservaService reservaService, EmpresaService empresaService, VoucherProductoService voucherProductoService) {
        this.trackService = trackService;
        this.feriadoService = feriadoService;
        this.productoSkuService = productoSkuService;
        this.clienteService = clienteService;
        this.voucherService = voucherService;
        this.reservaContextService = reservaContextService;
        this.reservaService = reservaService;
        this.empresaService = empresaService;
        this.voucherProductoService = voucherProductoService;
    }

    @Transactional
    public ProgramaDiaDto processOneProduct(OrderNote orderNote, Integer proveedorId, Integer hotelId, Product product, Negocio negocio, Track track) {
        log.debug("Processing facturaUnProducto");
        if (track == null) {
            track = trackService.startTracking("factura-un-producto");
        }
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

        Producto productoPaxMayor;
        Producto productoPaxMenor;
        Producto productoPaxInfante;

        try {
            ProductoSku productoSku = productoSkuService.findBySku(product.getSku(), dias[0], dias[1], dias[2], dias[3], dias[4], dias[5], dias[6], feriado);
            productoPaxMayor = productoSku.getProductoPaxMayor();
            productoPaxMenor = productoSku.getProductoPaxMenor();
            productoPaxInfante = productoSku.getProductoPaxInfante();
        } catch (ProductoSkuException e) {
            return createErrorResponse();
        }

        int paxsMenor = 0;
        int paxsMayor = 0;
        int paxsInfante = 0;
        for (ProductsService.PersonType personType : extractPaxs(product.getPersonTypes())) {
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
                .trackUuid(track.getUuid())
                .build();

        voucher = registrarVoucher(voucher, voucherProductos, track);
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

    @Transactional
    public Voucher registrarVoucher(Voucher voucher, List<VoucherProducto> voucherProductos, Track track) {
        log.debug("Processing registrarVoucher");
        if (track == null) {
            track = trackService.startTracking("registro-voucher");
        }
        voucher = voucherService.save(voucher, track);
        voucherProductos = saveVoucherProductos(voucher.getVoucherId(), voucherProductos, track);
        voucher.setProductos(cadenaResumen(voucherProductos));
        voucher = voucherService.save(voucher, track);

        if (voucher.getReservaId() == null) {
            Reserva reserva = generarReserva(voucher, voucherProductos, track);
            voucher.setReservaId(reserva.getReservaId());
        }

        ReservaContext reservaContext = ReservaContext.builder()
                .reservaId(voucher.getReservaId())
                .voucherId(voucher.getVoucherId())
                .orderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())))
                .facturaPendiente((byte) 1)
                .envioPendiente((byte) 1)
                .diferenciaWeb(BigDecimal.ZERO)
                .build();
        reservaContextService.add(reservaContext);

        return voucher;
    }

    @Transactional
    public List<VoucherProducto> saveVoucherProductos(Long voucherId, List<VoucherProducto> voucherProductos, Track track) {
        log.debug("Processing saveVoucherProductos");
        if (track == null) {
            track = trackService.startTracking("save-voucher-productos");
        }
        voucherProductoService.deleteAllByVoucherId(voucherId);
        for (var voucherProducto : voucherProductos) {
            voucherProducto.setVoucherId(voucherId);
            assert track != null;
            voucherProducto.setTrackUuid(track.getUuid());
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
            cadena.append(Objects.requireNonNull(voucherProducto.getProducto()).getNombre()).append(" x ").append(voucherProducto.getCantidadPaxs());
            primero = false;
        }
        return cadena.toString();
    }

    @Transactional
    public Reserva generarReserva(Voucher voucher, List<VoucherProducto> voucherProductos, Track track) {
        log.debug("Processing generarReserva");
        if (track == null) {
            track = trackService.startTracking("generar-reserva");
        }
        Reserva reserva = reservaService.copyFromVoucher(voucher);
        reserva.setNegocioId(empresaService.findTop().getNegocioId());
        reserva.setUsuario("admin");
        reserva.setTrackUuid(track.getUuid());
        reserva = reservaService.add(reserva, track);

        voucher.setReservaId(reserva.getReservaId());
        voucher.setTrackUuid(track.getUuid());
        voucher = voucherService.update(voucher, voucher.getVoucherId());
        logVoucher(voucher);

        reservaService.generarReservaArticulo(reserva, voucherProductos, track);

        return reserva;
    }

    private ProgramaDiaDto createErrorResponse() {
        log.debug("Processing createErrorResponse");
        return new ProgramaDiaDto.Builder().errorMessage("Error: SKU sin asociación de Productos").build();
    }

    private void logVoucherProductos(ArrayList<VoucherProducto> voucherProductos) {
        log.debug("Processing logVoucherProductos");
        try {
            log.debug("VoucherProductos -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(voucherProductos));
        } catch (JsonProcessingException e) {
            log.debug("VoucherProductos - error -> {}", e.getMessage());
        }
    }

    private void logVoucher(Voucher voucher) {
        log.debug("Processing logVoucher");
        try {
            log.debug("Voucher -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(voucher));
        } catch (JsonProcessingException e) {
            log.debug("Voucher error -> {}", e.getMessage());
        }
    }

}
