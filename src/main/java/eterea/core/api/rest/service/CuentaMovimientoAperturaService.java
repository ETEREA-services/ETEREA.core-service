package eterea.core.api.rest.service;

import eterea.core.api.rest.repository.ICuentaMovimientoAperturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class CuentaMovimientoAperturaService {

    private final ICuentaMovimientoAperturaRepository repository;

    @Autowired
    public CuentaMovimientoAperturaService(ICuentaMovimientoAperturaRepository repository) {
        this.repository = repository;
    }

    public BigDecimal calculateTotalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 1, desde, hasta);
    }

    public BigDecimal calculateTotalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 0, desde, hasta);
    }

}
