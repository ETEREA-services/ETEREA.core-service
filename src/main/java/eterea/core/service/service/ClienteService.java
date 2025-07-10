/**
 * 
 */
package eterea.core.service.service;

import java.util.List;
import java.util.Optional;

import eterea.core.service.exception.ClienteException;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.view.ClienteSearch;
import eterea.core.service.model.PosicionIva;
import eterea.core.service.repository.ClienteRepository;
import eterea.core.service.service.view.ClienteSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class ClienteService {

	private final ClienteRepository repository;
	private final ClienteSearchService clienteSearchService;
	private final PosicionIvaService posicionIvaService;

	public ClienteService(ClienteRepository repository, ClienteSearchService clienteSearchService,
			PosicionIvaService posicionIvaService) {
		this.repository = repository;
		this.clienteSearchService = clienteSearchService;
		this.posicionIvaService = posicionIvaService;
	}

	public List<ClienteSearch> findAllBySearch(String search) {
		return clienteSearchService.findAllBySearch(search);
	}

	public Cliente findByClienteId(Long clienteId) {
		return repository.findByClienteId(clienteId).orElseThrow(() -> new ClienteException(clienteId));
	}

	public Cliente findByNumeroDocumento(String numeroDocumento) {
		log.debug("Processing findByNumeroDocumento");
		return repository.findTopByNumeroDocumento(numeroDocumento)
				.orElseThrow(() -> new ClienteException(numeroDocumento));
	}

	public Cliente findLast() {
		return repository.findTopByOrderByClienteIdDesc().orElseThrow(ClienteException::new);
	}

	public Cliente add(Cliente cliente) {
		return repository.save(cliente);
	}

	// NUEVO METODO PARA BUSCAR CLIENTES CON "LIKE"
	// TODO: Agregar reemplazo de caracteres especiales
	public List<Cliente> buscar(String searchTerm) {
		searchTerm = searchTerm.trim();
		return repository.findByRazonSocialOrNumeroDocumentoContainingIgnoreCase(searchTerm);
	}

	public Cliente findByNumeroDocumentoAndDocumentoId(String numeroDocumento, Integer documentoId) {
		return repository.findByNumeroDocumentoAndDocumentoId(numeroDocumento, documentoId)
				.orElseThrow(() -> new ClienteException(numeroDocumento, documentoId));
	}

	public Cliente createOrGet(Cliente cliente) {
		Optional<Cliente> existentClientByTipoDocumentoAndNumeroDocumentoOpt = repository
				.findByNumeroDocumentoAndDocumentoId(cliente.getNumeroDocumento(), cliente.getDocumentoId());

		if (existentClientByTipoDocumentoAndNumeroDocumentoOpt.isPresent()) {
			return existentClientByTipoDocumentoAndNumeroDocumentoOpt.get();
		}

		Optional<Cliente> existentClientByNumeroDocumentoOpt = repository
				.findTopByNumeroDocumento(cliente.getNumeroDocumento());

		if (existentClientByNumeroDocumentoOpt.isPresent()) {
			Cliente existentClientByNumeroDocumento = existentClientByNumeroDocumentoOpt.get();
			if (cliente.getNacionalidad().equals(existentClientByNumeroDocumento.getNacionalidad())
					&& (cliente.getEmail().equalsIgnoreCase(existentClientByNumeroDocumento.getEmail())
							|| cliente.getTelefono().equals(existentClientByNumeroDocumento.getTelefono()))) {
				return existentClientByNumeroDocumento;
			}
		}

		return repository.save(cliente);
	}

	public Cliente update(Long clienteId, Cliente updatedCliente) {
		Cliente existentClient = findByClienteId(clienteId);
		PosicionIva posicionIva = posicionIvaService.findByPosicionId(updatedCliente.getPosicionIva());

		existentClient.setNombre(updatedCliente.getNombre());
		existentClient.setCuit(updatedCliente.getCuit());
		existentClient.setRazonSocial(updatedCliente.getRazonSocial());
		existentClient.setNombreFantasia(updatedCliente.getNombreFantasia());
		existentClient.setDomicilio(updatedCliente.getDomicilio());
		existentClient.setTelefono(updatedCliente.getTelefono());
		existentClient.setEmail(updatedCliente.getEmail());
		existentClient.setNumeroMovil(updatedCliente.getNumeroMovil());
		existentClient.setPosicionIva(updatedCliente.getPosicionIva());
		existentClient.setDocumentoId(updatedCliente.getDocumentoId());
		existentClient.setTipoDocumento(updatedCliente.getTipoDocumento());
		existentClient.setNumeroDocumento(updatedCliente.getNumeroDocumento());
		existentClient.setNacionalidad(updatedCliente.getNacionalidad());
		existentClient.setClienteCategoriaId(updatedCliente.getClienteCategoriaId());
		existentClient.setImpositivoId(updatedCliente.getImpositivoId());
		existentClient.setDiscapacitado(updatedCliente.getDiscapacitado());
		existentClient.setPosicion(posicionIva);

		return repository.save(existentClient);
	}

	public List<Cliente> findAllByIds(List<Long> clienteIds) {
		return repository.findAllByClienteIdIn(clienteIds);
	}

	public Cliente findByCuit(String cuit) {
		return repository.findByCuit(cuit).orElseThrow(() -> new ClienteException(cuit));
	}

}
