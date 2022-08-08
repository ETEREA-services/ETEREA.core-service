/**
 * 
 */
package eterea.api.rest.service.facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.Articulo;
import eterea.api.rest.model.ArticuloMovimiento;
import eterea.api.rest.model.ClienteMovimiento;
import eterea.api.rest.model.Comprobante;
import eterea.api.rest.model.ConceptoFacturado;
import eterea.api.rest.model.Reserva;
import eterea.api.rest.model.ReservaArticulo;
import eterea.api.rest.model.Voucher;
import eterea.api.rest.repository.IReservaRepository;
import eterea.api.rest.service.ArticuloMovimientoService;
import eterea.api.rest.service.ArticuloService;
import eterea.api.rest.service.ClienteMovimientoService;
import eterea.api.rest.service.ComprobanteService;
import eterea.api.rest.service.ConceptoFacturadoService;
import eterea.api.rest.service.ReservaArticuloService;
import eterea.api.rest.service.VoucherService;

/**
 * @author daniel
 *
 */
@Service
public class ReservaService {

	@Autowired
	private IReservaRepository repository;

	@Autowired
	private ClienteMovimientoService clienteMovimientoService;

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private ComprobanteService comprobanteService;

	@Autowired
	private ReservaArticuloService reservaArticuloService;

	@Autowired
	private ArticuloService articuloService;

	@Autowired
	private ArticuloMovimientoService articuloMovimientoService;

	@Autowired
	private ConceptoFacturadoService conceptoFacturadoService;

	@Transactional
	public void completeArticulos(Long clienteMovimientoId) {
		ClienteMovimiento clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);

		if (clienteMovimiento.getReservaId() == 0)
			return;
		Voucher voucher = voucherService.findByReservaId(clienteMovimiento.getReservaId());
		Reserva reserva = repository.findByReservaId(clienteMovimiento.getReservaId()).get();
		Comprobante comprobante = comprobanteService.findByComprobanteId(clienteMovimiento.getComprobanteId());
		Integer item = 0;
		List<ArticuloMovimiento> articuloMovs = new ArrayList<ArticuloMovimiento>();

		String numeroVoucher = "";
		if (!voucher.getNumeroVoucher().trim().equals(""))
			numeroVoucher = " - No.Voucher: " + voucher.getNumeroVoucher();

		for (ReservaArticulo reservaArticulo : reservaArticuloService
				.findAllByReservaId(clienteMovimiento.getReservaId())) {
			if (!reservaArticulo.getObservaciones().trim().equals(""))
				reservaArticulo.setObservaciones(reservaArticulo.getObservaciones() + numeroVoucher);
			addArticulo(clienteMovimiento, reservaArticulo, comprobante, ++item, reserva.getFacturarextranjero(),
					articuloMovs);
		}
	}

	@Transactional
	private void addArticulo(ClienteMovimiento clienteMovimiento, ReservaArticulo reservaArticulo,
			Comprobante comprobante, Integer item, Byte facturaExtranjero, List<ArticuloMovimiento> articuloMovs) {
		Articulo articulo = articuloService.findByArticuloId(reservaArticulo.getArticuloId());
		BigDecimal factorIva = new BigDecimal(1.21);
		if (articulo.getIva105() == 1)
			factorIva = new BigDecimal(1.105);
		if (articulo.getExento() == 1)
			factorIva = new BigDecimal(1.0);
		Integer factor = comprobante.getDebita() == 1 ? -1 : 1;

		if (articulo.getCentroStockId() != 1 || facturaExtranjero == 0) {
			ArticuloMovimiento articuloMovimiento = new ArticuloMovimiento();
			articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
			articuloMovimiento.setComprobanteId(clienteMovimiento.getComprobanteId());
			articuloMovimiento.setNegocioId(clienteMovimiento.getNegocioId());
			articuloMovimiento.setItem(item);
			articuloMovimiento.setFechaMovimiento(clienteMovimiento.getFechaComprobante());
			articuloMovimiento.setFechaFactura(clienteMovimiento.getFechaComprobante());
			articuloMovimiento.setCierreCajaId(clienteMovimiento.getCierreCajaId());
			articuloMovimiento.setArticuloId(reservaArticulo.getArticuloId());
			articuloMovimiento.setCentroStockId(articulo.getCentroStockId());
			BigDecimal preciounitario = reservaArticulo.getPreciounitario();
			articuloMovimiento.setPrecioUnitarioSinIva(preciounitario.divide(factorIva, 2, RoundingMode.HALF_UP));
			preciounitario = articuloMovimiento.getPrecioUnitarioSinIva().multiply(factorIva);
			preciounitario = preciounitario.setScale(2, RoundingMode.HALF_UP);
			articuloMovimiento.setPrecioUnitarioConIva(preciounitario);
			articuloMovimiento.setPrecioUnitario(articuloMovimiento.getPrecioUnitarioConIva());
			articuloMovimiento.setCantidad(new BigDecimal(factor * reservaArticulo.getCantidad()));
			articuloMovimiento.setTotal(
					articuloMovimiento.getPrecioUnitario().multiply(new BigDecimal(reservaArticulo.getCantidad())));
			articuloMovimiento.setCuenta(articulo.getCuentaVentas());
			articuloMovimiento.setIva105(articulo.getIva105());
			articuloMovimiento.setExento(articulo.getExento());

			articuloMovimiento = articuloMovimientoService.add(articuloMovimiento);

			articuloMovs.add(articuloMovimiento);

			if (!reservaArticulo.getObservaciones().trim().equals("")) {
				ConceptoFacturado conceptoFacturado = new ConceptoFacturado();
				conceptoFacturado.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
				conceptoFacturado.setNumeroLinea(1);
				conceptoFacturado.setConcepto(reservaArticulo.getObservaciones());
				conceptoFacturado.setArticuloMovimientoId(articuloMovimiento.getArticuloMovimientoId());

				conceptoFacturado = conceptoFacturadoService.add(conceptoFacturado);
			}
		}
	}
}
