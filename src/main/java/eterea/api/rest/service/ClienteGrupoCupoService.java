/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ClienteGrupoCupoNotFoundException;
import eterea.api.rest.model.ClienteGrupoCupo;
import eterea.api.rest.repository.IClienteGrupoCupoRepository;

/**
 * @author daniel
 *
 */
@Service
public class ClienteGrupoCupoService {
	@Autowired
	private IClienteGrupoCupoRepository repository;

	public List<ClienteGrupoCupo> findByClienteIdAndGrupoId(Long clienteId, Integer grupoId) {
		return repository.findByClienteIdAndGrupoId(clienteId, grupoId);
	}

	public ClienteGrupoCupo findByUnique(Long clienteId, Integer grupoId, Integer dias) {
		return repository.findByClienteIdAndGrupoIdAndDias(clienteId, grupoId, dias)
				.orElseThrow(() -> new ClienteGrupoCupoNotFoundException(clienteId, grupoId, dias));
	}

	public ClienteGrupoCupo findByAutonumerico(Long clientegrupocupoId) {
		return repository.findByClientegrupocupoId(clientegrupocupoId)
				.orElseThrow(() -> new ClienteGrupoCupoNotFoundException(clientegrupocupoId));
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
		}).orElseThrow(() -> new ClienteGrupoCupoNotFoundException(clientegrupocupoId));
	}

	@Transactional
	public void deleteByUnique(Long clienteId, Integer grupoId, Integer dias) {
		repository.deleteByClienteIdAndGrupoIdAndDias(clienteId, grupoId, dias);
	}
}
