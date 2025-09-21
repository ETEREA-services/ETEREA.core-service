/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.HotelService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/hotel", "/hotel"})
@RequiredArgsConstructor
public class HotelController {

	private final HotelService service;

	@GetMapping("/")
	public ResponseEntity<List<Hotel>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> findByHotelId(@PathVariable Integer hotelId) {
		return new ResponseEntity<>(service.findByHotelId(hotelId), HttpStatus.OK);
	}

	@GetMapping("/paradatraslado/{paradatraslado}")
	public ResponseEntity<List<Hotel>> findAllByParadaTraslado(@PathVariable Byte paradatraslado) {
		return new ResponseEntity<>(service.findAllByParadaTraslado(paradatraslado), HttpStatus.OK);
	}

	@GetMapping("/puntoencuentro/{puntoencuentro}")
	public ResponseEntity<List<Hotel>> findAllByPuntoEncuentro(@PathVariable Byte puntoencuentro) {
		return new ResponseEntity<>(service.findAllByPuntoEncuentro(puntoencuentro), HttpStatus.OK);
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> update(@RequestBody Hotel hotel, @PathVariable Integer hotelId) {
		return new ResponseEntity<>(service.update(hotel, hotelId), HttpStatus.OK);
	}
	
}
