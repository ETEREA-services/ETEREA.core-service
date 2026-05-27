package eterea.core.service.hexagonal.articulo.domain.ports.in;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;

public interface FindArticuloByMascaraBalanzaUseCase {
    Articulo findByMascaraBalanza(String mascaraBalanza);
}
