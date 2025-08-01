package eterea.core.service.service.facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import eterea.core.service.client.reservas.PluspagosClient;
import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.CuentaMovimiento;
import eterea.core.service.kotlin.model.Parametro;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.model.dto.pluspagos.ApiResponseDto;
import eterea.core.service.model.dto.pluspagos.PluspagosTransactionDto;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.ComprobanteService;
import eterea.core.service.service.CuentaMovimientoService;
import eterea.core.service.service.ParametroService;
import eterea.core.service.service.ReservaService;
import eterea.core.service.service.ValorMovimientoService;
import eterea.core.service.service.ValorService;
import eterea.core.service.service.VoucherService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
@Slf4j
public class ContabilidadService {

   private final CuentaMovimientoService cuentaMovimientoService;
   private final ClienteMovimientoService clienteMovimientoService;
   private final ValorMovimientoService valorMovimientoService;
   private final ParametroService parametroService;
   private final ComprobanteService comprobanteService;
   private final PluspagosClient pluspagosClient;
   private final ReservaService reservaService;
   private final VoucherService voucherService;
   private final ValorService valorService;

   public ContabilidadService(CuentaMovimientoService cuentaMovimientoService,
         ClienteMovimientoService clienteMovimientoService, ValorMovimientoService valorMovimientoService,
         ParametroService parametroService, ComprobanteService comprobanteService,
         PluspagosClient pluspagosClient,
         ReservaService reservaService, VoucherService voucherService, ValorService valorService) {
      this.cuentaMovimientoService = cuentaMovimientoService;
      this.clienteMovimientoService = clienteMovimientoService;
      this.valorMovimientoService = valorMovimientoService;
      this.parametroService = parametroService;
      this.comprobanteService = comprobanteService;
      this.pluspagosClient = pluspagosClient;
      this.reservaService = reservaService;
      this.voucherService = voucherService;
      this.valorService = valorService;
   }

