package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ArticuloMovimientoResponse;
import eterea.core.service.kotlin.exception.ConceptoFacturadoException;
import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.ConceptoFacturado;
import eterea.core.service.service.ConceptoFacturadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ArticuloMovimientoMapper {

    private final ArticuloMapper articuloMapper;
    private final ConceptoFacturadoService conceptoFacturadoService;
    private final ConceptoFacturadoMapper conceptoFacturadoMapper;

    public ArticuloMovimientoResponse toResponse(ArticuloMovimiento articuloMovimiento) {
        if (articuloMovimiento == null) {
            return null;
        }
        ConceptoFacturado conceptoFacturado = null;
        try {
            conceptoFacturado = conceptoFacturadoService.findByArticuloMovimientoId(articuloMovimiento.getArticuloMovimientoId());
        } catch (ConceptoFacturadoException e) {
            log.error(e.getMessage());
        }
        return ArticuloMovimientoResponse.builder()
                .cantidad(articuloMovimiento.getCantidad())
                .precioUnitarioSinIva(articuloMovimiento.getPrecioUnitarioSinIva())
                .precioUnitarioConIva(articuloMovimiento.getPrecioUnitarioConIva())
                .articulo(articuloMapper.toResponse(articuloMovimiento.getArticulo()))
                .conceptoFacturado(conceptoFacturadoMapper.toResponse(conceptoFacturado))
                .build();
    }

}
