package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.*;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransaccionFacturaProgramaDiaService {

    private final FacturacionService facturacionService;
    private final VoucherService voucherService;
    private final ComprobanteService comprobanteService;
    private final EmpresaService empresaService;
    private final ParametroService parametroService;
    private final ReservaContextService reservaContextService;
    private final TrackService trackService;

    public TransaccionFacturaProgramaDiaService(FacturacionService facturacionService,
                                                VoucherService voucherService,
                                                ComprobanteService comprobanteService,
                                                EmpresaService empresaService,
                                                ParametroService parametroService,
                                                ReservaContextService reservaContextService,
                                                TrackService trackService) {
        this.facturacionService = facturacionService;
        this.voucherService = voucherService;
        this.comprobanteService = comprobanteService;
        this.empresaService = empresaService;
        this.parametroService = parametroService;
        this.reservaContextService = reservaContextService;
        this.trackService = trackService;
    }

    public void registroTransaccionFacturaProgramaDia(Long orderNumberId, FacturacionDto facturacionDto, Boolean dryRun) {
        logFacturacionDto(facturacionDto);
        var voucher = voucherService.findByNumeroVoucher(String.valueOf(orderNumberId));
        logVoucher(voucher);
        var reserva = voucher.getReserva();
        var cliente = voucher.getCliente();
        var comprobante = comprobanteService.findByComprobanteId(853);
        logComprobante(comprobante);
        var empresa = empresaService.findTop();
        logEmpresa(empresa);
        var parametro = parametroService.findTop();
        logParametro(parametro);
        var reservaContext = reservaContextService.findByReservaId(voucher.getReservaId());
        logReservaContext(reservaContext);
        if (dryRun == false) {
            var track = trackService.startTracking("transaccion-factura-programa-dia");
            logTrack(track);
            facturacionService.registraTransaccionFacturaProgramaDia(reserva, facturacionDto, comprobante, empresa, cliente, parametro, reservaContext, track);
        }
    }

    private void logFacturacionDto(FacturacionDto facturacionDto) {
        try {
            log.debug("FacturacionDto -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(facturacionDto));
        } catch (JsonProcessingException e) {
            log.debug("FacturacionDto jsonify error -> {}", e.getMessage());
        }
    }

    private void logVoucher(Voucher voucher) {
        try {
            log.debug("Voucher -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(voucher));
        } catch (JsonProcessingException e) {
            log.debug("Voucher jsonify error -> {}", e.getMessage());
        }
    }

    private void logComprobante(Comprobante comprobante) {
        try {
            log.debug("Comprobante -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(comprobante));
        } catch (JsonProcessingException e) {
            log.debug("Comprobante jsonify error -> {}", e.getMessage());
        }
    }

    private void logEmpresa(Empresa empresa) {
        try {
            log.debug("Empresa -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(empresa));
        } catch (JsonProcessingException e) {
            log.debug("Empresa jsonify error -> {}", e.getMessage());
        }
    }

    private void logParametro(Parametro parametro) {
        try {
            log.debug("Parametro -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(parametro));
        } catch (JsonProcessingException e) {
            log.debug("Parametro jsonify error -> {}", e.getMessage());
        }
    }

    private void logReservaContext(ReservaContext reservaContext) {
        try {
            log.debug("ReservaContext -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(reservaContext));
        } catch (JsonProcessingException e) {
            log.debug("ReservaContext jsonify error -> {}", e.getMessage());
        }
    }

    private void logTrack(Track track) {
        try {
            log.debug("Track -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(track));
        } catch (JsonProcessingException e) {
            log.debug("Track jsonify error -> {}", e.getMessage());
        }
    }

}
