package eterea.core.service.hexagonal.cierrecaja.processCierre.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model.PendingCounts;
import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.ports.out.ProcessCierreRepository;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.mapper.ClienteMovimientoMapper;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.repository.JpaClienteMovimientoRepository;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.mapper.CuentaMovimientoMapper;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.repository.JpaCuentaMovimientoRepository;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.mapper.StockMovimientoMapper;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.repository.JpaStockMovimientoRepository;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.mapper.ValorMovimientoMapper;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.repository.JpaValorMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaProcessCierreRepositoryAdapter implements ProcessCierreRepository {

    private final JpaClienteMovimientoRepository clienteMovimientoRepository;
    private final JpaCuentaMovimientoRepository cuentaMovimientoRepository;
    private final JpaValorMovimientoRepository valorMovimientoRepository;
    private final JpaStockMovimientoRepository stockMovimientoRepository;

    private final ClienteMovimientoMapper clienteMovimientoMapper;
    private final CuentaMovimientoMapper cuentaMovimientoMapper;
    private final ValorMovimientoMapper valorMovimientoMapper;
    private final StockMovimientoMapper stockMovimientoMapper;

    @Override
    public PendingCounts getPendingCounts(OffsetDateTime desde, OffsetDateTime hasta) {
        List<ClienteMovimiento> clienteMovimientos = clienteMovimientoRepository
                .findAllByCierreCajaIdAndFechaComprobanteBetween(0L, desde, hasta)
                .stream()
                .map(clienteMovimientoMapper::toDomain)
                .toList();

        List<CuentaMovimiento> cuentaMovimientos = cuentaMovimientoRepository
                .findAllByCierreCajaIdAndFechaBetween(0L, desde, hasta)
                .stream()
                .map(cuentaMovimientoMapper::toDomain)
                .toList();

        List<ValorMovimiento> valorMovimientos = valorMovimientoRepository
                .findAllByCierreCajaIdAndFechaContableBetween(0L, desde, hasta)
                .stream()
                .map(valorMovimientoMapper::toDomain)
                .toList();

        List<StockMovimiento> stockMovimientos = stockMovimientoRepository
                .findAllByCierreCajaIdAndFechaComprobanteBetween(0L, desde, hasta)
                .stream()
                .map(stockMovimientoMapper::toDomain)
                .toList();

        long countCuentaMovimientoCuentaCero = cuentaMovimientos.stream()
                .filter(cm -> cm.getNumeroCuenta() != null && cm.getNumeroCuenta() == 0L)
                .count();

        return PendingCounts.builder()
                .countClienteMovimiento((long) clienteMovimientos.size())
                .countCuentaMovimiento((long) cuentaMovimientos.size())
                .countCuentaMovimientoCuentaCero(countCuentaMovimientoCuentaCero)
                .countValorMovimiento((long) valorMovimientos.size())
                .countStockMovimiento((long) stockMovimientos.size())
                .clienteMovimientos(clienteMovimientos)
                .cuentaMovimientos(cuentaMovimientos)
                .valorMovimientos(valorMovimientos)
                .stockMovimientos(stockMovimientos)
                .build();
    }

}
