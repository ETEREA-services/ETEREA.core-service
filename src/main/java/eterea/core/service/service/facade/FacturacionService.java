package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.model.Snapshot;
import eterea.core.service.model.Track;
import eterea.core.service.model.dto.snapshot.ProgramaDiaSnapshot;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FacturacionService {

    private final VoucherService voucherService;
    private final OrderNoteService orderNoteService;
    private final ValorService valorService;
    private final ClienteMovimientoService clienteMovimientoService;
    private final ReservaContextService reservaContextService;
    private final ValorMovimientoService valorMovimientoService;
    private final ReservaArticuloService reservaArticuloService;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final ReservaService reservaService;
    private final ContabilidadService contabilidadService;
    private final SnapshotService snapshotService;
    private final TrackService trackService;

    public FacturacionService(VoucherService voucherService,
                              OrderNoteService orderNoteService,
                              ValorService valorService,
                              ClienteMovimientoService clienteMovimientoService,
                              ReservaContextService reservaContextService,
                              ValorMovimientoService valorMovimientoService,
                              ReservaArticuloService reservaArticuloService,
                              ArticuloMovimientoService articuloMovimientoService,
                              ReservaService reservaService,
                              ContabilidadService contabilidadService, SnapshotService snapshotService, TrackService trackService) {
        this.voucherService = voucherService;
        this.orderNoteService = orderNoteService;
        this.valorService = valorService;
        this.clienteMovimientoService = clienteMovimientoService;
        this.reservaContextService = reservaContextService;
        this.valorMovimientoService = valorMovimientoService;
        this.reservaArticuloService = reservaArticuloService;
        this.articuloMovimientoService = articuloMovimientoService;
        this.reservaService = reservaService;
        this.contabilidadService = contabilidadService;
        this.snapshotService = snapshotService;
        this.trackService = trackService;
    }

    public ClienteMovimiento registraTransaccionFacturaProgramaDia(Reserva reserva,
                                                                   FacturacionDto facturacionDTO,
                                                                   Comprobante comprobante,
                                                                   Empresa empresa,
                                                                   Cliente cliente,
                                                                   Parametro parametro,
                                                                   ReservaContext reservaContext,
                                                                   Track track) {
        if (track == null) {
            track = trackService.startTracking("transaccion-factura-programa-dia");
        }
        var programaDiaSnapshot = ProgramaDiaSnapshot.builder()
                .reserva(reserva)
                .facturacionDto(facturacionDTO)
                .cliente(cliente)
                .comprobante(comprobante)
                .reservaContext(reservaContext)
                .build();
        Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
        logVoucher(voucher);
        programaDiaSnapshot.setVoucher(voucher);
        OrderNote orderNote = orderNoteService.findByOrderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())));
        logOrderNote(orderNote);
        programaDiaSnapshot.setOrderNote(orderNote);

        int valorId = switch (Objects.requireNonNull(Objects.requireNonNull(orderNote.getPayment()).getMarcaTarjeta())) {
            case "American Express" -> 64;
            case "Cabal" -> 67;
            case "Cabal Du00e9bito" -> 66;
            case "Maestro" -> 61;
            case "MasterCard" -> 62;
            case "MasterCard Debito" -> 61;
            case "Tarjeta Naranja" -> 60;
            case "Visa Cru00e9dito" -> 60;
            case "Visa Debito" -> 59;
            default -> 0;
        };
        Valor valor = valorService.findByValorId(valorId);
        programaDiaSnapshot.setValor(valor);

        String observaciones = "Pedido web #" + orderNote.getOrderNumberId() + " - Reserva #" + reserva.getReservaId();
        programaDiaSnapshot.setObservaciones(observaciones);

        // Construye clienteMovimiento
        ClienteMovimiento clienteMovimiento = new ClienteMovimiento.Builder()
                .negocioId(empresa.getNegocioId())
                .empresaId(empresa.getEmpresaId())
                .clienteId(cliente.getClienteId())
                .comprobanteId(comprobante.getComprobanteId())
                .fechaComprobante(ToolService.dateAbsoluteArgentina())
                .fechaVencimiento(ToolService.dateAbsoluteArgentina())
                .importe(facturacionDTO.getTotal())
                .cancelado(facturacionDTO.getTotal())  // contado
                .puntoVenta(comprobante.getPuntoVenta())
                .numeroComprobante(facturacionDTO.getNumeroComprobante())
                .montoIva(facturacionDTO.getIva())
                .montoIvaRni(facturacionDTO.getIva105())
                .neto(facturacionDTO.getNeto())
                .letraComprobante(comprobante.getLetraComprobante())
                .montoExento(facturacionDTO.getExento())
                .reservaId(reserva.getReservaId())
                .cae(facturacionDTO.getCae())
                .caeVencimiento(facturacionDTO.getVencimientoCae())
                .monedaId(1)
                .cotizacion(BigDecimal.ONE)
                .letras(ToolService.number_2_text(facturacionDTO.getTotal()))
                .observaciones(observaciones)
                .trackUuid(track.getUuid())
                .build();
        programaDiaSnapshot.setClienteMovimiento(clienteMovimiento);

        ValorMovimiento valorMovimiento = new ValorMovimiento.Builder()
                .negocioId(empresa.getNegocioId())
                .clienteId(cliente.getClienteId())
                .proveedorId(0L)
                .comprobanteId(comprobante.getComprobanteId())
                .fechaEmision(clienteMovimiento.getFechaComprobante())
                .fechaVencimiento(clienteMovimiento.getFechaComprobante())
                .valorId(valorId)
                .numeroComprobante(0L)
                .importe(facturacionDTO.getTotal())
                .numeroCuenta(valor.getNumeroCuenta())
                .proveedorMovimientoId(0L)
                .titular("")
                .banco("")
                .receptor("")
                .estadoId(0)
                .cierreCajaId(0L)
                .observaciones(observaciones)
                .trackUuid(track.getUuid())
                .build();
        programaDiaSnapshot.setValorMovimiento(valorMovimiento);

        List<ArticuloMovimiento> articuloMovimientos = new ArrayList<>();
        var reservaArticulos = reservaArticuloService.findAllByReservaId(reserva.getReservaId());
        programaDiaSnapshot.setReservaArticulos(reservaArticulos);
        logReservaArticulos(reservaArticulos);

        int item = 1;
        for (ReservaArticulo reservaArticulo : reservaArticulos) {
            articuloMovimientos.add(new ArticuloMovimiento.Builder()
                    .centroStockId(Objects.requireNonNull(reservaArticulo.getArticulo()).getCentroStockId())
                    .comprobanteId(comprobante.getComprobanteId())
                    .item(item++)
                    .articuloId(reservaArticulo.getArticuloId())
                    .negocioId(clienteMovimiento.getNegocioId())
                    .cantidad(new BigDecimal(-1 * reservaArticulo.getCantidad()))
                    .precioUnitario(reservaArticulo.getPrecioUnitario())
                    .precioUnitarioSinIva(calcularPrecioSinIva(reservaArticulo.getPrecioUnitario(), reservaArticulo.getArticulo().getIva105(), reservaArticulo.getArticulo().getExento(), parametro))
                    .precioUnitarioConIva(reservaArticulo.getPrecioUnitario())
                    .numeroCuenta(reservaArticulo.getArticulo().getCuentaVentas())
                    .iva105(reservaArticulo.getArticulo().getIva105())
                    .exento(reservaArticulo.getArticulo().getExento())
                    .fechaMovimiento(clienteMovimiento.getFechaComprobante())
                    .fechaFactura(clienteMovimiento.getFechaComprobante())
                    .precioCompra(reservaArticulo.getArticulo().getPrecioCompra())
                    .trackUuid(track.getUuid())
                    .build());
        }
        programaDiaSnapshot.setArticuloMovimientos(articuloMovimientos);

        var snapshot = Snapshot.builder()
                .uuid(UUID.randomUUID().toString())
                .descripcion("transaccion-factura-programa-dia-pre")
                .payload(logProgramaDiaSnapshot(programaDiaSnapshot))
                .trackUuid(track.getUuid())
                .previousUuid(null)
                .build();
        snapshot = snapshotService.add(snapshot);

        // Comienza registro en la db
        // Registra clienteMovimiento
        clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);
        logClienteMovimiento(clienteMovimiento);
        programaDiaSnapshot.setClienteMovimiento(clienteMovimiento);

        // Registra reservaContext
        reservaContext.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
        programaDiaSnapshot.setReservaContext(reservaContext);

        // Registra valorMovimiento
        valorMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        valorMovimiento = valorMovimientoService.add(valorMovimiento);
        programaDiaSnapshot.setValorMovimiento(valorMovimiento);

        // Registra articuloMovimientos
        for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
            articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        }
        articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);
        programaDiaSnapshot.setArticuloMovimientos(articuloMovimientos);

        List<CuentaMovimiento> cuentaMovimientos = contabilidadService.registraContabilidadProgramaDia(clienteMovimiento, valorMovimiento, valor, articuloMovimientos, facturacionDTO, comprobante, parametro, track);
        programaDiaSnapshot.setCuentaMovimientos(cuentaMovimientos);

        reserva.setFacturada((byte) 1);
        reserva.setVerificada((byte) 1);
        reserva = reservaService.update(reserva, reserva.getReservaId());
        programaDiaSnapshot.setReserva(reserva);

        voucher.setConfirmado((byte) 1);
        voucher = voucherService.update(voucher, voucher.getVoucherId());
        programaDiaSnapshot.setVoucher(voucher);

        snapshot = Snapshot.builder()
                .uuid(UUID.randomUUID().toString())
                .descripcion("transaccion-factura-programa-dia-post")
                .payload(logProgramaDiaSnapshot(programaDiaSnapshot))
                .trackUuid(track.getUuid())
                .previousUuid(snapshot.getUuid())
                .build();
        snapshot = snapshotService.add(snapshot);

        track = trackService.endTracking(track);

        return clienteMovimiento;

    }

    @Transactional
    public ClienteMovimiento registraTransaccionFacturaFaltante(ClienteMovimiento clienteMovimiento,
                                                                ArticuloMovimiento articuloMovimiento) {
        log.debug("Processing FacturacionService.registraTransaccionFacturaFaltante");
        clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);
        logClienteMovimiento(clienteMovimiento);
        articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        articuloMovimiento = articuloMovimientoService.add(articuloMovimiento);
        logArticuloMovimiento(articuloMovimiento);
        contabilidadService.registraFacturaFaltanteCuentaCorriente(clienteMovimiento, articuloMovimiento);
        return clienteMovimiento;
    }

    private BigDecimal calcularPrecioSinIva(BigDecimal precioUnitario, byte iva105, byte exento, Parametro parametro) {
        if (exento == 1) {
            return precioUnitario;
        }
        var coeficiente = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
        if (iva105 == 1) {
            coeficiente = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
        }
        var precioUnitarioSinIva = precioUnitario.divide(BigDecimal.ONE.add(coeficiente), 5, RoundingMode.HALF_UP);
        return precioUnitarioSinIva.setScale(2, RoundingMode.HALF_UP);
    }

    private String logValorMovimiento(ValorMovimiento valorMovimiento) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(valorMovimiento);
            log.debug("valorMovimiento={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("valorMovimiento jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logReservaContext(ReservaContext reservaContext) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(reservaContext);
            log.debug("reservaContext={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("reservaContext jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logOrderNote(OrderNote orderNote) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(orderNote);
            log.debug("orderNote={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("orderNote jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logVoucher(Voucher voucher) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(voucher);
            log.debug("voucher={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("voucher jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logClienteMovimiento(ClienteMovimiento clienteMovimiento) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(clienteMovimiento);
            log.debug("clienteMovimiento={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("clienteMovimiento jsonify error {}", e.getMessage());
            return null;
        }
    }

    private void logArticuloMovimiento(ArticuloMovimiento articuloMovimiento) {
        try {
            log.debug("articuloMovimiento={}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(articuloMovimiento));
        } catch (JsonProcessingException e) {
            log.debug("articuloMovimiento jsonify error {}", e.getMessage());
        }
    }

    private String logReserva(Reserva reserva) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(reserva);
            log.debug("reserva={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("reserva jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logArticuloMovimientos(List<ArticuloMovimiento> articuloMovimientos) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(articuloMovimientos);
            log.debug("articuloMovimientos={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("articuloMovimientos jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logReservaArticulos(List<ReservaArticulo> reservaArticulos) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(reservaArticulos);
            log.debug("reservaArticulos={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("reservaArticulos jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logCuentaMovimientos(List<CuentaMovimiento> cuentaMovimientos) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(cuentaMovimientos);
            log.debug("cuentaMovimientos={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("cuentaMovimientos jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logCliente(Cliente cliente) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(cliente);
            log.debug("cliente={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("cliente jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logProgramaDiaSnapshot(ProgramaDiaSnapshot programaDiaSnapshot) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(programaDiaSnapshot);
            log.debug("programaDiaSnapshot={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("programaDiaSnapshot jsonify error {}", e.getMessage());
            return null;
        }
    }

}

