/**
 * 
 */
package eterea.core.api.rest.service.facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import eterea.core.api.rest.kotlin.model.*;
import eterea.core.api.rest.repository.IReservaRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Articulo;
import eterea.core.api.rest.model.Comprobante;
import eterea.core.api.rest.model.ReservaArticulo;
import eterea.core.api.rest.service.ArticuloMovimientoService;
import eterea.core.api.rest.service.ArticuloService;
import eterea.core.api.rest.service.ClienteMovimientoService;
import eterea.core.api.rest.service.ComprobanteService;
import eterea.core.api.rest.service.ConceptoFacturadoService;
import eterea.core.api.rest.service.ReservaArticuloService;
import eterea.core.api.rest.service.VoucherService;

/**
 * @author daniel
 *
 */
@Service
public class ReservaService {

	private final IReservaRepository repository;

	private final ClienteMovimientoService clienteMovimientoService;

	private final VoucherService voucherService;

	private final ComprobanteService comprobanteService;

	private final ReservaArticuloService reservaArticuloService;

	private final ArticuloService articuloService;

	private final ArticuloMovimientoService articuloMovimientoService;

	private final ConceptoFacturadoService conceptoFacturadoService;

	@Autowired
	public ReservaService(IReservaRepository repository, ClienteMovimientoService clienteMovimientoService, VoucherService voucherService, ComprobanteService comprobanteService, ReservaArticuloService reservaArticuloService, ArticuloService articuloService, ArticuloMovimientoService articuloMovimientoService, ConceptoFacturadoService conceptoFacturadoService) {
		this.repository = repository;
		this.clienteMovimientoService = clienteMovimientoService;
		this.voucherService = voucherService;
		this.comprobanteService = comprobanteService;
		this.reservaArticuloService = reservaArticuloService;
		this.articuloService = articuloService;
		this.articuloMovimientoService = articuloMovimientoService;
		this.conceptoFacturadoService = conceptoFacturadoService;
	}

	public List<Reserva> findTopPendientes() {
		return repository
				.findTop100ByVerificadaAndFacturadaAndEliminadaAndPagaCacheutaAndFacturadoFueraAndAnuladaAndClienteIdGreaterThan(
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 0L,
						Sort.by("fechaToma").descending().and(Sort.by("clienteId")));
	}

	@Transactional
	public void completeArticulos(Long clienteMovimientoId) {
		ClienteMovimiento clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);

		if (clienteMovimiento.getReservaId() == 0)
			return;
		Voucher voucher = voucherService.findByReservaId(clienteMovimiento.getReservaId());
		Reserva reserva = repository.findByReservaId(clienteMovimiento.getReservaId()).get();
		Comprobante comprobante = comprobanteService.findByComprobanteId(clienteMovimiento.getComprobanteId());
		int item = 0;
		List<ArticuloMovimiento> articuloMovs = new ArrayList<>();

		String numeroVoucher = "";
		if (!voucher.getNumeroVoucher().trim().isEmpty())
			numeroVoucher = " - No.Voucher: " + voucher.getNumeroVoucher();

		for (ReservaArticulo reservaArticulo : reservaArticuloService
				.findAllByReservaId(clienteMovimiento.getReservaId())) {
			if (!reservaArticulo.getObservaciones().trim().isEmpty())
				reservaArticulo.setObservaciones(reservaArticulo.getObservaciones() + numeroVoucher);
			addArticulo(clienteMovimiento, reservaArticulo, comprobante, ++item, reserva.getFacturarExtranjero(),
					articuloMovs);
		}
	}

	@Transactional
	protected void addArticulo(ClienteMovimiento clienteMovimiento, ReservaArticulo reservaArticulo,
							   Comprobante comprobante, Integer item, Byte facturaExtranjero, List<ArticuloMovimiento> articuloMovs) {
		Articulo articulo = articuloService.findByArticuloId(reservaArticulo.getArticuloId());
		BigDecimal factorIva = new BigDecimal("1.21");
		if (articulo.getIva105() == 1)
			factorIva = new BigDecimal("1.105");
		if (articulo.getExento() == 1)
			factorIva = new BigDecimal("1.0");
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

			if (!reservaArticulo.getObservaciones().trim().isEmpty()) {
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
