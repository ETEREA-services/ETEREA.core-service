/**
 * 
 */
package eterea.api.rest.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.LegajoRegistro;

/**
 * @author daniel
 *
 */
@Repository
public interface ILegajoRegistroRepository extends JpaRepository<LegajoRegistro, Long> {

	public List<LegajoRegistro> findAllByLegajoId(Integer legajoId);

	@Query(nativeQuery = true, value = "SELECT * FROM legajoregistro l WHERE l.lre_leg_id = :legajoId ORDER BY l.lre_fecha DESC, l.lre_hora DESC LIMIT 1")
	public Optional<LegajoRegistro> findLastByLegajoId(@Param("legajoId") Integer legajoId);

	@Query(nativeQuery = true, value = "SELECT * FROM legajoregistro l WHERE l.lre_leg_id = :legajoId AND l.lre_fecha = :fecha AND l.lre_hora < :hora ORDER BY l.lre_hora DESC LIMIT 1")
	public Optional<LegajoRegistro> findLastByLegajoIdAndFecha(Integer legajoId, Date fecha, Time hora);

}
