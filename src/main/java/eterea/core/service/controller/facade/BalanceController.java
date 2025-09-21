package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.view.AsientoView;
import eterea.core.service.service.CuentaMovimientoService;
import eterea.core.service.service.view.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping({"/api/core/balance", "/balance"})
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService service;
    private final CuentaMovimientoService cuentaMovimientoService;

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

    @GetMapping("/totalesEntreFechas/{numeroCuenta}/{desde}/{hasta}/{incluyeApertura}/{incluyeInflacion}")
    public ResponseEntity<List<BigDecimal>> totalesEntreFechas(@PathVariable Long numeroCuenta, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
                                                               @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta, @PathVariable Boolean incluyeApertura, @PathVariable Boolean incluyeInflacion) {
        return new ResponseEntity<>(cuentaMovimientoService.totalesEntreFechas(numeroCuenta, desde, hasta, incluyeApertura, incluyeInflacion), HttpStatus.OK);
    }

    @GetMapping("/asientos/{negocioId}/{fecha}")
    public ResponseEntity<List<AsientoView>> findAsientosByFecha(@PathVariable Integer negocioId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fecha) {
        return new ResponseEntity<>(service.findAsientosByFecha(negocioId, fecha), HttpStatus.OK);
    }

    @GetMapping("/cotizacion/generate/{monedaIdOrigen}/{monedaIdDestino}/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<Void> generateMovimientosCotizados(@PathVariable Integer monedaIdOrigen,
                                                             @PathVariable Integer monedaIdDestino,
                                                             @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaDesde,
                                                            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaHasta) {
        service.generateMovimientosCotizados(monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
