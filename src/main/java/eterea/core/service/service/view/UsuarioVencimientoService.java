/**
 * 
 */
package eterea.core.service.service.view;

import java.util.List;

import eterea.core.service.repository.view.IUsuarioVencimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.service.model.view.UsuarioVencimiento;
import eterea.core.service.tool.ToolService;
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
