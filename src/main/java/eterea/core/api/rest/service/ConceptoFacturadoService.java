/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.exception.ConceptoFacturadoException;
import eterea.core.api.rest.kotlin.model.ConceptoFacturado;
import eterea.core.api.rest.kotlin.repository.IConceptoFacturadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ConceptoFacturadoService {

	private final IConceptoFacturadoRepository repository;

	@Autowired
	public ConceptoFacturadoService(IConceptoFacturadoRepository repository) {
		this.repository = repository;
	}

	public ConceptoFacturado findByArticuloMovimientoId(Long articuloMovimientoId) {
		return repository.findByArticuloMovimientoId(articuloMovimientoId).orElseThrow(() -> new ConceptoFacturadoException(articuloMovimientoId));
	}

	public ConceptoFacturado add(ConceptoFacturado conceptoFacturado) {
		return repository.save(conceptoFacturado);
	}

}
