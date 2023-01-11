/**
 * 
 */
package eterea.api.rest.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.dto.VentaDTO;
import eterea.api.rest.service.ArticuloMovimientoService;
import eterea.api.rest.service.ClienteMovimientoService;
import eterea.api.rest.service.ValorMovimientoService;

/**
 * @author daniel
 *
 */
@Service
public class VentaService {

	@Autowired
	private ClienteMovimientoService clienteMovimientoService;

	@Autowired
	private ArticuloMovimientoService articuloMovimientoService;

	@Autowired
	private ValorMovimientoService valorMovimientoService;

	public VentaDTO findByComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
		VentaDTO venta = new VentaDTO();
		venta.setClienteMovimiento(
				clienteMovimientoService.findByComprobante(comprobanteId, puntoVenta, numeroComprobante));
		venta.setCliente(venta.getClienteMovimiento().getCliente());
		venta.setComprobante(venta.getClienteMovimiento().getComprobante());
		venta.setArticuloMovimientos(articuloMovimientoService
				.findAllByClienteMovimientoId(venta.getClienteMovimiento().getClienteMovimientoId()));
		venta.setValorMovimientos(valorMovimientoService
				.findAllbyClienteMovimientoId(venta.getClienteMovimiento().getClienteMovimientoId()));
		return venta;
	}

}
