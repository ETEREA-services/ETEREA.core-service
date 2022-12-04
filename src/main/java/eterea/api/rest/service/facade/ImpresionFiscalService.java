/**
 * 
 */
package eterea.api.rest.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.Comprobante;
import eterea.api.rest.model.dto.ImpresionFiscalDTO;
import eterea.api.rest.service.ArticuloMovimientoTemporalService;
import eterea.api.rest.service.ClienteMovimientoService;
import eterea.api.rest.service.ClienteService;
import eterea.api.rest.service.ComprobanteService;

/**
 * @author daniel
 *
 */
@Service
public class ImpresionFiscalService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ComprobanteService comprobanteService;

	@Autowired
	private ClienteMovimientoService clienteMovimientoService;

	@Autowired
	private ArticuloMovimientoTemporalService articuloMovimientoTemporalService;

	public ImpresionFiscalDTO getData(String ipAddress, Long hWnd, Long clienteId, Integer comprobanteId) {
		Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
		return new ImpresionFiscalDTO(
				clienteMovimientoService.nextNumeroFactura(comprobante.getPuntoVenta(),
						comprobante.getLetraComprobante()),
				clienteService.findByClienteId(clienteId), comprobante,
				articuloMovimientoTemporalService.findAllByHwnd(ipAddress, hWnd, null));
	}

}
