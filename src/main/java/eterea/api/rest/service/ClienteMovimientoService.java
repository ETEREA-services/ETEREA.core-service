/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ClienteMovimientoNotFoundException;
import eterea.api.rest.model.ClienteMovimiento;
import eterea.api.rest.repository.IClienteMovimientoRepository;

/**
 * @author daniel
 *
 */
@Service
public class ClienteMovimientoService {

	@Autowired
	private IClienteMovimientoRepository repository;

	@Autowired
	private ComprobanteService comprobanteService;

	public List<ClienteMovimiento> findTop200Asociables(Long clienteId) {
		List<Integer> comprobanteIds = comprobanteService.findAllAsociables().stream()
				.map(comprobante -> comprobante.getComprobanteId()).collect(Collectors.toList());
		return repository.findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId,
				comprobanteIds);
	}

	public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
		return repository.findAllByReservaIdIn(reservaIds);
	}

	public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}

	public Long nextNumeroFactura(Integer puntoVenta, String letraComprobante) {
		return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc((byte) 0,
				puntoVenta, letraComprobante).map(clienteMovimiento -> {
					return 1 + clienteMovimiento.getNumeroComprobante();
				}).orElse(1L);
	}

	public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findByClienteMovimientoId(clienteMovimientoId)
				.orElseThrow(() -> new ClienteMovimientoNotFoundException(clienteMovimientoId));
	}

	public ClienteMovimiento findByComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
		return repository
				.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante)
				.orElseThrow(
						() -> new ClienteMovimientoNotFoundException(comprobanteId, puntoVenta, numeroComprobante));
	}

}
