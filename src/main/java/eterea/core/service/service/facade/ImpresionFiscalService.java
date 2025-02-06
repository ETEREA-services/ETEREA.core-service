/**
 * 
 */
package eterea.core.service.service.facade;

import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.ClienteMovimientoPrevio;
import eterea.core.service.kotlin.model.Comprobante;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import eterea.core.service.model.dto.ImpresionFiscalDto;
import eterea.core.service.service.ArticuloMovimientoTemporalService;
import eterea.core.service.service.ClienteMovimientoPrevioService;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.ClienteService;
import eterea.core.service.service.ComprobanteService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class ImpresionFiscalService {

	private final ClienteService clienteService;
	private final ComprobanteService comprobanteService;
	private final ClienteMovimientoService clienteMovimientoService;
	private final ClienteMovimientoPrevioService clienteMovimientoPrevioService;
	private final ArticuloMovimientoTemporalService articuloMovimientoTemporalService;

	public ImpresionFiscalService(ClienteService clienteService, ComprobanteService comprobanteService, ClienteMovimientoService clienteMovimientoService, ClienteMovimientoPrevioService clienteMovimientoPrevioService, ArticuloMovimientoTemporalService articuloMovimientoTemporalService) {
		this.clienteService = clienteService;
		this.comprobanteService = comprobanteService;
		this.clienteMovimientoService = clienteMovimientoService;
		this.clienteMovimientoPrevioService = clienteMovimientoPrevioService;
		this.articuloMovimientoTemporalService = articuloMovimientoTemporalService;
	}

	public ImpresionFiscalDto getData(String ipAddress, Long hWnd, Long clienteId, Integer comprobanteId,
                                      Long comprobanteOrigenId) {
		Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
		ClienteMovimiento clienteMovimiento = null;
		if (comprobanteOrigenId > 0) {
			clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
		}
		ImpresionFiscalDto impresionFiscal = new ImpresionFiscalDto(
				clienteMovimientoService.nextNumeroFactura(comprobante.getPuntoVenta(),
						comprobante.getLetraComprobante()),
				clienteService.findByClienteId(clienteId), comprobante,
				articuloMovimientoTemporalService.findAllByHwnd(ipAddress, hWnd, null), clienteMovimiento, null);
		logImpresionFiscal(impresionFiscal);
		return impresionFiscal;
	}

	public ImpresionFiscalDto getDataPrevio(Long clienteMovimientoPrevioId, Integer comprobanteId,
                                            Long comprobanteOrigenId) {
		Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
		logComprobante(comprobante);
		ClienteMovimiento clienteMovimiento = null;
		if (comprobanteOrigenId > 0) {
			clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
		}
		ClienteMovimientoPrevio clienteMovimientoPrevio = clienteMovimientoPrevioService
				.findByClienteMovimientoPrevioId(clienteMovimientoPrevioId);
		logClienteMovimientoPrevio(clienteMovimientoPrevio);
		ImpresionFiscalDto impresionFiscal = new ImpresionFiscalDto(
				clienteMovimientoService.nextNumeroFactura(comprobante.getPuntoVenta(),
						comprobante.getLetraComprobante()),
				clienteMovimientoPrevio.getCliente(), comprobante, null, clienteMovimiento, clienteMovimientoPrevio);
		logImpresionFiscal(impresionFiscal);
		return impresionFiscal;
	}

	private void logClienteMovimientoPrevio(ClienteMovimientoPrevio clienteMovimientoPrevio) {
		try {
			log.debug("ClienteMovimientoPrecio -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(clienteMovimientoPrevio));
		} catch (JsonProcessingException e) {
			log.debug("ClienteMovimientoPrevio jsonify error -> {}", e.getMessage());
		}
	}

	private void logComprobante(Comprobante comprobante) {
		try {
			log.debug("Comprobante -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(comprobante));
		} catch (JsonProcessingException e) {
			log.debug("Comprobante jsonify error -> {}", e.getMessage());
		}
	}

	private void logImpresionFiscal(ImpresionFiscalDto impresionFiscal) {
		try {
			log.debug("ImpresionFiscal -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(impresionFiscal));
		} catch (JsonProcessingException e) {
			log.debug("Exception in ImpresionFiscal object");
		}
	}

}
