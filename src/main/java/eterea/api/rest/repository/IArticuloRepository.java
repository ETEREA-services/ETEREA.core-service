/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eterea.api.rest.model.Articulo;

/**
 * @author daniel
 *
 */
public interface IArticuloRepository extends JpaRepository<Articulo, String> {

	public List<Articulo> findTop50ByDescripcionLikeOrderByDescripcion(String descripcion);

	public Optional<Articulo> findByAutoNumericoId(Long autoNumericoId);

	public Optional<Articulo> findByArticuloId(String articuloId);
}
