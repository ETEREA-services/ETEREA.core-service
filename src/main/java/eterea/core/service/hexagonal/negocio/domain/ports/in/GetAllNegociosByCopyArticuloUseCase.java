package eterea.core.service.hexagonal.negocio.domain.ports.in;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;

import java.util.List;

public interface GetAllNegociosByCopyArticuloUseCase {

    List<Negocio> getAllNegociosByCopyArticulo(Byte copyArticulo);

}
