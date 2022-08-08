/**
 * 
 */
package eterea.api.rest.service.view;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.view.HabitacionMovimientoExtendedNotFoundException;
import eterea.api.rest.model.view.HabitacionMovimientoExtended;
import eterea.api.rest.repository.view.IHabitacionMovimientoExtendedRepository;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionMovimientoExtendedService {

	@Autowired
	private IHabitacionMovimientoExtendedRepository repository;

	public List<HabitacionMovimientoExtended> findAllByPeriodo(OffsetDateTime desde, OffsetDateTime hasta) {
		Map<Long, HabitacionMovimientoExtended> ingresos = repository.findAllByFechaIngresoBetween(desde, hasta)
				.stream().collect(Collectors.toMap(HabitacionMovimientoExtended::getHabitacionMovimientoId,
						movimiento -> movimiento));
		Map<Long, HabitacionMovimientoExtended> salidas = repository.findAllByFechaSalidaBetween(desde, hasta).stream()
				.collect(Collectors.toMap(HabitacionMovimientoExtended::getHabitacionMovimientoId,
						movimiento -> movimiento));
		Map<Long, HabitacionMovimientoExtended> orillas = repository
				.findAllByFechaIngresoLessThanEqualAndFechaSalidaGreaterThanEqual(desde, hasta).stream()
				.collect(Collectors.toMap(HabitacionMovimientoExtended::getHabitacionMovimientoId,
						movimiento -> movimiento));
		Map<Long, HabitacionMovimientoExtended> reservadas = new HashMap<Long, HabitacionMovimientoExtended>(ingresos);
		salidas.forEach((key, value) -> reservadas.merge(key, value, (movimiento1, movimiento2) -> movimiento1));
		orillas.forEach((key, value) -> reservadas.merge(key, value, (movimiento1, movimiento2) -> movimiento1));
		return new ArrayList<HabitacionMovimientoExtended>(reservadas.values());
	}

	public HabitacionMovimientoExtended findByNumeroReserva(Long numeroReserva) {
		return repository.findByNumeroReserva(numeroReserva)
				.orElseThrow(() -> new HabitacionMovimientoExtendedNotFoundException(numeroReserva));
	}

}
