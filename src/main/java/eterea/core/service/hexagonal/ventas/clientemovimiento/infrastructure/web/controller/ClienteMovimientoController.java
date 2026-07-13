package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import eterea.core.service.hexagonal.ventas.clientemovimiento.application.exception.ClienteMovimientoException;
import eterea.core.service.hexagonal.ventas.clientemovimiento.application.service.ClienteMovimientoService;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ClienteMovimientoRequest;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ClienteMovimientoResponse;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.mapper.ClienteMovimientoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/clienteMovimiento", "/clienteMovimiento"})
@RequiredArgsConstructor
public class ClienteMovimientoController {

    private final ClienteMovimientoService service;
    private final ClienteMovimientoDtoMapper mapper;

    @GetMapping("/asociable/{clienteId}/comprobante/{comprobanteId}")
    public ResponseEntity<List<ClienteMovimientoResponse>> findTop200Asociables(
        @PathVariable Long clienteId, @PathVariable Integer comprobanteId) {
        List<ClienteMovimientoResponse> responses = service.findTop200Asociables(clienteId, comprobanteId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/findAllByReservaIds/{reservaIds}")
    public ResponseEntity<List<ClienteMovimientoResponse>> findAllByReservaIds(@PathVariable List<Long> reservaIds) {
        List<ClienteMovimientoResponse> responses = service.findAllByReservaIds(reservaIds).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<ClienteMovimientoResponse>> findAllByReservaId(@PathVariable Long reservaId) {
        List<ClienteMovimientoResponse> responses = service.findAllByReservaId(reservaId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/rango/facturas/{letraComprobante}/{debita}/{puntoVenta}/{numeroComprobanteDesde}/{numeroComprobanteHasta}")
    public ResponseEntity<List<ClienteMovimientoResponse>> findAllFacturasByRango(
            @PathVariable String letraComprobante,
            @PathVariable Byte debita,
            @PathVariable Integer puntoVenta,
            @PathVariable Long numeroComprobanteDesde,
            @PathVariable Long numeroComprobanteHasta) {
        List<ClienteMovimientoResponse> responses = service.findAllFacturasByRango(letraComprobante, debita,
                puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/arca/regimen/informacion/ventas/{desde}/{hasta}")
    public ResponseEntity<List<ClienteMovimientoResponse>> findAllByRegimenInformacionVentas(
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta) {
        List<ClienteMovimientoResponse> responses = service.findAllByRegimenInformacionVentas(desde, hasta).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/last/{puntoVenta}/{letraComprobante}")
    public ResponseEntity<Long> nextNumeroFactura(@PathVariable Integer puntoVenta,
        @PathVariable String letraComprobante) {
        return new ResponseEntity<>(service.nextNumeroFactura(letraComprobante, puntoVenta, 0), HttpStatus.OK);
    }

    @GetMapping("/consulta/comprobante/{comprobanteId}/{puntoVenta}/{numeroComprobante}")
    public ResponseEntity<ClienteMovimientoResponse> findByComprobante(
        @PathVariable Integer comprobanteId, @PathVariable Integer puntoVenta,
        @PathVariable Long numeroComprobante) {
        try {
            ClienteMovimiento domain = service.findByComprobante(comprobanteId, puntoVenta, numeroComprobante);
            return ResponseEntity.ok(mapper.toResponse(domain));
        } catch (ClienteMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/consulta/factura/{letraComprobante}/{debita}/{puntoVenta}/{numeroComprobante}")
    public ResponseEntity<ClienteMovimientoResponse> findByFactura(
            @PathVariable String letraComprobante,
            @PathVariable Byte debita,
            @PathVariable Integer puntoVenta,
            @PathVariable Long numeroComprobante) {
        try {
            ClienteMovimiento domain = service.findByFactura(letraComprobante, debita, puntoVenta, numeroComprobante);
            return ResponseEntity.ok(mapper.toResponse(domain));
        } catch (ClienteMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{clienteMovimientoId}")
    public ResponseEntity<ClienteMovimientoResponse> findByClienteMovimientoId(
        @PathVariable Long clienteMovimientoId) {
        try {
            ClienteMovimiento domain = service.findByClienteMovimientoId(clienteMovimientoId);
            return ResponseEntity.ok(mapper.toResponse(domain));
        } catch (ClienteMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/find/next/invoice/number/letraComprobante/{letraComprobante}/puntoVenta/{puntoVenta}/comprobante/{comprobanteId}")
    public ResponseEntity<Long> findNextInvoiceNumber(
        @PathVariable String letraComprobante, @PathVariable Integer puntoVenta,
        @PathVariable Integer comprobanteId) {
        try {
            return ResponseEntity.ok(service.nextNumeroFactura(letraComprobante, puntoVenta, comprobanteId));
        } catch (ClienteMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/find/next/creditNote/number/letraComprobante/{letraComprobante}/puntoVenta/{puntoVenta}/comprobante/{comprobanteId}")
    public ResponseEntity<Long> findNextCreditNoteNumber(
        @PathVariable String letraComprobante, @PathVariable Integer puntoVenta,
        @PathVariable Integer comprobanteId) {
        try {
            return ResponseEntity.ok(service.nextNumeroNotaCredito(letraComprobante, puntoVenta, comprobanteId));
        } catch (ClienteMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/byIds")
    public ResponseEntity<List<ClienteMovimientoResponse>> findAllByIds(@RequestBody List<Long> clienteMovimientoIds) {
        List<ClienteMovimientoResponse> responses = service.findAllByIds(clienteMovimientoIds).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ClienteMovimientoResponse> create(@RequestBody ClienteMovimientoRequest clienteMovimiento) {
        ClienteMovimiento domain = mapper.toDomain(clienteMovimiento);
        ClienteMovimiento created = service.add(domain);
        return ResponseEntity.ok(mapper.toResponse(created));
    }
}
