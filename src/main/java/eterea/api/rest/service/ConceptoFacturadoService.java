/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.ConceptoFacturado;
import eterea.api.rest.repository.IConceptoFacturadoRepository;

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
