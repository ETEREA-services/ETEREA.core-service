/**
 * 
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import eterea.core.service.exception.ClienteMovimientoException;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.model.dto.pluspagos.ApiResponseDto;
import eterea.core.service.model.dto.pluspagos.PluspagosTransactionDto;
import eterea.core.service.repository.ClienteMovimientoRepository;
import jakarta.transaction.Transactional;

/**
 * @author daniel
 *
 */
@Service
public class ClienteMovimientoService {
	Logger log = LoggerFactory.getLogger(ClienteMovimientoService.class);

	private final ClienteMovimientoRepository repository;
	private final ComprobanteService comprobanteService;
	private final ValorMovimientoService valorMovimientoService;

	public ClienteMovimientoService(
			ClienteMovimientoRepository repository,
			ComprobanteService comprobanteService,
			ValorMovimientoService valorMovimientoService) {
		this.repository = repository;
		this.comprobanteService = comprobanteService;
		this.valorMovimientoService = valorMovimientoService;
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
		return repository.findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(fecha, 0, (byte) 1);
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

	/*
 	* @author Sebastian
 	* Metodo existFactura extraido de VB6
 	*/
	 public ClienteMovimiento findFirstClienteMovimientoByReservaId(Long reservaId) {
		return repository.findFirstByReservaId(reservaId)
				.orElseThrow(() -> new ClienteMovimientoException(reservaId));
	}

	public List<ClienteMovimiento> findByComprobanteIdAndFechaComprobanteBetween(Integer comprobanteId, OffsetDateTime desde, OffsetDateTime hasta) {
		return repository.findByComprobanteIdAndFechaComprobanteBetween(comprobanteId, desde, hasta);
	}

   public Slice<ClienteMovimiento> findByComprobanteIdAndFechaComprobanteBetweenSliced(Integer comprobanteId, OffsetDateTime desde, OffsetDateTime hasta, Pageable pageable) {
      return repository.findSliceByComprobanteIdAndFechaComprobanteBetween(comprobanteId, desde, hasta, pageable);
   }

   public List<ClienteMovimiento> findOrphanFacturas(OffsetDateTime desde, OffsetDateTime hasta, Integer comprobanteId) {
      log.info("Verificando facturas desde {} hasta {} con comprobanteId={}", desde, hasta, comprobanteId);
      List<ClienteMovimiento> clienteMovimientos = findByComprobanteIdAndFechaComprobanteBetween(comprobanteId, desde, hasta);
      List<Long> clienteMovimientoIds = clienteMovimientos.stream()
            .map(ClienteMovimiento::getClienteMovimientoId)
            .toList();

      log.info("Facturas encontradas: {}", clienteMovimientos.size());

      List<ValorMovimiento> valorMovimientosWithCliente = valorMovimientoService
            .findAllByClienteMovimientoIdIn(clienteMovimientoIds);

      List<Long> clienteMovimientoIdsWithValor = valorMovimientosWithCliente.stream()
            .map(ValorMovimiento::getClienteMovimientoId)
            .toList();

      List<ClienteMovimiento> clienteMovimientosSinValor = clienteMovimientos.stream()
            .filter(cm -> !clienteMovimientoIdsWithValor.contains(cm.getClienteMovimientoId()))
            .toList();

      log.info("Facturas sin ValorMovimiento asociado: {}. Total: {}",
            clienteMovimientosSinValor.stream()
                  .map(ClienteMovimiento::getNumeroComprobante)
                  .toList(),
            clienteMovimientosSinValor.size());

      return clienteMovimientosSinValor;
   }

   private ApiResponseDto<PluspagosTransactionDto> getTransactionFromPluspagos(String orderNoteId) {
      try {
         RestTemplate restTemplate = new RestTemplate();
         String url = "http://10.147.17.148:8080/api/v1/pluspagos/transactions/order-note/" + orderNoteId;
         log.info("Calling Pluspagos API: {}", url);
         
         // Use ParameterizedTypeReference to preserve generic type information
         ParameterizedTypeReference<ApiResponseDto<PluspagosTransactionDto>> typeRef = 
            new ParameterizedTypeReference<ApiResponseDto<PluspagosTransactionDto>>() {};
         
         ResponseEntity<ApiResponseDto<PluspagosTransactionDto>> response = 
            restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
         
         if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
         } else {
            log.info("Failed to get transaction from Pluspagos. Status: {}", response.getStatusCode());
            return null;
         }
         
      } catch (RestClientException e) {
         log.info("Error calling Pluspagos service for orderNote {}: {}", orderNoteId, e.getMessage());
         return null;
      }
   }

   // public void fixOrphanFacturas(List<ClienteMovimiento> orphanFacturas, Integer comprobanteId) {
   //    Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);

