/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.exception.ClienteGrupoCupoException;
import eterea.core.service.kotlin.model.ClienteGrupoCupo;
import eterea.core.service.repository.ClienteGrupoCupoRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ClienteGrupoCupoService {

	private final ClienteGrupoCupoRepository repository;

	public ClienteGrupoCupoService(ClienteGrupoCupoRepository repository) {
		this.repository = repository;
	}

	public List<ClienteGrupoCupo> findByClienteIdAndGrupoId(Long clienteId, Integer grupoId) {
		return repository.findByClienteIdAndGrupoId(clienteId, grupoId);
	}

	public ClienteGrupoCupo findByUnique(Long clienteId, Integer grupoId, Integer dias) {
		return repository.findByClienteIdAndGrupoIdAndDias(clienteId, grupoId, dias)
				.orElseThrow(() -> new ClienteGrupoCupoException(clienteId, grupoId, dias));
	}

	public ClienteGrupoCupo findByAutonumerico(Long clientegrupocupoId) {
		return repository.findByClientegrupocupoId(clientegrupocupoId)
				.orElseThrow(() -> new ClienteGrupoCupoException(clientegrupocupoId));
	}

	public ClienteGrupoCupo add(ClienteGrupoCupo clientegrupocupo) {
		repository.save(clientegrupocupo);
		return clientegrupocupo;
	}

	public ClienteGrupoCupo update(ClienteGrupoCupo newclientegrupocupo, Long clientegrupocupoId) {
		return repository.findByClientegrupocupoId(clientegrupocupoId).map(clientegrupocupo -> {
			clientegrupocupo.setCantidad(newclientegrupocupo.getCantidad());
			repository.save(clientegrupocupo);
			return clientegrupocupo;
		}).orElseThrow(() -> new ClienteGrupoCupoException(clientegrupocupoId));
	}

	@Transactional
	public void deleteByUnique(Long clienteId, Integer grupoId, Integer dias) {
		repository.deleteByClienteIdAndGrupoIdAndDias(clienteId, grupoId, dias);
	}
}
