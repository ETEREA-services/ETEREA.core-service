package eterea.core.api.rest.controller.facade;

import eterea.core.api.rest.service.CuentaMovimientoService;
import eterea.core.api.rest.service.view.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService service;

    private final CuentaMovimientoService cuentaMovimientoService;

    @Autowired
    public BalanceController(BalanceService service, CuentaMovimientoService cuentaMovimientoService) {
        this.service = service;
        this.cuentaMovimientoService = cuentaMovimientoService;
    }

    @GetMapping("/cuadraAgencia")
    public ResponseEntity<Void> cuadraAgencia() {
        service.findAllDifferences();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/totalDebeEntreFechas/{numeroCuenta}/{desde}/{hasta}/{incluyeApertura}/{incluyeInflacion}")
    public ResponseEntity<BigDecimal> totalDebeEntreFechas(@PathVariable Long numeroCuenta, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
                                                           @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta, @PathVariable Boolean incluyeApertura, @PathVariable Boolean incluyeInflacion) {
        return new ResponseEntity<>(cuentaMovimientoService.totalDebeEntreFechas(numeroCuenta, desde, hasta, incluyeApertura, incluyeInflacion), HttpStatus.OK);
    }

    @GetMapping("/totalHaberEntreFechas/{numeroCuenta}/{desde}/{hasta}/{incluyeApertura}/{incluyeInflacion}")
    public ResponseEntity<BigDecimal> totalHaberEntreFechas(@PathVariable Long numeroCuenta, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
                                                            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta, @PathVariable Boolean incluyeApertura, @PathVariable Boolean incluyeInflacion) {
        return new ResponseEntity<>(cuentaMovimientoService.totalHaberEntreFechas(numeroCuenta, desde, hasta, incluyeApertura, incluyeInflacion), HttpStatus.OK);
    }

}
