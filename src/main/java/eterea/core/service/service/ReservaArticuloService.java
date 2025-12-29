/**
 * 
 */
package eterea.core.service.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import eterea.core.service.exception.ArticuloException;
import eterea.core.service.exception.ArticuloFechaException;
import eterea.core.service.kotlin.exception.ReservaArticuloException;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.ArticuloFecha;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ReservaArticulo;
import eterea.core.service.kotlin.model.ReservaArticuloEliminado;
import eterea.core.service.kotlin.repository.ReservaArticuloRepository;
import eterea.core.service.model.dto.reserva.ArticuloFechasDto;
import eterea.core.service.service.facade.PrecioService;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ReservaArticuloService {
	private final Logger log = LoggerFactory.getLogger(ReservaArticuloService.class);

	private final ReservaArticuloRepository repository;
	private final ArticuloService articuloService;
	private final PrecioService precioService;
	private final ArticuloFechaService articuloFechaService;
	private final ReservaArticuloEliminadoService reservaArticuloEliminadoService;

	public ReservaArticuloService(ReservaArticuloRepository repository, ArticuloService articuloService,
			PrecioService precioService, ArticuloFechaService articuloFechaService,
			ReservaArticuloEliminadoService reservaArticuloEliminadoService) {
		this.articuloService = articuloService;
		this.repository = repository;
		this.precioService = precioService;
		this.articuloFechaService = articuloFechaService;
		this.reservaArticuloEliminadoService = reservaArticuloEliminadoService;
	}

	public List<ReservaArticulo> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}

	public List<ReservaArticulo> findAllByVoucherId(Long reservaId, Long voucherId) {
		return repository.findAllByReservaIdAndVoucherId(reservaId, voucherId);
	}

	@Transactional
	public void deleteByReservaArticuloId(Long reservaArticuloId) {
		repository.deleteByReservaArticuloId(reservaArticuloId);
	}

	public List<ReservaArticulo> saveAll(List<ReservaArticulo> reservaArticulos) {
		return repository.saveAll(reservaArticulos);
	}

	public ReservaArticulo add(ReservaArticulo reservaArticulo) {
		return repository.save(reservaArticulo);
	}

	public List<ReservaArticulo> createAll(Reserva reserva, List<ReservaArticulo> reservaArticulos,
			Map<String, List<ArticuloFechasDto>> articuloFechasMap) {
		List<String> articulosIds = reservaArticulos.stream()
				.map(ReservaArticulo::getArticuloId)
				.toList();

		List<Articulo> articulos = articuloService.findAllByIds(articulosIds);

		reservaArticulos.forEach(ra -> {
			Articulo articulo = articulos.stream()
					.filter(a -> a.getArticuloId().equals(ra.getArticuloId()))
					.findFirst()
					.orElseThrow(() -> new ArticuloException(ra.getArticuloId()));

			List<ArticuloFechasDto> fechasList = articuloFechasMap.get(ra.getArticuloId());

			BigDecimal precioUnitarioSinComision = calculateTotalWeightedPrecio(ra, fechasList,
					reserva.getFechaInServicio(), reserva.getFechaOutServicio());
			ra.setPrecioUnitarioSinComision(precioUnitarioSinComision);

			ra.setArticulo(articulo);
			ra.setArticuloId(articulo.getArticuloId());
			ra.setReservaId(reserva.getReservaId());
			ra.setNegocioId(reserva.getNegocioId());

			BigDecimal complemento = BigDecimal.ONE.subtract(ra.getComision()).setScale(2, RoundingMode.HALF_UP);
			ra.setPrecioUnitario(precioUnitarioSinComision.multiply(complemento).setScale(2, RoundingMode.HALF_UP));
		});
		return saveAll(reservaArticulos);
	}

	public List<ReservaArticulo> updateAll(Reserva reserva, List<ReservaArticulo> existingReservaArticulos,
			List<ReservaArticulo> newReservaArticulos, Map<String, List<ArticuloFechasDto>> articuloFechasMap) {

		log.debug("newReservaArticulos: {}", newReservaArticulos);
		List<ReservaArticulo> raToDelete = existingReservaArticulos.stream()
				.filter(existingRa -> {
					var matchingUpdate = newReservaArticulos.stream()
							.filter(newRa -> newRa.getReservaArticuloId() != null &&
									newRa.getReservaArticuloId().equals(existingRa.getReservaArticuloId()))
							.findFirst();
					// Delete if:
					// 1. Not found in update request (complete deletion)
					// 2. Found but articuloId changed (article replacement)
					return matchingUpdate.isEmpty()
							|| !matchingUpdate.get().getArticuloId().equals(existingRa.getArticuloId());
				})
				.toList();

		log.debug("raToDelete: {}", raToDelete);

		// Create audit records and delete articles
		for (ReservaArticulo ra : raToDelete) {
			ReservaArticuloEliminado auditRecord = new ReservaArticuloEliminado.Builder()
					.negocioId(ra.getNegocioId())
					.reservaId(ra.getReservaId())
					.voucherId(ra.getVoucherId() != null ? ra.getVoucherId() : 0L)
					.articuloId(ra.getArticuloId())
					.cantidad(ra.getCantidad())
					.comision(ra.getComision())
					.precioUnitarioSinComision(ra.getPrecioUnitarioSinComision())
					.precioUnitario(ra.getPrecioUnitario())
					.precioCompra(ra.getPrecioCompra())
					.observaciones(ra.getObservaciones())
					.reservaArticuloId(ra.getReservaArticuloId())
					.build();

			reservaArticuloEliminadoService.create(auditRecord);
			deleteByReservaArticuloId(ra.getReservaArticuloId());
		}

		Set<Long> deletedIds = raToDelete.stream()
				.map(ReservaArticulo::getReservaArticuloId)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		List<ReservaArticulo> raToUpdate = new ArrayList<>();
		List<ReservaArticulo> raToCreate = new ArrayList<>();

		for (ReservaArticulo ra : newReservaArticulos) {
			boolean wasDeleted = ra.getReservaArticuloId() != null && deletedIds.contains(ra.getReservaArticuloId());

			if (ra.getReservaArticuloId() != null && !wasDeleted) {
				raToUpdate.add(ra);
			} else {
				raToCreate.add(ra);
			}
		}

		List<String> articulosIds = raToUpdate.stream()
				.map(ReservaArticulo::getArticuloId)
				.toList();
		List<Articulo> articulos = articuloService.findAllByIds(articulosIds);

		List<ReservaArticulo> updatedRa = raToUpdate.stream()
				.map(ra -> {
					Articulo articulo = articulos.stream()
							.filter(a -> a.getArticuloId().equals(ra.getArticuloId()))
							.findFirst()
							.orElseThrow(() -> new ArticuloException(ra.getArticuloId()));

					List<ArticuloFechasDto> fechasList = articuloFechasMap.get(ra.getArticuloId());

					BigDecimal precioUnitarioSinComision = calculateTotalWeightedPrecio(ra, fechasList,
							reserva.getFechaInServicio(), reserva.getFechaOutServicio());
					ra.setPrecioUnitarioSinComision(precioUnitarioSinComision);
					ra.setArticulo(articulo);
					ra.setArticuloId(articulo.getArticuloId());

					BigDecimal complemento = BigDecimal.ONE.subtract(ra.getComision()).setScale(2, RoundingMode.HALF_UP);
					ra.setPrecioUnitario(precioUnitarioSinComision.multiply(complemento).setScale(2, RoundingMode.HALF_UP));

					return update(ra, ra.getReservaArticuloId());
				})
				.toList();

		List<ReservaArticulo> createdRa = raToCreate.isEmpty()
				? List.of()
				: createAll(reserva, raToCreate, articuloFechasMap);

		return Stream.concat(updatedRa.stream(), createdRa.stream()).toList();
	}

	public ReservaArticulo update(ReservaArticulo newReservaArticulo, Long reservaArticuloId) {
		return repository.findByReservaArticuloId(reservaArticuloId).map(reservaArticulo -> {
			reservaArticulo = new ReservaArticulo.Builder()
					.reservaArticuloId(reservaArticuloId)
					.negocioId(newReservaArticulo.getNegocioId())
					.reservaId(newReservaArticulo.getReservaId())
					.voucherId(newReservaArticulo.getVoucherId())
					.articuloId(newReservaArticulo.getArticuloId())
					.cantidad(newReservaArticulo.getCantidad())
					.comision(newReservaArticulo.getComision())
					.precioUnitarioSinComision(newReservaArticulo.getPrecioUnitarioSinComision())
					.precioUnitario(newReservaArticulo.getPrecioUnitario())
					.precioCompra(newReservaArticulo.getPrecioCompra())
					.observaciones(newReservaArticulo.getObservaciones())
					.build();
			return repository.save(reservaArticulo);
		}).orElseThrow(() -> new ReservaArticuloException(reservaArticuloId));
	}

	public boolean equals(List<ReservaArticulo> reservaArticulos1, List<ReservaArticulo> reservaArticulos2) {
		if (reservaArticulos1.size() != reservaArticulos2.size()) {
			return false;
		}

		List<ReservaArticulo> sorted1 = reservaArticulos1.stream()
				.sorted(Comparator.comparing(ReservaArticulo::getArticuloId))
				.toList();

		List<ReservaArticulo> sorted2 = reservaArticulos2.stream()
				.sorted(Comparator.comparing(ReservaArticulo::getArticuloId))
				.toList();

		for (int i = 0; i < sorted1.size(); i++) {
			ReservaArticulo ra1 = sorted1.get(i);
			ReservaArticulo ra2 = sorted2.get(i);

			if (!ra1.getArticuloId().equals(ra2.getArticuloId()) ||
					ra1.getCantidad() != ra2.getCantidad() ||
					ra1.getComision().compareTo(ra2.getComision()) != 0 ||
					ra1.getPrecioUnitarioSinComision().compareTo(ra2.getPrecioUnitarioSinComision()) != 0 ||
					ra1.getPrecioUnitario().compareTo(ra2.getPrecioUnitario()) != 0) {
				return false;
			}
		}
		return true;
	}

	private BigDecimal calculatePrecio(ReservaArticulo ra, OffsetDateTime fechaIngreso, OffsetDateTime fechaSalida) {
		BigDecimal precioArticulo = (fechaSalida == null || fechaSalida.isEqual(fechaIngreso))
				? precioService.getUnitPriceByArticuloIdAndFecha(ra.getArticuloId(), fechaIngreso)
				: calculateAveragePerNight(ra, fechaIngreso, fechaSalida);
		return precioArticulo;
	}

	private BigDecimal calculateAveragePerNight(ReservaArticulo ra, OffsetDateTime fechaIngreso,
			OffsetDateTime fechaSalida) {
		OffsetDateTime fechaSalidaMinus1 = fechaSalida.minusDays(1);
		int noches = (int) ChronoUnit.DAYS.between(fechaIngreso, fechaSalida);

		List<ArticuloFecha> articuloFechas = articuloFechaService.findAllByArticuloIdAndPeriodo(ra.getArticuloId(),
				fechaIngreso, fechaSalidaMinus1);
		if (articuloFechas.isEmpty()) {
			throw new ArticuloFechaException(
					ra.getArticuloId(), fechaIngreso, fechaSalida);
		}

		BigDecimal precioArticuloAvg = articuloFechas.stream()
				.map(ArticuloFecha::getPrecioArs)
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(BigDecimal.valueOf(noches), 2, RoundingMode.HALF_UP);

		return precioArticuloAvg;
	}

	private BigDecimal calculateTotalWeightedPrecio(
			ReservaArticulo ra,
			List<ArticuloFechasDto> fechasList,
			OffsetDateTime fallbackIngreso,
			OffsetDateTime fallbackSalida) {

		// If no room dates, use fallback (reservation dates)
		if (fechasList == null || fechasList.isEmpty()) {
			return calculatePrecio(ra, fallbackIngreso, fallbackSalida);
		}

		// Single date range - use existing logic
		if (fechasList.size() == 1) {
			ArticuloFechasDto fechas = fechasList.get(0);
			return calculatePrecio(ra, fechas.fechaIngreso(), fechas.fechaSalida());
		}

		// Multiple date ranges - weighted average
		BigDecimal totalValue = BigDecimal.ZERO;
		long totalNights = 0;

		for (ArticuloFechasDto fechas : fechasList) {
			long nights = ChronoUnit.DAYS.between(fechas.fechaIngreso(), fechas.fechaSalida());
			if (nights <= 0)
				nights = 1; // Minimum 1 night

			BigDecimal priceForRange = calculatePrecio(ra, fechas.fechaIngreso(), fechas.fechaSalida());
			totalValue = totalValue.add(priceForRange.multiply(BigDecimal.valueOf(nights)));
			totalNights += nights;
		}

		if (totalNights == 0) {
			return calculatePrecio(ra, fallbackIngreso, fallbackSalida);
		}

		return totalValue.divide(BigDecimal.valueOf(totalNights), 2, RoundingMode.HALF_UP);
	}

}
