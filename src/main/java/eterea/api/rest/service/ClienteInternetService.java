/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ClienteInternetNotFoundException;
import eterea.api.rest.model.Authorities;
import eterea.api.rest.model.ClienteInternet;
import eterea.api.rest.repository.IAuthoritiesRepository;
import eterea.api.rest.repository.IClienteInternetRepository;

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
		return repository.findById(clienteId).orElseThrow(() -> new ClienteInternetNotFoundException(clienteId));
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
