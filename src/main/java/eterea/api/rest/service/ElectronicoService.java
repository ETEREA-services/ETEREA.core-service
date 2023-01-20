/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ElectronicoException;
import eterea.api.rest.model.Electronico;
import eterea.api.rest.repository.IElectronicoRepository;

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
