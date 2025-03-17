/**
 * 
 */
package eterea.core.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.exception.ComprobanteException;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.repository.ComprobanteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class ComprobanteService {

	private final ComprobanteRepository repository;

	public ComprobanteService(ComprobanteRepository repository) {
		this.repository = repository;
	}

	public List<Comprobante> findAllAsociables() {
		return repository.findAllByModuloAndDebitaAndAsociado(3, (byte) 1, (byte) 0);
	}

	public List<Integer> findAllDisponibles() {
		Comprobante firstComprobante = repository.findFirstByOrderByComprobanteId().orElseThrow(ComprobanteException::new);
        try {
            log.debug("First comprobante: {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(firstComprobante));
        } catch (JsonProcessingException e) {
            log.debug("First comprobante error: {}", e.getMessage());
        }
        Comprobante lastComprobante = repository.findFirstByOrderByComprobanteIdDesc().orElseThrow(ComprobanteException::new);
		try {
			log.debug("Last comprobante: {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(lastComprobante));
		} catch (JsonProcessingException e) {
			log.debug("Last comprobante error: {}", e.getMessage());
		}
		var usados = repository.findAll().stream().collect(Collectors.toMap(Comprobante::getComprobanteId, comprobante -> comprobante));
		List<Integer> disponibles = new ArrayList<>();
		for (Integer comprobanteId = firstComprobante.getComprobanteId(); comprobanteId <= lastComprobante.getComprobanteId(); comprobanteId++) {
			if (!usados.containsKey(comprobanteId)) {
				disponibles.add(comprobanteId);
			}
		}
		return disponibles;
	}

	public List<Comprobante> findAllByModulo(Integer modulo, Byte debita, Integer comprobanteId) {
//		debita < 2 && comprobanteId > 0
		if (debita < 2 && comprobanteId > 0) {
			return repository.findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(modulo, (byte) 0,
					(byte) 0, debita, comprobanteId);
		}
//		debita < 2 && comprobanteId = 0
		if (debita < 2) {
			return repository.findAllByModuloAndTransferirAndInvisibleAndDebita(modulo, (byte) 0, (byte) 0, (byte) 0);
		}
//		debita = 2 && comprobanteId > 0
		if (comprobanteId > 0) {
			return repository.findAllByModuloAndTransferirAndInvisibleAndComprobanteId(modulo, (byte) 0, (byte) 0,
					comprobanteId);
		}
//		debita = 2 && comprobanteId = 0
		return repository.findAllByModuloAndTransferirAndInvisible(modulo, (byte) 0, (byte) 0);
	}

	public Comprobante findByComprobanteId(Integer comprobanteId) {
		return repository.findByComprobanteId(comprobanteId)
				.orElseThrow(() -> new ComprobanteException(comprobanteId));
	}

	public Comprobante findByLetraComprobante(String letraComprobante) {
		return repository.findByLetraComprobante(letraComprobante)
			.orElseThrow(() -> new ComprobanteException(letraComprobante));
	}

}
