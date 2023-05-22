/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Hotel;

/**
 * @author daniel
 *
 */
@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer> {

	public List<Hotel> findAllByParadaTraslado(Byte paradaTraslado, Sort sort);

	public List<Hotel> findAllByPuntoEncuentro(Byte puntoEncuentro, Sort sort);
}
