/**
 * 
 */
package eterea.core.api.rest.service.view;

import java.util.List;

import eterea.core.api.rest.repository.view.IUsuarioVencimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.view.UsuarioVencimiento;
import eterea.core.api.rest.tool.ToolService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class UsuarioVencimientoService {

	@Autowired
	private IUsuarioVencimientoRepository repository;

	public List<UsuarioVencimiento> findAllToday() {
		log.debug("Today -> {}", ToolService.dateAbsoluteArgentina());
		return repository.findAllByFechaVencimiento(ToolService.dateAbsoluteArgentina());
	}

}
