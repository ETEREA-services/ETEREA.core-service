package eterea.core.service.hexagonal.stock.articulo.application.usecases;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.in.FindAllArticulosUseCase;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.out.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllArticulosUseCaseImpl implements FindAllArticulosUseCase {

    private final ArticuloRepository articuloRepository;

    @Override
    public List<Articulo> findAll() {
        return articuloRepository.findAll();
    }
}