   //    Map<Long, Integer> valoresPluspagosMap = new HashMap<>();
   //    valoresPluspagosMap.put(7L, 59);
   //    valoresPluspagosMap.put(4L, 60);
   //    valoresPluspagosMap.put(36L, 61);
   //    valoresPluspagosMap.put(9L, 62);
   //    valoresPluspagosMap.put(34L, 64);
   //    valoresPluspagosMap.put(37L, 66);
   //    valoresPluspagosMap.put(12L, 67);
   //    valoresPluspagosMap.put(47L, 72);

   //    for (ClienteMovimiento clienteMovimiento : orphanFacturas) {
   //       Cliente cliente = clienteMovimiento.getCliente();
   //       Reserva reserva = reservaService.findByReservaId(clienteMovimiento.getReservaId());
   //       Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
   //       log.info("--------------- Factura N° {} - Reserva N° {} ---------------", clienteMovimiento.getNumeroComprobante(), reserva.getReservaId());
   //       log.info(
   //             "Buscando transacción en Pluspagos para Orden web: {} correspondiente a Factura {}",
   //             voucher.getNumeroVoucher(),
   //             clienteMovimiento.getNumeroComprobante());

   //       ApiResponseDto<PluspagosTransactionDto> response = getTransactionFromPluspagos(voucher.getNumeroVoucher());

   //       if (response == null || response.data() == null) {
   //          log.info("No se encontró transacción en Pluspagos correspondiente");
   //          continue; // Skip this factura and continue with the next one
   //       }

   //       PluspagosTransactionDto transaction = response.data();
   //       log.info("Transacción encontrada en Pluspagos: {}", transaction);

   //       Long medioPagoId = transaction.medioPagoId();
   //       if (medioPagoId == 11L) medioPagoId = 4L;
   //       Integer valorId = valoresPluspagosMap.get(medioPagoId);
   //       Valor valor = valorService.findByValorId(valorId);

   //       String concepto = String.format("Nro: %04d %06d", clienteMovimiento.getPuntoVenta(),
   //             clienteMovimiento.getNumeroComprobante());
   //       List<CuentaMovimiento> cuentaMovimientos = cuentaMovimientoService
   //             .findAllByClienteIdAndComprobanteIdAndConceptoLike(cliente.getClienteId(),
   //                   comprobante.getComprobanteId(), concepto);
   //       log.info("CuentaMovimientos encontrados: {}", cuentaMovimientos);
   //       if (cuentaMovimientos.isEmpty()) {
   //          log.info("No se encontró cuentaMovimiento para la Factura N° {}", clienteMovimiento.getNumeroComprobante());
   //          continue; // Skip this factura and continue with the next one
   //       }

   //       int lastItem = cuentaMovimientos.stream()
   //             .map(CuentaMovimiento::getItem)
   //             .max(Integer::compareTo)
   //             .orElse(0);
   //       CuentaMovimiento newCuentaMovimiento = new CuentaMovimiento.Builder()
   //             .negocioId(clienteMovimiento.getNegocioId())
   //             .numeroCuenta(valor.getNumeroCuenta())
   //             .debita(comprobante.getDebita())
   //             .importe(clienteMovimiento.getImporte())
   //             .item(lastItem + 1)
   //             .fecha(clienteMovimiento.getFechaComprobante())
   //             .comprobanteId(comprobante.getComprobanteId())
   //             .orden(clienteMovimiento.getOrdenContable())
   //             .clienteId(cliente.getClienteId())
   //             .subrubroId(2L)
   //             .concepto(cuentaMovimientos.get(0).getConcepto())
   //             .build();

   //       cuentaMovimientoService.saveAll(List.of(newCuentaMovimiento));
   //       log.info("CuentaMovimiento agregada: {}", newCuentaMovimiento);

   //       ValorMovimiento newValorMovimiento = new ValorMovimiento.Builder()
   //             .negocioId(clienteMovimiento.getNegocioId())
   //             .valorId(valorId)
   //             .proveedorId(0L)
   //             .clienteId(cliente.getClienteId())
   //             .fechaEmision(clienteMovimiento.getFechaComprobante())
   //             .fechaVencimiento(clienteMovimiento.getFechaComprobante())
   //             .comprobanteId(comprobante.getComprobanteId())
   //             .numeroComprobante(0L)
   //             .importe(clienteMovimiento.getImporte())
   //             .numeroCuenta(valor.getNumeroCuenta())
   //             .fechaContable(clienteMovimiento.getFechaComprobante())
   //             .ordenContable(clienteMovimiento.getOrdenContable())
   //             .proveedorMovimientoId(0L)
   //             .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
   //             .titular("")
   //             .banco("")
   //             .receptor("")
   //             .estadoId(0)
   //             .cierreCajaId(0L)
   //             .observaciones(clienteMovimiento.getObservaciones())
   //             .valor(valor)
   //             .build();
   //       valorMovimientoService.add(newValorMovimiento);
   //       log.info("ValorMovimiento agregado: {}", newValorMovimiento);
   //    }
   // }



}
