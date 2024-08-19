package eterea.core.api.rest.service;

import eterea.core.api.rest.repository.CuentaMovimientoAperturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class CuentaMovimientoAperturaService {

    private final CuentaMovimientoAperturaRepository repository;

    @Autowired
    public CuentaMovimientoAperturaService(CuentaMovimientoAperturaRepository repository) {
        this.repository = repository;
    }

    public BigDecimal calculateTotalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 1, desde, hasta);
    }

    public BigDecimal calculateTotalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 0, desde, hasta);
    }

}
