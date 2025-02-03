package eterea.core.service.service;

import eterea.core.service.kotlin.repository.CuentaMovimientoAperturaMonedaRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CuentaMovimientoAperturaMonedaService {

    private final CuentaMovimientoAperturaMonedaRepository repository;

    public CuentaMovimientoAperturaMonedaService(CuentaMovimientoAperturaMonedaRepository repository) {
        this.repository = repository;
    }

    public void generateMovimientosCotizados(Integer monedaId, OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        repository.insertOrUpdateMovimientosCotizados(monedaId, fechaDesde, fechaHasta);
    }

    public int deleteOrphanedRecords(Integer monedaId, OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        return repository.deleteOrphanedRecords(monedaId, fechaDesde, fechaHasta);
    }

}
