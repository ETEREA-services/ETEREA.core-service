package eterea.core.service.service;

import eterea.core.service.kotlin.repository.CuentaMovimientoAperturaMonedaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CuentaMovimientoAperturaMonedaService {

    private final CuentaMovimientoAperturaMonedaRepository repository;

    public void generateMovimientosCotizados(Integer monedaIdOrigen, Integer monedaIdDestino, OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        repository.insertOrUpdateMovimientosCotizados(monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta);
    }

    public int deleteOrphanedRecords(Integer monedaId, OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        return repository.deleteOrphanedRecords(monedaId, fechaDesde, fechaHasta);
    }

}
