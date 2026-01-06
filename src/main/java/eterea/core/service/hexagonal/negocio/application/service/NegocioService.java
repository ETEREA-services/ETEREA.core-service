/**
 * 
 */
package eterea.core.service.hexagonal.negocio.application.service;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;
import eterea.core.service.hexagonal.negocio.domain.ports.in.GetAllNegociosByCopyArticuloUseCase;
import eterea.core.service.hexagonal.negocio.domain.ports.in.GetAllNegociosWithIpAddressUseCase;
import eterea.core.service.hexagonal.negocio.domain.ports.in.GetNegocioByIdUseCase;
import eterea.core.service.hexagonal.negocio.domain.ports.out.NegocioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NegocioService implements GetAllNegociosByCopyArticuloUseCase, GetNegocioByIdUseCase, GetAllNegociosWithIpAddressUseCase {

	private final NegocioRepository negocioRepository;

	@Override
	public List<Negocio> getAllNegociosByCopyArticulo(Byte copyArticulo) {
		return negocioRepository.findAllByCopyArticulo(copyArticulo);
	}

	@Override
	public Optional<Negocio> getNegocioById(Integer negocioId) {
		return negocioRepository.findByNegocioId(negocioId);
	}

	@Override
	public List<Negocio> getAllNegociosWithIpAddress(Integer negocioId) {
		return negocioRepository.findAllWithIpAddress(negocioId);
	}

}
