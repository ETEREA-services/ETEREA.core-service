/**
 * 
 */
package eterea.core.api.rest.service.facade;

import eterea.core.api.rest.kotlin.model.ClienteMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import eterea.core.api.rest.model.ClienteMovimientoPrevio;
import eterea.core.api.rest.model.Comprobante;
import eterea.core.api.rest.model.dto.ImpresionFiscalDTO;
import eterea.core.api.rest.service.ArticuloMovimientoTemporalService;
import eterea.core.api.rest.service.ClienteMovimientoPrevioService;
import eterea.core.api.rest.service.ClienteMovimientoService;
import eterea.core.api.rest.service.ClienteService;
import eterea.core.api.rest.service.ComprobanteService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class ImpresionFiscalService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ComprobanteService comprobanteService;

	@Autowired
	private ClienteMovimientoService clienteMovimientoService;

	@Autowired
	private ClienteMovimientoPrevioService clienteMovimientoPrevioService;

	@Autowired
	private ArticuloMovimientoTemporalService articuloMovimientoTemporalService;

	public ImpresionFiscalDTO getData(String ipAddress, Long hWnd, Long clienteId, Integer comprobanteId,
			Long comprobanteOrigenId) {
		Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
		ClienteMovimiento clienteMovimiento = null;
		if (comprobanteOrigenId > 0) {
			clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
		}
		ImpresionFiscalDTO impresionFiscal = new ImpresionFiscalDTO(
				clienteMovimientoService.nextNumeroFactura(comprobante.getPuntoVenta(),
						comprobante.getLetraComprobante()),
				clienteService.findByClienteId(clienteId), comprobante,
				articuloMovimientoTemporalService.findAllByHwnd(ipAddress, hWnd, null), clienteMovimiento, null);
		try {
			log.debug("ImpresionFiscal -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(impresionFiscal));
		} catch (JsonProcessingException e) {
			log.debug("Exception in ImpresionFiscal object");
		}
		return impresionFiscal;
	}

	public ImpresionFiscalDTO getDataPrevio(Long clienteMovimientoPrevioId, Integer comprobanteId,
			Long comprobanteOrigenId) {
		Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
		ClienteMovimiento clienteMovimiento = null;
		if (comprobanteOrigenId > 0) {
			clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
		}
		ClienteMovimientoPrevio clienteMovimientoPrevio = clienteMovimientoPrevioService
				.findByClienteMovimientoPrevioId(clienteMovimientoPrevioId);
		ImpresionFiscalDTO impresionFiscal = new ImpresionFiscalDTO(
				clienteMovimientoService.nextNumeroFactura(comprobante.getPuntoVenta(),
						comprobante.getLetraComprobante()),
				clienteMovimientoPrevio.getCliente(), comprobante, null, clienteMovimiento, clienteMovimientoPrevio);
		try {
			log.debug("ImpresionFiscal -> {}", JsonMapper.builder().findAndAddModules().build()
					.writerWithDefaultPrettyPrinter().writeValueAsString(impresionFiscal));
		} catch (JsonProcessingException e) {
			log.debug("Exception in ImpresionFiscal object");
		}
		return impresionFiscal;
	}

}
