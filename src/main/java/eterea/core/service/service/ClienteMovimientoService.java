/**
 * 
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import eterea.core.service.exception.ClienteMovimientoException;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.repository.ClienteMovimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ClienteMovimientoService {

	private final ClienteMovimientoRepository repository;
	private final ComprobanteService comprobanteService;

	public ClienteMovimientoService(ClienteMovimientoRepository repository, ComprobanteService comprobanteService) {
		this.repository = repository;
		this.comprobanteService = comprobanteService;
	}

	public List<ClienteMovimiento> findTop200Asociables(Long clienteId) {
		List<Integer> comprobanteIds = comprobanteService.findAllAsociables().stream()
				.map(Comprobante::getComprobanteId).collect(Collectors.toList());
		return repository.findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId, comprobanteIds);
	}

	public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
		return repository.findAllByReservaIdIn(reservaIds);
	}

	public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}

	public List<ClienteMovimiento> findAllFacturadosByFecha(OffsetDateTime fecha) {
		return repository.findAllByFechaComprobanteAndComprobanteLibroIva(fecha, (byte) 1);
	}

	public List<ClienteMovimiento> findAllFacturasByRango(String letraComprobante, Byte debita, Integer puntoVenta, Long numeroComprobanteDesde, Long numeroComprobanteHasta) {
		return repository.findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(letraComprobante, (byte) 0, puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta, debita);
	}

	public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findByClienteMovimientoId(clienteMovimientoId)
				.orElseThrow(() -> new ClienteMovimientoException(clienteMovimientoId));
	}

	public ClienteMovimiento add(ClienteMovimiento clienteMovimiento) {
		return repository.save(clienteMovimiento);
	}

	public ClienteMovimiento update(ClienteMovimiento newClienteMovimiento, Long clienteMovimientoId) {
		return repository.findByClienteMovimientoId(clienteMovimientoId).map(clienteMovimiento -> {
			clienteMovimiento = new ClienteMovimiento.Builder()
					.clienteMovimientoId(clienteMovimientoId)
					.comprobanteId(newClienteMovimiento.getComprobanteId())
					.puntoVenta(newClienteMovimiento.getPuntoVenta())
					.numeroComprobante(newClienteMovimiento.getNumeroComprobante())
					.fechaComprobante(newClienteMovimiento.getFechaComprobante())
					.clienteId(newClienteMovimiento.getClienteId())
					.fechaVencimiento(newClienteMovimiento.getFechaVencimiento())
					.negocioId(newClienteMovimiento.getNegocioId())
					.empresaId(newClienteMovimiento.getEmpresaId())
					.importe(newClienteMovimiento.getImporte())
					.cancelado(newClienteMovimiento.getCancelado())
					.neto(newClienteMovimiento.getNeto())
					.netoCancelado(newClienteMovimiento.getNetoCancelado())
					.montoIva(newClienteMovimiento.getMontoIva())
					.montoIvaRni(newClienteMovimiento.getMontoIvaRni())
					.reintegroTurista(newClienteMovimiento.getReintegroTurista())
					.fechaContable(newClienteMovimiento.getFechaContable())
					.ordenContable(newClienteMovimiento.getOrdenContable())
					.recibo(newClienteMovimiento.getRecibo())
					.asignado(newClienteMovimiento.getAsignado())
					.anulada(newClienteMovimiento.getAnulada())
					.decreto104316(newClienteMovimiento.getDecreto104316())
					.letraComprobante(newClienteMovimiento.getLetraComprobante())
					.montoExento(newClienteMovimiento.getMontoExento())
					.reservaId(newClienteMovimiento.getReservaId())
					.montoCuentaCorriente(newClienteMovimiento.getMontoCuentaCorriente())
					.cierreCajaId(newClienteMovimiento.getCierreCajaId())
					.cierreRestaurantId(newClienteMovimiento.getCierreRestaurantId())
					.nivel(newClienteMovimiento.getNivel())
					.eliminar(newClienteMovimiento.getEliminar())
					.cuentaCorriente(newClienteMovimiento.getCuentaCorriente())
					.letras(newClienteMovimiento.getLetras())
					.cae(newClienteMovimiento.getCae())
					.caeVencimiento(newClienteMovimiento.getCaeVencimiento())
					.codigoBarras(newClienteMovimiento.getCodigoBarras())
					.participacion(newClienteMovimiento.getParticipacion())
					.monedaId(newClienteMovimiento.getMonedaId())
					.cotizacion(newClienteMovimiento.getCotizacion())
					.observaciones(newClienteMovimiento.getObservaciones())
					.clienteMovimientoIdSlave(newClienteMovimiento.getClienteMovimientoIdSlave())
					.build();
			return repository.save(clienteMovimiento);
		}).orElseThrow(() -> new ClienteMovimientoException(clienteMovimientoId));
	}

	public Long nextNumeroFactura(Integer puntoVenta, String letraComprobante) {
		return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc((byte) 0, puntoVenta,
				letraComprobante).map(clienteMovimiento -> {
					return 1 + clienteMovimiento.getNumeroComprobante();
				}).orElse(1L);
	}

	@Transactional
    public void deleteAll0ByFecha(OffsetDateTime fecha) {
		repository.deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(fecha, 0, 0, 0L);
    }

}
