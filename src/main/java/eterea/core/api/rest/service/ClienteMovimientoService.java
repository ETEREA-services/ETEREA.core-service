/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import eterea.core.api.rest.exception.ClienteMovimientoException;
import eterea.core.api.rest.repository.IClienteMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ClienteMovimiento;

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
		return repository.findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId, comprobanteIds);
	}

	public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
		return repository.findAllByReservaIdIn(reservaIds);
	}

	public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}

	public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findByClienteMovimientoId(clienteMovimientoId)
				.orElseThrow(() -> new ClienteMovimientoException(clienteMovimientoId));
	}

	public Long nextNumeroFactura(Integer puntoVenta, String letraComprobante) {
		return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc(0, puntoVenta,
				letraComprobante).map(clienteMovimiento -> {
					return 1 + clienteMovimiento.getNumeroComprobante();
				}).orElse(1L);
	}

}
