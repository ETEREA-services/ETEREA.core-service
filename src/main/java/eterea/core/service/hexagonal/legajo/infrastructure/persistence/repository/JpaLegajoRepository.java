/**
 * 
 */
package eterea.core.service.hexagonal.legajo.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.legajo.infrastructure.persistence.entity.LegajoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author daniel
 *
 */
@Repository
public interface JpaLegajoRepository extends JpaRepository<LegajoEntity, Integer>, JpaLegajoRepositoryCustom {

    Optional<LegajoEntity> findByLegajoId(Integer legajoId);

}
