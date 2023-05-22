/**
 * 
 */
package eterea.core.api.rest.controller.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.facade.VencimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/vencimiento")
public class VencimientoController {

	@Autowired
	VencimientoService service;

	@GetMapping("/notificaciondia")
	@Scheduled(cron = "0 0 9 * * *")
	private String notificaciondia() {
		return service.notificaciondia();
	}
}
