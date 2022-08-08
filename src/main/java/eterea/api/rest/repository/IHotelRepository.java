/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Hotel;

/**
 * @author daniel
 *
 */
@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer> {

	public List<Hotel> findAllByParadatraslado(Byte paradatraslado, Sort sort);

	public List<Hotel> findAllByPuntoencuentro(Byte puntoencuentro, Sort sort);
}
