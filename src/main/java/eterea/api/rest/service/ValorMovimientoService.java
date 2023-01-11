/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.ValorMovimiento;
import eterea.api.rest.repository.IValorMovimientoRepository;

/**
 * @author daniel
 *
 */
@Service
public class ValorMovimientoService {

	@Autowired
	private IValorMovimientoRepository repository;

	public List<ValorMovimiento> findAllbyClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findAllByClienteMovimientoId(clienteMovimientoId);
	}

}
