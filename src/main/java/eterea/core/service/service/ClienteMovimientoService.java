/**
 * 
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import eterea.core.service.exception.ClienteMovimientoException;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.repository.ClienteMovimientoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class ClienteMovimientoService {

	private final ClienteMovimientoRepository repository;
	private final ComprobanteService comprobanteService;

	public ClienteMovimientoService(ClienteMovimientoRepository repository, ComprobanteService comprobanteService) {
		this.repository = repository;
		this.comprobanteService = comprobanteService;
	}

	public List<ClienteMovimiento> findTop200Asociables(Long clienteId, Integer comprobanteId) {
        var comprobante = comprobanteService.findByComprobanteId(comprobanteId);
        log.debug("Comprobante -> {}", comprobante.jsonify());
        List<Comprobante> comprobantes = (comprobante.getAsociado() == (byte) 1 && comprobante.getDebita() == (byte) 1) ? comprobanteService.findAllAsociables() : comprobanteService.findAllAsociables(comprobante.getDebita());
		List<Integer> comprobanteIds = comprobantes.stream()
				.map(Comprobante::getComprobanteId).collect(Collectors.toList());
		return repository.findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId, comprobanteIds);
	}

	public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
		return repository.findAllByReservaIdIn(reservaIds);
	}

	public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}

	public List<ClienteMovimiento> findAllFacturadosByFecha(OffsetDateTime fecha) {
		return repository.findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(fecha, 0, (byte) 1);
	}

	public List<ClienteMovimiento> findAllFacturasByRango(String letraComprobante, Byte debita, Integer puntoVenta, Long numeroComprobanteDesde, Long numeroComprobanteHasta) {
		return repository.findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(letraComprobante, (byte) 0, puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta, debita);
	}

	public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findByClienteMovimientoId(clienteMovimientoId)
				.orElseThrow(() -> new ClienteMovimientoException(clienteMovimientoId));
	}

	public ClienteMovimiento findByComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
		return repository.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante)
				.orElseThrow(() -> new ClienteMovimientoException(comprobanteId, puntoVenta, numeroComprobante));
	}

	public ClienteMovimiento findByFactura(
			String letraComprobante,
			Byte debita,
			Integer puntoVenta,
			Long numeroComprobante
	) {
		return repository.findByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteAndComprobanteDebita(
				letraComprobante,
				(byte) 0,
				puntoVenta,
				numeroComprobante,
				debita
		).orElseThrow(() -> new ClienteMovimientoException(letraComprobante, debita, puntoVenta, numeroComprobante));
	}

	public ClienteMovimiento add(ClienteMovimiento clienteMovimiento) {
		return repository.save(clienteMovimiento);
	}

	public ClienteMovimiento update(ClienteMovimiento newClienteMovimiento, Long clienteMovimientoId) {
		ClienteMovimiento clienteMovimiento = repository.findByClienteMovimientoId(clienteMovimientoId)
				.orElseThrow(() -> new ClienteMovimientoException(clienteMovimientoId));

		clienteMovimiento.setComprobanteId(newClienteMovimiento.getComprobanteId());
		clienteMovimiento.setPuntoVenta(newClienteMovimiento.getPuntoVenta());
		clienteMovimiento.setNumeroComprobante(newClienteMovimiento.getNumeroComprobante());
		clienteMovimiento.setFechaComprobante(newClienteMovimiento.getFechaComprobante());
		clienteMovimiento.setClienteId(newClienteMovimiento.getClienteId());
		clienteMovimiento.setFechaVencimiento(newClienteMovimiento.getFechaVencimiento());
		clienteMovimiento.setNegocioId(newClienteMovimiento.getNegocioId());
		clienteMovimiento.setEmpresaId(newClienteMovimiento.getEmpresaId());
		clienteMovimiento.setImporte(newClienteMovimiento.getImporte());
		clienteMovimiento.setCancelado(newClienteMovimiento.getCancelado());
		clienteMovimiento.setNeto(newClienteMovimiento.getNeto());
		clienteMovimiento.setNetoCancelado(newClienteMovimiento.getNetoCancelado());
		clienteMovimiento.setMontoIva(newClienteMovimiento.getMontoIva());
		clienteMovimiento.setMontoIvaRni(newClienteMovimiento.getMontoIvaRni());
		clienteMovimiento.setReintegroTurista(newClienteMovimiento.getReintegroTurista());
		clienteMovimiento.setFechaContable(newClienteMovimiento.getFechaContable());
		clienteMovimiento.setOrdenContable(newClienteMovimiento.getOrdenContable());
		clienteMovimiento.setRecibo(newClienteMovimiento.getRecibo());
		clienteMovimiento.setAsignado(newClienteMovimiento.getAsignado());
		clienteMovimiento.setAnulada(newClienteMovimiento.getAnulada());
		clienteMovimiento.setDecreto104316(newClienteMovimiento.getDecreto104316());
		clienteMovimiento.setLetraComprobante(newClienteMovimiento.getLetraComprobante());
		clienteMovimiento.setMontoExento(newClienteMovimiento.getMontoExento());
		clienteMovimiento.setReservaId(newClienteMovimiento.getReservaId());
		clienteMovimiento.setMontoCuentaCorriente(newClienteMovimiento.getMontoCuentaCorriente());
		clienteMovimiento.setCierreCajaId(newClienteMovimiento.getCierreCajaId());
		clienteMovimiento.setCierreRestaurantId(newClienteMovimiento.getCierreRestaurantId());
		clienteMovimiento.setNivel(newClienteMovimiento.getNivel());
		clienteMovimiento.setEliminar(newClienteMovimiento.getEliminar());
		clienteMovimiento.setCuentaCorriente(newClienteMovimiento.getCuentaCorriente());
		clienteMovimiento.setLetras(newClienteMovimiento.getLetras());
		clienteMovimiento.setCae(newClienteMovimiento.getCae());
		clienteMovimiento.setCaeVencimiento(newClienteMovimiento.getCaeVencimiento());
		clienteMovimiento.setCodigoBarras(newClienteMovimiento.getCodigoBarras());
		clienteMovimiento.setParticipacion(newClienteMovimiento.getParticipacion());
		clienteMovimiento.setMonedaId(newClienteMovimiento.getMonedaId());
		clienteMovimiento.setCotizacion(newClienteMovimiento.getCotizacion());
		clienteMovimiento.setObservaciones(newClienteMovimiento.getObservaciones());
		clienteMovimiento.setClienteMovimientoIdSlave(newClienteMovimiento.getClienteMovimientoIdSlave());
		clienteMovimiento.setTrackUuid(newClienteMovimiento.getTrackUuid());

		return repository.save(clienteMovimiento);
	}

	public Long nextNumeroFactura(Integer puntoVenta, String letraComprobante) {
		return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc((byte) 0, puntoVenta,
				letraComprobante).map(clienteMovimiento -> {
					return 1 + clienteMovimiento.getNumeroComprobante();
				}).orElse(1L);
	}

	@Transactional
    public void deleteAll0ByFecha(OffsetDateTime fecha) {
		repository.deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(fecha, 0, 0, 0L);
    }

}
