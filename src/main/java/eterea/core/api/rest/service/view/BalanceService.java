package eterea.core.api.rest.service.view;

import eterea.core.api.rest.kotlin.model.CuentaMovimiento;
import eterea.core.api.rest.kotlin.model.view.AsientoView;
import eterea.core.api.rest.service.CuentaMovimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BalanceService {

    @Autowired
    private AsientoViewService asientoViewService;

    @Autowired
    private CuentaMovimientoService cuentaMovimientoService;

    public void findAllDifferences() {
        List<AsientoView> asientos = asientoViewService.findAllDifferences(OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), OffsetDateTime.of(2022, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC));
        List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
        for (AsientoView asientoView : asientos) {
            BigDecimal diferencia = asientoView.getDebe().subtract(asientoView.getHaber()).setScale(2, RoundingMode.HALF_UP);
            Byte debita = (byte) (diferencia.compareTo(BigDecimal.ZERO) < 0 ? 0 : 1);
            Long numeroCuenta = 44106000L;
            CuentaMovimiento lastByAsiento = cuentaMovimientoService.findLastByAsiento(asientoView.getFecha(), asientoView.getOrden());
            CuentaMovimiento cuentaMovimiento = new CuentaMovimiento(null, lastByAsiento.getFecha(), lastByAsiento.getOrden(), 1 + lastByAsiento.getItem(), debita, lastByAsiento.getNegocioId(), numeroCuenta, lastByAsiento.getComprobanteId(), lastByAsiento.getConcepto(), diferencia.abs(), lastByAsiento.getSubrubroId(), lastByAsiento.getProveedorId(), lastByAsiento.getClienteId(), lastByAsiento.getCierreCajaId(), lastByAsiento.getNivel(), lastByAsiento.getFirma(), lastByAsiento.getTipoAsientoId(), lastByAsiento.getArticuloMovimientoId(), lastByAsiento.getEjercicioId(), lastByAsiento.getInflacion(), null, null, null);
            cuentaMovimientos.add(cuentaMovimiento);
        }
        cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
    }
}
