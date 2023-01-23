/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.PuntoVentaException;
import eterea.api.rest.model.PuntoVenta;
import eterea.api.rest.repository.IPuntoVentaRepository;

/**
 * @author daniel
 *
 */
@Service
public class PuntoVentaService {

	@Autowired
	private IPuntoVentaRepository repository;

	public PuntoVenta findByNumero(Integer numero) {
		return repository.findByNumero(numero).orElseThrow(() -> new PuntoVentaException(numero));
	}

}
