package eterea.core.service.hexagonal.legajo.domain.ports.in;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;

import java.util.List;

public interface GetAllLegajosUseCase {

    List<Legajo> getAllLegajos();

}
