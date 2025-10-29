/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.exception.ClienteMovimientoException;
import eterea.core.service.model.ClienteMovimiento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import eterea.core.service.service.ClienteMovimientoService;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/clienteMovimiento", "/clienteMovimiento"})
public class ClienteMovimientoController {

	private final ClienteMovimientoService service;

	public ClienteMovimientoController(ClienteMovimientoService service) {
		this.service = service;
	}

	@GetMapping("/asociable/{clienteId}/comprobante/{comprobanteId}")
	public ResponseEntity<List<ClienteMovimiento>> findTop200Asociables(@PathVariable Long clienteId, @PathVariable Integer comprobanteId) {
        return ResponseEntity.ok(service.findTop200Asociables(clienteId, comprobanteId));
	}

	@GetMapping("/findAllByReservaIds/{reservaIds}")
	public ResponseEntity<List<ClienteMovimiento>> findAllByReservaIds(@PathVariable List<Long> reservaIds) {
        return ResponseEntity.ok(service.findAllByReservaIds(reservaIds));
	}

	@GetMapping("/reserva/{reservaId}")
	public ResponseEntity<List<ClienteMovimiento>> findAllByReservaId(@PathVariable Long reservaId) {
        return ResponseEntity.ok(service.findAllByReservaId(reservaId));
	}

	@GetMapping("/rango/facturas/{letraComprobante}/{debita}/{puntoVenta}/{numeroComprobanteDesde}/{numeroComprobanteHasta}")
	public ResponseEntity<List<ClienteMovimiento>> findAllFacturasByRango(
			@PathVariable String letraComprobante,
			@PathVariable Byte debita,
			@PathVariable Integer puntoVenta,
			@PathVariable Long numeroComprobanteDesde,
			@PathVariable Long numeroComprobanteHasta
	) {
        return ResponseEntity.ok(service.findAllFacturasByRango(letraComprobante, debita, puntoVenta, numeroComprobanteDesde,
                numeroComprobanteHasta));
	}

	@GetMapping("/last/{puntoVenta}/{letraComprobante}")
	public ResponseEntity<Long> nextNumeroFactura(@PathVariable Integer puntoVenta,
			@PathVariable String letraComprobante) {
		return new ResponseEntity<>(service.nextNumeroFactura(letraComprobante, puntoVenta, 0), HttpStatus.OK);
	}

	@GetMapping("/consulta/comprobante/{comprobanteId}/{puntoVenta}/{numeroComprobante}")
	public ResponseEntity<ClienteMovimiento> findByComprobante(@PathVariable Integer comprobanteId, @PathVariable Integer puntoVenta, @PathVariable Long numeroComprobante) {
		try {
			return ResponseEntity.ok(service.findByComprobante(comprobanteId, puntoVenta, numeroComprobante));
		} catch (ClienteMovimientoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/consulta/factura/{letraComprobante}/{debita}/{puntoVenta}/{numeroComprobante}")
	public ResponseEntity<ClienteMovimiento> findByFactura(
			@PathVariable String letraComprobante,
			@PathVariable Byte debita,
			@PathVariable Integer puntoVenta,
			@PathVariable Long numeroComprobante
	) {
		try {
			return ResponseEntity.ok(service.findByFactura(letraComprobante, debita, puntoVenta, numeroComprobante));
		} catch (ClienteMovimientoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/{clienteMovimientoId}")
	public ResponseEntity<ClienteMovimiento> findByClienteMovimientoId(@PathVariable Long clienteMovimientoId) {
		try {
			return ResponseEntity.ok(service.findByClienteMovimientoId(clienteMovimientoId));
		} catch (ClienteMovimientoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

    @GetMapping("/find/next/invoice/number/letraComprobante/{letraComprobante}/puntoVenta/{puntoVenta}/comprobante/{comprobanteId}")
    public ResponseEntity<Long> findNextInvoiceNumber(@PathVariable String letraComprobante, @PathVariable Integer puntoVenta, @PathVariable Integer comprobanteId) {
        try {
            return ResponseEntity.ok(service.nextNumeroFactura(letraComprobante, puntoVenta, comprobanteId));
        } catch (ClienteMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/find/next/creditNote/number/letraComprobante/{letraComprobante}/puntoVenta/{puntoVenta}/comprobante/{comprobanteId}")
    public ResponseEntity<Long> findNextCreditNoteNumber(@PathVariable String letraComprobante, @PathVariable Integer puntoVenta, @PathVariable Integer comprobanteId) {
        try {
            return ResponseEntity.ok(service.nextNumeroNotaCredito(letraComprobante, puntoVenta, comprobanteId));
        } catch (ClienteMovimientoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<ClienteMovimiento> create(@RequestBody ClienteMovimiento clienteMovimiento) {
        return ResponseEntity.ok(service.add(clienteMovimiento));
    }

}
