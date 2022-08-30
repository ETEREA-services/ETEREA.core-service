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

	public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findByClienteMovimientoId(clienteMovimientoId)
				.orElseThrow(() -> new ClienteMovimientoNotFoundException(clienteMovimientoId));
	}

}
