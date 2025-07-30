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

	public Hotel findByHotelId(Integer hotelId) {
		return repository.findByHotelId(hotelId).orElseThrow(() -> new HotelException(hotelId));
	}

	public Hotel update(Hotel newHotel, Integer hotelId) {
		return repository.findByHotelId(hotelId).map(hotel -> {
			hotel.setNombre(newHotel.getNombre());
			hotel.setExtras(newHotel.getExtras());
			hotel.setParadaTraslado(newHotel.getParadaTraslado());
			hotel.setPuntoEncuentro(newHotel.getPuntoEncuentro());
			return repository.save(hotel);
		}).orElseThrow(() -> new HotelException(hotelId));
	}

}
