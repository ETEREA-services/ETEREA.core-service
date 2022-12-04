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

	public List<ClienteMovimiento> findAllAsociables(Long clienteId) {
		List<Integer> comprobanteIds = comprobanteService.findAllAsociables().stream()
				.map(comprobante -> comprobante.getComprobanteId()).collect(Collectors.toList());
		return repository.findAllByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId, comprobanteIds);
	}

	public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
		return repository.findAllByReservaIdIn(reservaIds);
	}

	public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}

	public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findByClienteMovimientoId(clienteMovimientoId)
				.orElseThrow(() -> new ClienteMovimientoNotFoundException(clienteMovimientoId));
	}

	public Long nextNumeroFactura(Integer puntoVenta, String letraComprobante) {
		return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc(0, puntoVenta,
				letraComprobante).map(clienteMovimiento -> {
					return 1 + clienteMovimiento.getNumeroComprobante();
				}).orElse(1L);
	}

}
