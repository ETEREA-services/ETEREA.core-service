package eterea.core.service.service.facade;

import eterea.core.service.hexagonal.empresa.application.service.EmpresaService;
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
    private final ParametroService parametroService;
    private final ReservaContextService reservaContextService;
    private final TrackService trackService;
    private final EmpresaService empresaService;

    public TransaccionFacturaProgramaDiaService(FacturacionService facturacionService,
                                                VoucherService voucherService,
                                                ComprobanteService comprobanteService,
                                                ParametroService parametroService,
                                                ReservaContextService reservaContextService,
                                                TrackService trackService, EmpresaService empresaService) {
        this.facturacionService = facturacionService;
        this.voucherService = voucherService;
        this.comprobanteService = comprobanteService;
        this.parametroService = parametroService;
        this.reservaContextService = reservaContextService;
        this.trackService = trackService;
        this.empresaService = empresaService;
    }

    public void registroTransaccionFacturaProgramaDia(Long orderNumberId, FacturacionDto facturacionDto, Boolean soloFactura, Boolean dryRun) {
        log.debug("Processing TransaccionFacturaProgramaDiaService.registroTransaccionFacturaProgramaDia");
        log.debug("FacturacionDto -> {}", facturacionDto.jsonify());
        var voucher = voucherService.findByNumeroVoucher(String.valueOf(orderNumberId));
        log.debug("Voucher -> {}", voucher.jsonify());
        var reserva = voucher.getReserva();
        var cliente = voucher.getCliente();
        var comprobante = comprobanteService.findByComprobanteId(853);
        log.debug("Comprobante -> {}", comprobante.jsonify());
        var empresa = empresaService.findLast().get();
        var parametro = parametroService.findTop();
        var reservaContext = reservaContextService.findByReservaId(voucher.getReservaId());
        log.debug("ReservaContext -> {}", reservaContext.jsonify());
        if (dryRun == false) {
            var track = trackService.startTracking("transaccion-factura-programa-dia");
            log.debug("Track -> {}", track.jsonify());
            facturacionService.registraTransaccionFacturaProgramaDia(
                    reserva,
                    facturacionDto,
                    comprobante,
                    empresa,
                    cliente,
                    parametro,
                    reservaContext,
                    track,
                    soloFactura
            );
        }
    }

}
