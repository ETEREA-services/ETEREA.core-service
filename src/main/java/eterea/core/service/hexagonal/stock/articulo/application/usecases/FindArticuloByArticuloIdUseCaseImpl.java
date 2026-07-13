package eterea.core.service.hexagonal.stock.articulo.application.usecases;

import eterea.core.service.hexagonal.stock.articulo.application.exception.ArticuloException;
import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.in.FindArticuloByArticuloIdUseCase;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.out.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindArticuloByArticuloIdUseCaseImpl implements FindArticuloByArticuloIdUseCase {

    private final ArticuloRepository articuloRepository;

    @Override
    public Articulo findByArticuloId(String articuloId) {
        return articuloRepository.findByArticuloId(articuloId)
                .orElseThrow(() -> new ArticuloException(articuloId));
    }
}
