/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ConceptoFacturadoException;
import eterea.core.service.kotlin.model.ConceptoFacturado;
import eterea.core.service.kotlin.repository.ConceptoFacturadoRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author daniel
 *
 */
@Service
public class ConceptoFacturadoService {

	private final ConceptoFacturadoRepository repository;

	public ConceptoFacturadoService(ConceptoFacturadoRepository repository) {
		this.repository = repository;
	}

	public ConceptoFacturado findByArticuloMovimientoId(Long articuloMovimientoId) {
		return Objects.requireNonNull(repository.findTopByArticuloMovimientoId(articuloMovimientoId)).orElseThrow(() -> new ConceptoFacturadoException(articuloMovimientoId));
	}

	public ConceptoFacturado add(ConceptoFacturado conceptoFacturado) {
		return repository.save(conceptoFacturado);
	}

}
