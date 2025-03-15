/**
 * 
 */
package eterea.core.service.controller.facade;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.facade.VencimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/vencimiento", "/vencimiento"})
public class VencimientoController {

	private final VencimientoService service;

	public VencimientoController(VencimientoService service) {
		this.service = service;
	}

	@GetMapping("/notificaciondia")
	@Scheduled(cron = "0 0 9 * * *")
	private String notificaciondia() {
		return service.notificaciondia();
	}

}
