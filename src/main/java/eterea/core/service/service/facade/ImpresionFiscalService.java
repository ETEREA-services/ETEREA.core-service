/**
 * 
 */
package eterea.core.service.service.facade;

import eterea.core.service.kotlin.model.ClienteMovimientoPrevio;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.model.ClienteMovimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
@RequiredArgsConstructor
public class ImpresionFiscalService {

	private final ClienteService clienteService;
	private final ComprobanteService comprobanteService;
	private final ClienteMovimientoService clienteMovimientoService;
	private final ClienteMovimientoPrevioService clienteMovimientoPrevioService;
	private final ArticuloMovimientoTemporalService articuloMovimientoTemporalService;

	public ImpresionFiscalDto getData(String ipAddress, Long hWnd, Long clienteId, Integer comprobanteId,
                                      Long comprobanteOrigenId) {
		Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
		ClienteMovimiento clienteMovimiento = null;
		if (comprobanteOrigenId > 0) {
			clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
		}
		ImpresionFiscalDto impresionFiscal = new ImpresionFiscalDto(
				clienteMovimientoService.nextNumeroFactura(
                        comprobante.getLetraComprobante(),
                        comprobante.getPuntoVenta(),
                        0
                ),
				clienteService.findByClienteId(clienteId), comprobante,
				articuloMovimientoTemporalService.findAllByHWnd(ipAddress, hWnd, null), clienteMovimiento, null);
		log.debug("ImpresionFiscal -> {}", impresionFiscal.jsonify());
		return impresionFiscal;
	}

	public ImpresionFiscalDto getDataPrevio(Long clienteMovimientoPrevioId, Integer comprobanteId,
                                            Long comprobanteOrigenId) {
		Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
		log.debug("Comprobante -> {}", comprobante.jsonify());
		ClienteMovimiento clienteMovimiento = null;
		if (comprobanteOrigenId > 0) {
			clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(comprobanteOrigenId);
		}
		ClienteMovimientoPrevio clienteMovimientoPrevio = clienteMovimientoPrevioService
				.findByClienteMovimientoPrevioId(clienteMovimientoPrevioId);
		log.debug("ClienteMovimientoPrevio -> {}", clienteMovimientoPrevio.jsonify());
		ImpresionFiscalDto impresionFiscal = new ImpresionFiscalDto(
				clienteMovimientoService.nextNumeroFactura(
                        comprobante.getLetraComprobante(),
                        comprobante.getPuntoVenta(),
                        0
                ),
				clienteMovimientoPrevio.getCliente(), comprobante, null, clienteMovimiento, clienteMovimientoPrevio);
		log.debug("ImpresionFiscal -> {}", impresionFiscal.jsonify());
		return impresionFiscal;
	}

}
