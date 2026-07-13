package eterea.core.service.hexagonal.stock.articulo.application.usecases;

import eterea.core.service.hexagonal.stock.articulo.application.exception.ArticuloException;
import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.in.FindArticuloByMascaraBalanzaUseCase;
import eterea.core.service.hexagonal.stock.articulo.domain.ports.out.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindArticuloByMascaraBalanzaUseCaseImpl implements FindArticuloByMascaraBalanzaUseCase {

    private final ArticuloRepository articuloRepository;

    @Override
    public Articulo findByMascaraBalanza(String mascaraBalanza) {
        return articuloRepository.findByMascaraBalanza(mascaraBalanza)
                .orElseThrow(() -> new ArticuloException(mascaraBalanza));
    }
}
