/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.exception.HotelException;
import eterea.core.service.kotlin.model.Hotel;
import eterea.core.service.repository.HotelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HotelService {

	private final HotelRepository repository;

	public HotelService(HotelRepository repository) {
		this.repository = repository;
	}

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
