package eterea.core.service.hexagonal.articulo.application.usecases;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.articulo.domain.ports.in.CreateArticuloUseCase;
import eterea.core.service.hexagonal.articulo.domain.ports.out.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateArticuloUseCaseImpl implements CreateArticuloUseCase {

    private final ArticuloRepository articuloRepository;

    @Override
    public Articulo createArticulo(Articulo articulo) {
        articulo.setAutoNumericoId(null);
        return articuloRepository.save(articulo);
    }
}
