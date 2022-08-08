/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.HotelNotFoundException;
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
		return repository.findAllByParadatraslado(paradatraslado, Sort.by("nombre").ascending());
	}

	public List<Hotel> findAllByPuntoEncuentro(Byte puntoencuentro) {
		return repository.findAllByPuntoencuentro(puntoencuentro, Sort.by("nombre").ascending());
	}

	public Hotel findById(Integer hotelId) {
		return repository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException(hotelId));
	}

	public Hotel update(Hotel newhotel, Integer hotelId) {
		return repository.findById(hotelId).map(hotel -> {
			hotel.setNombre(newhotel.getNombre());
			hotel.setExtras(newhotel.getExtras());
			hotel.setParadatraslado(newhotel.getParadatraslado());
			hotel.setPuntoencuentro(newhotel.getPuntoencuentro());
			return repository.save(hotel);
		}).orElseThrow(() -> new HotelNotFoundException(hotelId));
	}
}
