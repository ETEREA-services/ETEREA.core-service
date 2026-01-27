package eterea.core.service.hexagonal.legajo.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.legajo.infrastructure.persistence.entity.LegajoEntity;

import java.util.List;

public interface JpaLegajoRepositoryCustom {

    List<LegajoEntity> findAllByStrings(List<String> conditions);

}
