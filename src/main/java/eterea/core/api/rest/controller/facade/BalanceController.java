package eterea.core.api.rest.controller.facade;

import eterea.core.api.rest.service.view.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService service;

    @GetMapping("/cuadraAgencia")
    public ResponseEntity<Void> cuadraAgencia() {
        service.findAllDifferences();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
