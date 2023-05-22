/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ClienteInternetException;
import eterea.core.api.rest.repository.IAuthoritiesRepository;
import eterea.core.api.rest.repository.IClienteInternetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Authorities;
import eterea.core.api.rest.model.ClienteInternet;

/**
 * @author daniel
 *
 */
@Service
public class ClienteInternetService {
	@Autowired
	private IClienteInternetRepository repository;

	@Autowired
	private IAuthoritiesRepository authoritiesrepository;

	public ClienteInternet findById(Long clienteId) {
		return repository.findById(clienteId).orElseThrow(() -> new ClienteInternetException(clienteId));
	}

	public ClienteInternet add(ClienteInternet clienteinternet) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		clienteinternet.setPassword(encoder.encode(clienteinternet.getPassword()));
		repository.save(clienteinternet);
		this.addAuthorities(clienteinternet.getClienteId());
		return clienteinternet;
	}

	public void delete(Long clienteId) {
		repository.deleteById(clienteId);
	}

	public ClienteInternet update(ClienteInternet clienteinternet, Long clienteId) {
		for (Authorities a : authoritiesrepository.findAllByClienteId(clienteId))
			authoritiesrepository.deleteById(a.getId());
		delete(clienteId);
		return add(clienteinternet);
	}

	private void addAuthorities(Long clienteId) {
		authoritiesrepository.save(new Authorities(null, clienteId, "ROLE_USER"));
		authoritiesrepository.save(new Authorities(null, clienteId, "ROLE_ADMIN"));
	}
}
