/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eterea.api.rest.model.Moneda;

/**
 * @author daniel
 *
 */
public interface IMonedaRepository extends JpaRepository<Moneda, Integer> {

	public Optional<Moneda> findByMonedaId(Integer monedaId);

}
