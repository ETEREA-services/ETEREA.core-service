package eterea.core.service.hexagonal.stock.articulo.application.usecases;

import eterea.core.service.hexagonal.stock.articulo.application.exception.ArticuloException;
import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.in.FindArticuloByAutoNumericoUseCase;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.out.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindArticuloByAutoNumericoUseCaseImpl implements FindArticuloByAutoNumericoUseCase {

    private final ArticuloRepository articuloRepository;

    @Override
    public Articulo findByAutoNumerico(Long autoNumerico) {
        return articuloRepository.findByAutoNumericoId(autoNumerico)
                .orElseThrow(() -> new ArticuloException(autoNumerico));
    }
}
