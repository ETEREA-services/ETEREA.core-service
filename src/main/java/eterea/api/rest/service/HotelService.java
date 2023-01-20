/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.HotelException;
import eterea.api.rest.model.Hotel;
import eterea.api.rest.repository.IHotelRepository;

/**
 * @author daniel
 *
 */
@Service
public class HotelService {
	@Autowired
	private IHotelRepository repository;

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
