package eterea.core.service.service;

import eterea.core.service.repository.CuentaMovimientoAperturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CuentaMovimientoAperturaService {

    private final CuentaMovimientoAperturaRepository repository;

    public BigDecimal calculateTotalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 1, desde, hasta);
    }

    public BigDecimal calculateTotalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, 0, desde, hasta);
    }

}
