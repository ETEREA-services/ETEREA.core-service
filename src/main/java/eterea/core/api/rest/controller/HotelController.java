/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.Hotel;
import eterea.core.api.rest.service.HotelService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> findAll() {
		return new ResponseEntity<List<Hotel>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{hotelID}")
	public ResponseEntity<Hotel> findById(@PathVariable("hotelID") Integer hotelId) {
		return new ResponseEntity<Hotel>(service.findById(hotelId), HttpStatus.OK);
	}

	@GetMapping("/paradatraslado/{paradatraslado}")
	public ResponseEntity<List<Hotel>> findAllByParadaTraslado(@PathVariable Byte paradatraslado) {
		return new ResponseEntity<List<Hotel>>(service.findAllByParadaTraslado(paradatraslado), HttpStatus.OK);
	}

	@GetMapping("/puntoencuentro/{puntoencuentro}")
	public ResponseEntity<List<Hotel>> findAllByPuntoEncuentro(@PathVariable Byte puntoencuentro) {
		return new ResponseEntity<List<Hotel>>(service.findAllByPuntoEncuentro(puntoencuentro), HttpStatus.OK);
	}
	
	@PutMapping("/{hotelID}")
	public ResponseEntity<Hotel> update(@RequestBody Hotel hotel, @PathVariable("hotelID") Integer hotelId) {
		return new ResponseEntity<Hotel>(service.update(hotel, hotelId), HttpStatus.OK);
	}
	
	@GetMapping("/update")
	public ResponseEntity<Hotel> updateByGet(@RequestParam("hotelID") Integer hotelId, @RequestParam("nombre") String nombre, @RequestParam("extras") Byte extras, @RequestParam("paradatraslado") Byte paradatraslado, @RequestParam("puntoencuentro") Byte puntoencuentro) {
		return update(new Hotel(hotelId, nombre, extras, paradatraslado, puntoencuentro), hotelId);
	}
}