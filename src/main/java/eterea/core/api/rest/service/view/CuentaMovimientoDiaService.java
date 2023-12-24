package eterea.core.api.rest.service.view;

import eterea.core.api.rest.kotlin.model.view.CuentaMovimientoDia;
import eterea.core.api.rest.repository.view.CuentaMovimientoDiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class CuentaMovimientoDiaService {

    private final CuentaMovimientoDiaRepository repository;

    private final CuentaMovimientoAperturaDiaService cuentaMovimientoAperturaDiaService;

    @Autowired
    public CuentaMovimientoDiaService(CuentaMovimientoDiaRepository repository, CuentaMovimientoAperturaDiaService cuentaMovimientoAperturaDiaService) {
        this.repository = repository;
        this.cuentaMovimientoAperturaDiaService = cuentaMovimientoAperturaDiaService;
    }

    public BigDecimal totalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
        if (incluyeApertura) {
            total = total.add(cuentaMovimientoAperturaDiaService.totalDebeEntreFechas(numeroCuenta, desde, hasta));
        }
        for (CuentaMovimientoDia cuentaMovimientoDia : repository.findAllByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(numeroCuenta, 1, 0, desde, hasta)) {
            total = total.add(cuentaMovimientoDia.getImporte());
        }
        if (incluyeInflacion) {
            for (CuentaMovimientoDia cuentaMovimientoDia : repository.findAllByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(numeroCuenta, 1, 1, desde, hasta)) {
                total = total.add(cuentaMovimientoDia.getImporte());
            }
        }
        return total;
    }

    public BigDecimal totalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
        if (incluyeApertura) {
            total = total.add(cuentaMovimientoAperturaDiaService.totalHaberEntreFechas(numeroCuenta, desde, hasta));
        }
        for (CuentaMovimientoDia cuentaMovimientoDia : repository.findAllByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(numeroCuenta, 0, 0, desde, hasta)) {
            total = total.add(cuentaMovimientoDia.getImporte());
        }
        if (incluyeInflacion) {
            for (CuentaMovimientoDia cuentaMovimientoDia : repository.findAllByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(numeroCuenta, 0, 1, desde, hasta)) {
                total = total.add(cuentaMovimientoDia.getImporte());
            }
        }
        return total;
    }

}
