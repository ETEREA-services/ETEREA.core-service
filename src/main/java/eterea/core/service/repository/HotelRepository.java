/**
 *
 */
package eterea.core.service.repository;

import java.util.List;

import eterea.core.service.kotlin.model.Hotel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    List<Hotel> findAllByParadaTraslado(Byte paradaTraslado, Sort sort);

    List<Hotel> findAllByPuntoEncuentro(Byte puntoEncuentro, Sort sort);
}
