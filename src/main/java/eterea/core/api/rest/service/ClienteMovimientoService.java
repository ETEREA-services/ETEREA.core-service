/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import eterea.core.api.rest.exception.ClienteMovimientoException;
import eterea.core.api.rest.kotlin.model.ClienteMovimiento;
import eterea.core.api.rest.repository.ClienteMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ClienteMovimientoService {

	@Autowired
	private ClienteMovimientoRepository repository;

	@Autowired
	private ComprobanteService comprobanteService;

	public List<ClienteMovimiento> findTop200Asociables(Long clienteId) {
		List<Integer> comprobanteIds = comprobanteService.findAllAsociables().stream()
				.map(comprobante -> comprobante.getComprobanteId()).collect(Collectors.toList());
		return repository.findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId, comprobanteIds);
	}

	public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
		return repository.findAllByReservaIdIn(reservaIds);
	}

	public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
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
		return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc(0, puntoVenta,
				letraComprobante).map(clienteMovimiento -> {
					return 1 + clienteMovimiento.getNumeroComprobante();
				}).orElse(1L);
	}

}
