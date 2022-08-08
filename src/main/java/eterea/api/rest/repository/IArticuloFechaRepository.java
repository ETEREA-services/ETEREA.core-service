/**
 * 
 */
package eterea.api.rest.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ArticuloFecha;

/**
 * @author daniel
 *
 */
@Repository
public interface IArticuloFechaRepository extends JpaRepository<ArticuloFecha, Long> {

	public Optional<ArticuloFecha> findByArticuloIdAndFecha(String articuloId, OffsetDateTime fecha);

	public Optional<ArticuloFecha> findByArticulofechaId(Long articulofechaId);

}
