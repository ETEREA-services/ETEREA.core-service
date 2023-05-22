/**
 * 
 */
package eterea.core.api.rest.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.client.OrderWeb;
import eterea.core.api.rest.service.client.OrderWebService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/orderWeb")
public class OrderWebController {
	
	@Autowired
	private OrderWebService service;

	@GetMapping("/test")
	public ResponseEntity<List<OrderWeb>> test() {
		return new ResponseEntity<List<OrderWeb>>(service.test(), HttpStatus.OK);
	}
}
