/**
 * 
 */
package eterea.core.service.service.view;

import java.util.List;

import eterea.core.service.repository.view.IUsuarioVencimientoRepository;
import org.springframework.stereotype.Service;

import eterea.core.service.model.view.UsuarioVencimiento;
import eterea.core.service.tool.ToolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioVencimientoService {

	private final IUsuarioVencimientoRepository repository;

	public List<UsuarioVencimiento> findAllToday() {
		log.debug("Today -> {}", ToolService.dateAbsoluteArgentina());
		return repository.findAllByFechaVencimiento(ToolService.dateAbsoluteArgentina());
	}

}
