/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.HotelException;
import eterea.core.api.rest.kotlin.model.Hotel;
import eterea.core.api.rest.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HotelService {
	@Autowired
	private HotelRepository repository;

	public List<Hotel> findAll() {
		return repository.findAll();
	}

	public List<Hotel> findAllByParadaTraslado(Byte paradatraslado) {
		return repository.findAllByParadaTraslado(paradatraslado, Sort.by("nombre").ascending());
	}

	public List<Hotel> findAllByPuntoEncuentro(Byte puntoencuentro) {
		return repository.findAllByPuntoEncuentro(puntoencuentro, Sort.by("nombre").ascending());
	}

	public Hotel findById(Integer hotelId) {
		return repository.findById(hotelId).orElseThrow(() -> new HotelException(hotelId));
	}

	public Hotel update(Hotel newhotel, Integer hotelId) {
		return repository.findById(hotelId).map(hotel -> {
			hotel.setNombre(newhotel.getNombre());
			hotel.setExtras(newhotel.getExtras());
			hotel.setParadaTraslado(newhotel.getParadaTraslado());
			hotel.setPuntoEncuentro(newhotel.getPuntoEncuentro());
			return repository.save(hotel);
		}).orElseThrow(() -> new HotelException(hotelId));
	}
}
