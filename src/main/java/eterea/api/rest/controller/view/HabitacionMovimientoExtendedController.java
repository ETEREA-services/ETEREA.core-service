/**
 * 
 */
package eterea.api.rest.controller.view;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.model.view.HabitacionMovimientoExtended;
import eterea.api.rest.service.view.HabitacionMovimientoExtendedService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/habitacionmovimientoextended")
public class HabitacionMovimientoExtendedController {

	@Autowired
	private HabitacionMovimientoExtendedService service;

	@GetMapping("/periodo/{desde}/{hasta}")
	public ResponseEntity<List<HabitacionMovimientoExtended>> findAllByPeriodo(
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime desde,
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime hasta) {
		return new ResponseEntity<List<HabitacionMovimientoExtended>>(service.findAllByPeriodo(desde, hasta),
				HttpStatus.OK);
	}

	@GetMapping("/reserva/{numeroReserva}")
	public ResponseEntity<HabitacionMovimientoExtended> findByNumeroReserva(@PathVariable Long numeroReserva) {
		return new ResponseEntity<HabitacionMovimientoExtended>(service.findByNumeroReserva(numeroReserva),
				HttpStatus.OK);
	}

}
