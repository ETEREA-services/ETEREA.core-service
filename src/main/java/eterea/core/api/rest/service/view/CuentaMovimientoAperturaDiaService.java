package eterea.core.api.rest.service.view;

import eterea.core.api.rest.kotlin.model.view.CuentaMovimientoAperturaDia;
import eterea.core.api.rest.repository.view.CuentaMovimientoAperturaDiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class CuentaMovimientoAperturaDiaService {

    private final CuentaMovimientoAperturaDiaRepository repository;

    @Autowired
    public CuentaMovimientoAperturaDiaService(CuentaMovimientoAperturaDiaRepository repository) {
        this.repository = repository;
    }

    public BigDecimal totalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        BigDecimal total = BigDecimal.ZERO;

        for (CuentaMovimientoAperturaDia cuentaMovimientoAperturaDia : repository.findAllByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 1, desde, hasta)) {
            total = total.add(cuentaMovimientoAperturaDia.getImporte());
        }

        return total;
    }

    public BigDecimal totalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        BigDecimal total = BigDecimal.ZERO;

        for (CuentaMovimientoAperturaDia cuentaMovimientoAperturaDia : repository.findAllByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 0, desde, hasta)) {
            total = total.add(cuentaMovimientoAperturaDia.getImporte());
        }

        return total;
    }

}