   @Transactional
   public List<CuentaMovimiento> registraContabilidadProgramaDia(ClienteMovimiento clienteMovimiento,
         ValorMovimiento valorMovimiento, Valor valor, List<ArticuloMovimiento> articuloMovimientos,
         FacturacionDto facturacionDTO, Comprobante comprobante, Parametro parametro) {
      List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
      int ordenContable = cuentaMovimientoService.nextOrdenContable(clienteMovimiento.getFechaComprobante());
      // Agrego asiento contable a clienteMovimiento
      clienteMovimiento.setFechaContable(clienteMovimiento.getFechaComprobante());
      clienteMovimiento.setOrdenContable(ordenContable);
      clienteMovimiento = clienteMovimientoService.update(clienteMovimiento,
            clienteMovimiento.getClienteMovimientoId());
      // Agrego asiento contable a valorMovimiento
      valorMovimiento.setFechaContable(clienteMovimiento.getFechaContable());
      valorMovimiento.setOrdenContable(ordenContable);
      valorMovimiento = valorMovimientoService.update(valorMovimiento, valorMovimiento.getValorMovimientoId());
      int item = 1;
      String concepto = String.format("Nro: %04d %06d", facturacionDTO.getPuntoVenta(),
            facturacionDTO.getNumeroComprobante());
      // Registro total valores
      cuentaMovimientos.add(new CuentaMovimiento.Builder()
            .negocioId(clienteMovimiento.getNegocioId())
            .numeroCuenta(valor.getNumeroCuenta())
            .debita(comprobante.getDebita())
            .importe(facturacionDTO.getTotal())
            .item(item++)
            .fecha(clienteMovimiento.getFechaComprobante())
            .comprobanteId(comprobante.getComprobanteId())
            .orden(ordenContable)
            .clienteId(clienteMovimiento.getClienteId())
            .subrubroId(2L)
            .concepto(concepto)
            .build());
      // Registro iva 21
      if (facturacionDTO.getIva().compareTo(BigDecimal.ZERO) > 0) {
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(parametro.getCuentaIvaVentas())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(facturacionDTO.getIva())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .build());
      }
      // Registro iva 10.5
      if (facturacionDTO.getIva105().compareTo(BigDecimal.ZERO) > 0) {
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(parametro.getCuentaIvaRniVentas())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(facturacionDTO.getIva105())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .build());
      }
      // Registro de artículos
      for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
         assert articuloMovimiento.getArticuloMovimientoId() != null;
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(articuloMovimiento.getNumeroCuenta())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(Objects.requireNonNull(articuloMovimiento.getPrecioUnitarioSinIva())
                     .multiply(articuloMovimiento.getCantidad()).setScale(2, RoundingMode.HALF_UP)
                     .abs())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .articuloMovimientoId(articuloMovimiento.getArticuloMovimientoId())
               .build());
      }

      cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
      logCuentaMovimientos(cuentaMovimientos);
      return cuentaMovimientos;
   }

   public void registraFacturaFaltanteCuentaCorriente(ClienteMovimiento clienteMovimiento,
         ArticuloMovimiento articuloMovimiento) {
      log.debug("Processing ContabilidadService.registraFacturaFaltanteCuentaCorriente");
      var parametro = parametroService.findTop();
      var comprobante = comprobanteService.findByComprobanteId(clienteMovimiento.getComprobanteId());
      List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
      int ordenContable = cuentaMovimientoService.nextOrdenContable(clienteMovimiento.getFechaComprobante());
      // Agrego asiento contable a clienteMovimiento
      clienteMovimiento.setFechaContable(clienteMovimiento.getFechaComprobante());
      clienteMovimiento.setOrdenContable(ordenContable);
      clienteMovimiento = clienteMovimientoService.update(clienteMovimiento,
            clienteMovimiento.getClienteMovimientoId());
      logClienteMovimiento(clienteMovimiento);
      int item = 1;
      String concepto = String.format("Nro: %04d %06d", clienteMovimiento.getPuntoVenta(),
            clienteMovimiento.getNumeroComprobante());
      // Registro total deudores por ventas
      cuentaMovimientos.add(new CuentaMovimiento.Builder()
            .negocioId(clienteMovimiento.getNegocioId())
            .numeroCuenta(12101001L)
            .debita((byte) 1)
            .importe(clienteMovimiento.getImporte())
            .item(item++)
            .fecha(clienteMovimiento.getFechaComprobante())
            .comprobanteId(clienteMovimiento.getComprobanteId())
            .orden(ordenContable)
            .clienteId(clienteMovimiento.getClienteId())
            .subrubroId(2L)
            .concepto(concepto)
            .build());
      // Registro iva 21
      if (clienteMovimiento.getMontoIva().compareTo(BigDecimal.ZERO) > 0) {
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(parametro.getCuentaIvaVentas())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(clienteMovimiento.getMontoIva())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .build());
      }
      // Registro iva 10.5
      if (clienteMovimiento.getMontoIvaRni().compareTo(BigDecimal.ZERO) > 0) {
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(parametro.getCuentaIvaRniVentas())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(clienteMovimiento.getMontoIvaRni())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .build());
      }
      // Registro de artículo
      assert articuloMovimiento.getArticuloMovimientoId() != null;
      cuentaMovimientos.add(new CuentaMovimiento.Builder()
            .negocioId(clienteMovimiento.getNegocioId())
            .numeroCuenta(articuloMovimiento.getNumeroCuenta())
            .debita((byte) (1 - comprobante.getDebita()))
            .importe(clienteMovimiento.getNeto().abs())
            .item(item++)
            .fecha(clienteMovimiento.getFechaComprobante())
            .comprobanteId(comprobante.getComprobanteId())
            .orden(ordenContable)
            .clienteId(clienteMovimiento.getClienteId())
            .subrubroId(2L)
            .concepto(concepto)
            .articuloMovimientoId(articuloMovimiento.getArticuloMovimientoId())
            .build());

      cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
      logCuentaMovimientos(cuentaMovimientos);
   }

   private void logCuentaMovimientos(List<CuentaMovimiento> cuentaMovimientos) {
      log.debug("Processing ContabilidadService.logCuentaMovimientos");
      try {
         log.debug("cuentaMovimientos={}", JsonMapper
               .builder()
               .findAndAddModules()
               .build()
               .writerWithDefaultPrettyPrinter()
               .writeValueAsString(cuentaMovimientos));
      } catch (JsonProcessingException e) {
         log.debug("cuentaMovimientos jsonify error {}", e.getMessage());
      }
   }

   private void logClienteMovimiento(ClienteMovimiento clienteMovimiento) {
      log.debug("Processing ContabilidadService.logClienteMovimiento");
      try {
         log.debug("clienteMovimiento={}", JsonMapper
               .builder()
               .findAndAddModules()
               .build()
               .writerWithDefaultPrettyPrinter()
               .writeValueAsString(clienteMovimiento));
      } catch (JsonProcessingException e) {
         log.debug("clienteMovimiento jsonify error {}", e.getMessage());
      }
   }

   /**
    * 
    * @author sebastian
    * @
    * 
    */

   public List<CuentaMovimiento> registraContabilidad(ClienteMovimiento clienteMovimiento,
         List<ValorMovimiento> valorMovimientos,
         List<Valor> valores,
         List<ArticuloMovimiento> articuloMovimientos,
         FacturacionDto facturacionDTO,
         Comprobante comprobante,
         Parametro parametro) {

      List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
      int ordenContable = cuentaMovimientoService.nextOrdenContable(clienteMovimiento.getFechaComprobante());
      // Agrego asiento contable a clienteMovimiento
      clienteMovimiento.setFechaContable(clienteMovimiento.getFechaComprobante());
      clienteMovimiento.setOrdenContable(ordenContable);
      clienteMovimiento = clienteMovimientoService.update(clienteMovimiento,
            clienteMovimiento.getClienteMovimientoId());

      // Agrego asiento contable a valorMovimiento
      for (ValorMovimiento vm : valorMovimientos) {
         vm.setFechaContable(clienteMovimiento.getFechaContable());
         vm.setOrdenContable(ordenContable);
         valorMovimientoService.update(vm, vm.getValorMovimientoId());
      }

      int item = 1;
      String concepto = String.format("Nro: %04d %06d", facturacionDTO.getPuntoVenta(),
            facturacionDTO.getNumeroComprobante());

      for (ValorMovimiento vm : valorMovimientos) {
         // Registro total valores
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(vm.getValor().getNumeroCuenta())
               .debita(comprobante.getDebita())
               .importe(vm.getImporte())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .build());
      }
      // Registro iva 21
      if (facturacionDTO.getIva().compareTo(BigDecimal.ZERO) > 0) {
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(parametro.getCuentaIvaVentas())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(facturacionDTO.getIva())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .build());
      }
      // Registro iva 10.5
      if (facturacionDTO.getIva105().compareTo(BigDecimal.ZERO) > 0) {
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(parametro.getCuentaIvaRniVentas())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(facturacionDTO.getIva105())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .build());
      }
      // Registro de artículos
      for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
         assert articuloMovimiento.getArticuloMovimientoId() != null;
         cuentaMovimientos.add(new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(articuloMovimiento.getNumeroCuenta())
               .debita((byte) (1 - comprobante.getDebita()))
               .importe(Objects.requireNonNull(articuloMovimiento.getPrecioUnitarioSinIva())
                     .multiply(articuloMovimiento.getCantidad()).setScale(2, RoundingMode.HALF_UP)
                     .abs())
               .item(item++)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(ordenContable)
               .clienteId(clienteMovimiento.getClienteId())
               .subrubroId(2L)
               .concepto(concepto)
               .articuloMovimientoId(articuloMovimiento.getArticuloMovimientoId())
               .build());
      }

      cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
      logCuentaMovimientos(cuentaMovimientos);
      return cuentaMovimientos;

   }

   public List<ClienteMovimiento> checkOrphanFacturas(OffsetDateTime desde, OffsetDateTime hasta, Integer comprobanteId) {
      log.info("Verificando facturas desde {} hasta {} con comprobanteId={}", desde, hasta, comprobanteId);
      List<ClienteMovimiento> clienteMovimientos = clienteMovimientoService.findByFechaComprobanteBetween(desde, hasta);
      List<Long> clienteMovimientoIds = clienteMovimientos.stream()
            .map(ClienteMovimiento::getClienteMovimientoId)
            .toList();

      log.info("Facturas encontradas: {}", clienteMovimientos.size());

      List<ValorMovimiento> valorMovimientosWithCliente = valorMovimientoService
            .findAllByFechaContableBetweenAndComprobanteIdAndClienteMovimientoIdIn(desde,
                  hasta,
                  comprobanteId,
                  clienteMovimientoIds);

      // Extract clienteMovimientoIds that DO have corresponding ValorMovimientos
      List<Long> clienteMovimientoIdsWithValor = valorMovimientosWithCliente.stream()
            .map(ValorMovimiento::getClienteMovimientoId)
            .toList();

      // Filter clienteMovimientos to keep only those that DON'T have corresponding
      // ValorMovimientos
      List<ClienteMovimiento> clienteMovimientosSinValor = clienteMovimientos.stream()
            .filter(cm -> !clienteMovimientoIdsWithValor.contains(cm.getClienteMovimientoId()))
            .toList();

      log.info("Facturas sin ValorMovimiento asociado: {}. Total: {}",
            clienteMovimientosSinValor.stream()
                  .map(ClienteMovimiento::getNumeroComprobante)
                  .toList(),
            clienteMovimientosSinValor.size());

      List<Long> hardcodedReservaIds = List.of(
         466681L,466690L,466691L,466692L,466694L,466695L,
         466696L, 466698L, 466721L,466763L,466764L,466766L,
         466793L,466794L,466796L, 466798L,466799L,466802L,
         466804L, 466805L, 466808L, 466809L, 466810L, 466811L,
         466813L, 466814L,466815L,466816L,466819L,466820L,466821L,
         466822L,466823L,466847L,466849L,466850L,466852L,466882L,
         466883L,466884L,466913L,466914L,466915L,466916L,466917L,
         466918L,466919L,466913L,466920L,466922L,466924L,466925L
      );

      List<ClienteMovimiento> hardcodedClienteMovimientosSinValor = clienteMovimientoService.findAllByReservaIds(hardcodedReservaIds);

      return hardcodedClienteMovimientosSinValor;
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

   public void fixOrphanFacturas(List<ClienteMovimiento> orphanFacturas, Integer comprobanteId) {
      Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);

      Map<Long, Integer> valoresPluspagosMap = new HashMap<>();
      valoresPluspagosMap.put(7L, 59);
      valoresPluspagosMap.put(4L, 60);
      valoresPluspagosMap.put(36L, 61);
      valoresPluspagosMap.put(9L, 62);
      valoresPluspagosMap.put(34L, 64);
      valoresPluspagosMap.put(37L, 66);
      valoresPluspagosMap.put(12L, 67);
      valoresPluspagosMap.put(47L, 72);

      for (ClienteMovimiento clienteMovimiento : orphanFacturas) {
         Cliente cliente = clienteMovimiento.getCliente();
         Reserva reserva = reservaService.findByReservaId(clienteMovimiento.getReservaId());
         Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
         log.info("--------------- Factura N° {} ---------------", clienteMovimiento.getNumeroComprobante());
         log.info(
               "Buscando transacción en Pluspagos para Orden web: {} correspondiente a Factura {}",
               voucher.getNumeroVoucher(),
               clienteMovimiento.getNumeroComprobante());

         ApiResponseDto<PluspagosTransactionDto> response = getTransactionFromPluspagos(voucher.getNumeroVoucher());

         if (response == null || response.data() == null) {
            log.info("No se encontró transacción en Pluspagos correspondiente");
            continue; // Skip this factura and continue with the next one
         }

         PluspagosTransactionDto transaction = response.data();
         log.info("Transacción encontrada en Pluspagos: {}", transaction);

         Integer valorId = valoresPluspagosMap.get(transaction.medioPagoId());
         Valor valor = valorService.findByValorId(valorId);

         String concepto = String.format("Nro: %04d %06d", clienteMovimiento.getPuntoVenta(),
               clienteMovimiento.getNumeroComprobante());
         List<CuentaMovimiento> cuentaMovimientos = cuentaMovimientoService
               .findAllByClienteIdAndComprobanteIdAndConceptoLike(cliente.getClienteId(),
                     comprobante.getComprobanteId(), concepto);
         log.info("CuentaMovimientos encontrados: {}", cuentaMovimientos);
         if (cuentaMovimientos.isEmpty()) {
            log.info("No se encontró cuentaMovimiento para la Factura N° {}", clienteMovimiento.getNumeroComprobante());
            continue; // Skip this factura and continue with the next one
         }

         int lastItem = cuentaMovimientos.stream()
               .map(CuentaMovimiento::getItem)
               .max(Integer::compareTo)
               .orElse(0);
         CuentaMovimiento newCuentaMovimiento = new CuentaMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .numeroCuenta(valor.getNumeroCuenta())
               .debita(comprobante.getDebita())
               .importe(clienteMovimiento.getImporte())
               .item(lastItem + 1)
               .fecha(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .orden(clienteMovimiento.getOrdenContable())
               .clienteId(cliente.getClienteId())
               .subrubroId(2L)
               .concepto(cuentaMovimientos.get(0).getConcepto())
               .build();

         cuentaMovimientoService.saveAll(List.of(newCuentaMovimiento));
         log.info("CuentaMovimiento agregada: {}", newCuentaMovimiento);

         ValorMovimiento newValorMovimiento = new ValorMovimiento.Builder()
               .negocioId(clienteMovimiento.getNegocioId())
               .valorId(valorId)
               .proveedorId(0L)
               .clienteId(cliente.getClienteId())
               .fechaEmision(clienteMovimiento.getFechaComprobante())
               .fechaVencimiento(clienteMovimiento.getFechaComprobante())
               .comprobanteId(comprobante.getComprobanteId())
               .numeroComprobante(0L)
               .importe(clienteMovimiento.getImporte())
               .numeroCuenta(valor.getNumeroCuenta())
               .fechaContable(clienteMovimiento.getFechaComprobante())
               .ordenContable(clienteMovimiento.getOrdenContable())
               .proveedorMovimientoId(0L)
               .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
               .titular("")
               .banco("")
               .receptor("")
               .estadoId(0)
               .cierreCajaId(0L)
               .observaciones(clienteMovimiento.getObservaciones())
               .valor(valor)
               .build();
         valorMovimientoService.add(newValorMovimiento);
         log.info("ValorMovimiento agregado: {}", newValorMovimiento);
      }

   }

}
