package eterea.core.service.hexagonal.negocio.domain.ports.in;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;

import java.util.List;

public interface GetAllNegociosWithIpAddressUseCase {

    List<Negocio> getAllNegociosWithIpAddress(Integer negocioId);

}
