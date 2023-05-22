/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ElectronicoException;
import eterea.core.api.rest.repository.IElectronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Electronico;

/**
 * @author daniel
 *
 */
@Service
public class ElectronicoService {

	@Autowired
	private IElectronicoRepository repository;

	public Electronico findByUnique(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
		return repository
				.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante)
				.orElseThrow(() -> new ElectronicoException(comprobanteId, puntoVenta, numeroComprobante));
	}

}
