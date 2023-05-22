/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.repository.IConceptoFacturadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ConceptoFacturado;

/**
 * @author daniel
 *
 */
@Service
public class ConceptoFacturadoService {

	@Autowired
	private IConceptoFacturadoRepository repository;

	public ConceptoFacturado add(ConceptoFacturado conceptoFacturado) {
		conceptoFacturado = repository.save(conceptoFacturado);
		return conceptoFacturado;
	}

}
