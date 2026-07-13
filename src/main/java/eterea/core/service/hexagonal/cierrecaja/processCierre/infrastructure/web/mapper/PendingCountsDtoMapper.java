package eterea.core.service.hexagonal.cierrecaja.processCierre.infrastructure.web.mapper;

import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model.PendingCounts;
import eterea.core.service.hexagonal.cierrecaja.processCierre.infrastructure.web.dto.PendingCountsResponse;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.mapper.ClienteMovimientoDtoMapper;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.mapper.CuentaMovimientoDtoMapper;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.mapper.StockMovimientoDtoMapper;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.mapper.ValorMovimientoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class PendingCountsDtoMapper {

    private final ClienteMovimientoDtoMapper clienteMovimientoDtoMapper;
    private final CuentaMovimientoDtoMapper cuentaMovimientoDtoMapper;
    private final ValorMovimientoDtoMapper valorMovimientoDtoMapper;
    private final StockMovimientoDtoMapper stockMovimientoDtoMapper;

    public PendingCountsResponse toResponse(PendingCounts domain) {
        if (domain == null) {
            return null;
        }
        return PendingCountsResponse.builder()
                .countClienteMovimiento(domain.getCountClienteMovimiento())
                .countCuentaMovimiento(domain.getCountCuentaMovimiento())
                .countCuentaMovimientoCuentaCero(domain.getCountCuentaMovimientoCuentaCero())
                .countValorMovimiento(domain.getCountValorMovimiento())
                .countStockMovimiento(domain.getCountStockMovimiento())
                .clienteMovimientos(domain.getClienteMovimientos() != null
                        ? domain.getClienteMovimientos().stream().map(clienteMovimientoDtoMapper::toResponse).toList()
                        : Collections.emptyList())
                .cuentaMovimientos(domain.getCuentaMovimientos() != null
                        ? domain.getCuentaMovimientos().stream().map(cuentaMovimientoDtoMapper::toResponse).toList()
                        : Collections.emptyList())
                .valorMovimientos(domain.getValorMovimientos() != null
                        ? domain.getValorMovimientos().stream().map(valorMovimientoDtoMapper::toResponse).toList()
                        : Collections.emptyList())
                .stockMovimientos(domain.getStockMovimientos() != null
                        ? domain.getStockMovimientos().stream().map(stockMovimientoDtoMapper::toResponse).toList()
                        : Collections.emptyList())
                .build();
    }

}
