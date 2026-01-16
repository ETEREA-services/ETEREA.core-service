package eterea.core.service.service.facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import eterea.core.service.exception.FacturacionException;
import eterea.core.service.kotlin.exception.ReservaException;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.CuentaMovimiento;
import eterea.core.service.kotlin.model.Empresa;
import eterea.core.service.kotlin.model.Parametro;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ReservaArticulo;
import eterea.core.service.kotlin.model.ReservaContext;
import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.service.ArticuloMovimientoService;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.ReservaArticuloService;
import eterea.core.service.service.ReservaContextService;
import eterea.core.service.service.ReservaService;
import eterea.core.service.service.ValorMovimientoService;
import eterea.core.service.service.ValorService;
import eterea.core.service.service.VoucherService;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import eterea.core.service.validation.CuitValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FacturacionService {

   private final Logger log = LoggerFactory.getLogger(FacturacionService.class);

   private final VoucherService voucherService;
   private final OrderNoteService orderNoteService;
   private final ValorService valorService;
   private final ClienteMovimientoService clienteMovimientoService;
   private final ReservaContextService reservaContextService;
   private final ValorMovimientoService valorMovimientoService;
   private final ReservaArticuloService reservaArticuloService;
   private final ArticuloMovimientoService articuloMovimientoService;
   private final ReservaService reservaService;
   private final ContabilidadService contabilidadService;

   public FacturacionService(VoucherService voucherService,
         OrderNoteService orderNoteService,
         ValorService valorService,
         ClienteMovimientoService clienteMovimientoService,
         ReservaContextService reservaContextService,
         ValorMovimientoService valorMovimientoService,
         ReservaArticuloService reservaArticuloService,
         ArticuloMovimientoService articuloMovimientoService,
         ReservaService reservaService,
         ContabilidadService contabilidadService) {
      this.voucherService = voucherService;
      this.orderNoteService = orderNoteService;
      this.valorService = valorService;
      this.clienteMovimientoService = clienteMovimientoService;
      this.reservaContextService = reservaContextService;
      this.valorMovimientoService = valorMovimientoService;
      this.reservaArticuloService = reservaArticuloService;
      this.articuloMovimientoService = articuloMovimientoService;
      this.reservaService = reservaService;
      this.contabilidadService = contabilidadService;
   }

   @Transactional
   public ClienteMovimiento registraTransaccionFacturaProgramaDia(Reserva reserva, FacturacionDto facturacionDTO,
         Comprobante comprobante, Empresa empresa, Cliente cliente, Parametro parametro,
         ReservaContext reservaContext) {
      Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
      logVoucher(voucher);
      OrderNote orderNote = orderNoteService
            .findByOrderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())));
      logOrderNote(orderNote);
      int valorId = switch (Objects
            .requireNonNull(Objects.requireNonNull(orderNote.getPayment()).getMarcaTarjeta())) {
         case "American Express" -> 64;
         case "Cabal" -> 67;
         case "Cabal Du00e9bito" -> 66;
         case "Maestro" -> 61;
         case "MasterCard" -> 62;
         case "MasterCard Debito" -> 61;
         case "Tarjeta Naranja" -> 60;
         case "Visa Cru00e9dito" -> 60;
         case "Visa Debito" -> 59;
         default -> 0;
      };
      Valor valor = valorService.findByValorId(valorId);

      String observaciones = "Pedido web #" + orderNote.getOrderNumberId() + " - Reserva #"
            + reserva.getReservaId();
      // Registra clienteMovimiento
      ClienteMovimiento clienteMovimiento = new ClienteMovimiento.Builder()
            .negocioId(empresa.getNegocioId())
            .empresaId(empresa.getEmpresaId())
            .clienteId(cliente.getClienteId())
            .comprobanteId(comprobante.getComprobanteId())
            .fechaComprobante(ToolService.dateAbsoluteArgentina())
            .fechaVencimiento(ToolService.dateAbsoluteArgentina())
            .importe(facturacionDTO.getTotal())
            .cancelado(facturacionDTO.getTotal()) // contado
            .puntoVenta(comprobante.getPuntoVenta())
            .numeroComprobante(facturacionDTO.getNumeroComprobante())
            .montoIva(facturacionDTO.getIva())
            .montoIvaRni(facturacionDTO.getIva105())
            .neto(facturacionDTO.getNeto().add(facturacionDTO.getNeto105()))
            .letraComprobante(comprobante.getLetraComprobante())
            .montoExento(facturacionDTO.getExento())
            .reservaId(reserva.getReservaId())
            .cae(facturacionDTO.getCae())
            .caeVencimiento(facturacionDTO.getVencimientoCae())
            .monedaId(1)
            .cotizacion(BigDecimal.ONE)
            .letras(ToolService.number_2_text(facturacionDTO.getTotal()))
            .observaciones(observaciones)
            .build();
      clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);

      // Registra reservaContext
      reservaContext.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
      reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());

      // Registra valorMovimiento
      ValorMovimiento valorMovimiento = new ValorMovimiento.Builder()
            .negocioId(empresa.getNegocioId())
            .clienteId(cliente.getClienteId())
            .proveedorId(0L)
            .comprobanteId(comprobante.getComprobanteId())
            .fechaEmision(clienteMovimiento.getFechaComprobante())
            .fechaVencimiento(clienteMovimiento.getFechaComprobante())
            .valorId(valorId)
            .numeroComprobante(0L)
            .importe(facturacionDTO.getTotal())
            .numeroCuenta(valor.getNumeroCuenta())
            .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
            .proveedorMovimientoId(0L)
            .titular("")
            .banco("")
            .receptor("")
            .estadoId(0)
            .cierreCajaId(0L)
            .observaciones(observaciones)
            .build();
      valorMovimiento = valorMovimientoService.add(valorMovimiento);

      List<ArticuloMovimiento> articuloMovimientos = new ArrayList<>();
      int item = 1;
      for (ReservaArticulo reservaArticulo : reservaArticuloService
            .findAllByReservaIdWithArticulo(reserva.getReservaId())) {
         articuloMovimientos.add(new ArticuloMovimiento.Builder()
               .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
               .centroStockId(Objects.requireNonNull(reservaArticulo.getArticulo())
                     .getCentroStockId())
               .comprobanteId(comprobante.getComprobanteId())
               .item(item++)
               .articuloId(reservaArticulo.getArticuloId())
               .negocioId(clienteMovimiento.getNegocioId())
               .cantidad(new BigDecimal(-1 * reservaArticulo.getCantidad()))
               .precioUnitario(reservaArticulo.getPrecioUnitario())
               .precioUnitarioSinIva(calcularPrecioSinIva(reservaArticulo.getPrecioUnitario(),
                     reservaArticulo.getArticulo().getIva105(),
                     reservaArticulo.getArticulo().getExento(),
                     parametro))
               .precioUnitarioConIva(reservaArticulo.getPrecioUnitario())
               .numeroCuenta(reservaArticulo.getArticulo().getCuentaVentas())
               .iva105(reservaArticulo.getArticulo().getIva105())
               .exento(reservaArticulo.getArticulo().getExento())
               .fechaMovimiento(clienteMovimiento.getFechaComprobante())
               .fechaFactura(clienteMovimiento.getFechaComprobante())
               .precioCompra(reservaArticulo.getArticulo().getPrecioCompra())
               .build());
      }
      articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);

      List<CuentaMovimiento> clienteMovimientos = contabilidadService.registraContabilidadProgramaDia(
            clienteMovimiento, valorMovimiento, valor, articuloMovimientos, facturacionDTO,
            comprobante, parametro);

      reserva.setFacturada((byte) 1);
      reserva.setVerificada((byte) 1);
      reserva = reservaService.update(reserva, reserva.getReservaId());

      voucher.setConfirmado((byte) 1);
      voucher = voucherService.update(voucher, voucher.getVoucherId());

      return clienteMovimiento;

   }

   @Transactional
   public ClienteMovimiento registraTransaccionFacturaFaltante(ClienteMovimiento clienteMovimiento,
         ArticuloMovimiento articuloMovimiento) {
      log.debug("Processing FacturacionService.registraTransaccionFacturaFaltante");
      clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);
      logClienteMovimiento(clienteMovimiento);
      articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
      articuloMovimiento = articuloMovimientoService.add(articuloMovimiento);
      logArticuloMovimiento(articuloMovimiento);
      contabilidadService.registraFacturaFaltanteCuentaCorriente(clienteMovimiento, articuloMovimiento);
      return clienteMovimiento;
   }

   @Transactional
   public ClienteMovimiento registraTransaccionFacturaMultiplesPagos(
         Reserva reserva,
         FacturacionDto facturacionDTO,
         Comprobante comprobante,
         List<ValorMovimiento> valorMovimientos,
         Empresa empresa,
         Cliente cliente,
         Parametro parametro,
         ReservaContext reservaContext) {

      validateReserva(reserva);
      validateFacturacion(cliente, comprobante, valorMovimientos, facturacionDTO);

      int posicionIva = cliente.getPosicion().getPosicionId();
      String tipoComprobante = (posicionIva == 1 || posicionIva == 4) ? "A" : "B";

      Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
      logVoucher(voucher);

      String observaciones = "";
      // Registra clienteMovimiento
      ClienteMovimiento clienteMovimiento = new ClienteMovimiento.Builder()
            .negocioId(empresa.getNegocioId())
            .empresaId(empresa.getEmpresaId())
            .clienteId(cliente.getClienteId())
            .comprobanteId(comprobante.getComprobanteId())
            .fechaComprobante(ToolService.dateAbsoluteArgentina())
            .fechaVencimiento(ToolService.dateAbsoluteArgentina())
            .importe(facturacionDTO.getTotal())
            .cancelado(comprobante.getCuentaCorriente() != 0
                  ? BigDecimal.ZERO
                  : facturacionDTO.getTotal())
            .puntoVenta(comprobante.getPuntoVenta())
            .numeroComprobante(facturacionDTO.getNumeroComprobante())
            .montoIva(facturacionDTO.getIva())
            .montoIvaRni(facturacionDTO.getIva105())
            .neto(facturacionDTO.getNeto().add(facturacionDTO.getNeto105()))
            .letraComprobante(comprobante.getLetraComprobante())
            .montoExento(facturacionDTO.getExento())
            .reservaId(reserva.getReservaId())
            .cae(facturacionDTO.getCae())
            .caeVencimiento(facturacionDTO.getVencimientoCae())
            .monedaId(1)
            .cotizacion(BigDecimal.ONE)
            .letras(ToolService.number_2_text(facturacionDTO.getTotal()))
            .observaciones(observaciones)
            .build();
      clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);

      // Registra reservaContext
      reservaContext.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
      reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());

      List<ValorMovimiento> savedValorMovimientos = new ArrayList<>();
      for (ValorMovimiento vm : valorMovimientos) {
         ValorMovimiento valorMovimiento = new ValorMovimiento.Builder()
               .negocioId(empresa.getNegocioId())
               .clienteId(cliente.getClienteId())
               .proveedorId(0L)
               .comprobanteId(comprobante.getComprobanteId())
               .fechaEmision(clienteMovimiento.getFechaComprobante())
               .fechaVencimiento(clienteMovimiento.getFechaComprobante())
               .valorId(vm.getValor().getValorId())
               .valor(vm.getValor())
               .numeroComprobante(0L)
               .importe(vm.getImporte())
               .numeroCuenta(vm.getValor().getNumeroCuenta())
               .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
               .proveedorMovimientoId(0L)
               .titular("")
               .banco("")
               .receptor("")
               .estadoId(0)
               .cierreCajaId(0L)
               .observaciones(observaciones)
               .build();
         savedValorMovimientos.add(valorMovimientoService.add(valorMovimiento));
      }

      List<ArticuloMovimiento> articuloMovimientos = new ArrayList<>();
      int item = 1;
      for (ReservaArticulo reservaArticulo : reservaArticuloService
            .findAllByReservaIdWithArticulo(reserva.getReservaId())) {
         articuloMovimientos.add(new ArticuloMovimiento.Builder()
               .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
               .centroStockId(Objects.requireNonNull(reservaArticulo.getArticulo())
                     .getCentroStockId())
               .comprobanteId(comprobante.getComprobanteId())
               .item(item++)
               .articuloId(reservaArticulo.getArticuloId())
               .negocioId(clienteMovimiento.getNegocioId())
               .cantidad(new BigDecimal(-1 * reservaArticulo.getCantidad()))
               .precioUnitario(reservaArticulo.getPrecioUnitario())
               .precioUnitarioSinIva(calcularPrecioSinIva(reservaArticulo.getPrecioUnitario(),
                     reservaArticulo.getArticulo().getIva105(),
                     reservaArticulo.getArticulo().getExento(),
                     parametro))
               .precioUnitarioConIva(reservaArticulo.getPrecioUnitario())
               .numeroCuenta(reservaArticulo.getArticulo().getCuentaVentas())
               .iva105(reservaArticulo.getArticulo().getIva105())
               .exento(reservaArticulo.getArticulo().getExento())
               .fechaMovimiento(clienteMovimiento.getFechaComprobante())
               .fechaFactura(clienteMovimiento.getFechaComprobante())
               .precioCompra(reservaArticulo.getArticulo().getPrecioCompra())
               .build());
      }
      articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);

      List<Integer> valorIds = savedValorMovimientos.stream()
            .map(ValorMovimiento::getValorId)
            .distinct()
            .toList();
      List<Valor> valores = valorService.findAllByIds(valorIds);
      log.debug("valores={}", valores);

      List<CuentaMovimiento> clienteMovimientos = contabilidadService.registraContabilidad(
            clienteMovimiento, savedValorMovimientos, valores, articuloMovimientos, facturacionDTO,
            comprobante,
            parametro);

      reserva.setFacturada((byte) 1);
      reserva.setVerificada((byte) 1);
      reserva = reservaService.update(reserva, reserva.getReservaId());

      voucher.setConfirmado((byte) 1);
      voucher = voucherService.update(voucher, voucher.getVoucherId());

      return clienteMovimiento;
   }

   @Transactional
   public ClienteMovimiento registraTransaccionFacturaHotelMultiplesPagos(
         Reserva reserva,
         FacturacionDto facturacionDTO,
         Comprobante comprobante,
         List<ValorMovimiento> valorMovimientos,
         Empresa empresa,
         Cliente cliente,
         Parametro parametro) {

      validateReserva(reserva);
      validateFacturacion(cliente, comprobante, valorMovimientos, facturacionDTO);

      int posicionIva = cliente.getPosicion().getPosicionId();
      String tipoComprobante = (posicionIva == 1 || posicionIva == 4) ? "A" : "B";

      String observaciones = getObservacionesPrecioPromedio(reserva);
      // Registra clienteMovimiento
      ClienteMovimiento clienteMovimiento = new ClienteMovimiento.Builder()
            .negocioId(empresa.getNegocioId())
            .empresaId(empresa.getEmpresaId())
            .clienteId(cliente.getClienteId())
            .comprobanteId(comprobante.getComprobanteId())
            .fechaComprobante(ToolService.dateAbsoluteArgentina())
            .fechaVencimiento(ToolService.dateAbsoluteArgentina())
            .importe(facturacionDTO.getTotal())
            .cancelado(comprobante.getCuentaCorriente() != 0
                  ? BigDecimal.ZERO
                  : facturacionDTO.getTotal())
            .puntoVenta(comprobante.getPuntoVenta())
            .numeroComprobante(facturacionDTO.getNumeroComprobante())
            .montoIva(facturacionDTO.getIva())
            .montoIvaRni(facturacionDTO.getIva105())
            .neto(facturacionDTO.getNeto().add(facturacionDTO.getNeto105()))
            .letraComprobante(comprobante.getLetraComprobante())
            .montoExento(facturacionDTO.getExento())
            .reservaId(reserva.getReservaId())
            .cae(facturacionDTO.getCae())
            .caeVencimiento(facturacionDTO.getVencimientoCae())
            .monedaId(1)
            .cotizacion(BigDecimal.ONE)
            .letras(ToolService.number_2_text(facturacionDTO.getTotal()))
            .observaciones(observaciones)
            .build();
      clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);

      // Registra valorMovimiento
      List<ValorMovimiento> savedValorMovimientos = new ArrayList<>();
      for (ValorMovimiento vm : valorMovimientos) {
         ValorMovimiento valorMovimiento = new ValorMovimiento.Builder()
               .negocioId(empresa.getNegocioId())
               .clienteId(cliente.getClienteId())
               .proveedorId(0L)
               .comprobanteId(comprobante.getComprobanteId())
               .fechaEmision(clienteMovimiento.getFechaComprobante())
               .fechaVencimiento(clienteMovimiento.getFechaComprobante())
               .valorId(vm.getValor().getValorId())
               .valor(vm.getValor())
               .numeroComprobante(0L)
               .importe(vm.getImporte())
               .numeroCuenta(vm.getValor().getNumeroCuenta())
               .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
               .proveedorMovimientoId(0L)
               .titular("")
               .banco("")
               .receptor("")
               .estadoId(0)
               .cierreCajaId(0L)
               .observaciones(observaciones)
               .build();
         savedValorMovimientos.add(valorMovimientoService.add(valorMovimiento));
      }

      List<ArticuloMovimiento> articuloMovimientos = new ArrayList<>();
      int item = 1;
      for (ReservaArticulo reservaArticulo : reservaArticuloService
            .findAllByReservaIdWithArticulo(reserva.getReservaId())) {
         articuloMovimientos.add(new ArticuloMovimiento.Builder()
               .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
               .centroStockId(Objects.requireNonNull(reservaArticulo.getArticulo())
                     .getCentroStockId())
               .comprobanteId(comprobante.getComprobanteId())
               .item(item++)
               .articuloId(reservaArticulo.getArticuloId())
               .negocioId(clienteMovimiento.getNegocioId())
               .cantidad(new BigDecimal(-1 * reservaArticulo.getCantidad()))
               .precioUnitario(reservaArticulo.getPrecioUnitario())
               .precioUnitarioSinIva(calcularPrecioSinIva(reservaArticulo.getPrecioUnitario(),
                     reservaArticulo.getArticulo().getIva105(),
                     reservaArticulo.getArticulo().getExento(),
                     parametro))
               .precioUnitarioConIva(reservaArticulo.getPrecioUnitario())
               .numeroCuenta(reservaArticulo.getArticulo().getCuentaVentas())
               .iva105(reservaArticulo.getArticulo().getIva105())
               .exento(reservaArticulo.getArticulo().getExento())
               .fechaMovimiento(clienteMovimiento.getFechaComprobante())
               .fechaFactura(clienteMovimiento.getFechaComprobante())
               .precioCompra(reservaArticulo.getArticulo().getPrecioCompra())
               .build());
      }
      articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);

      List<Integer> valorIds = savedValorMovimientos.stream()
            .map(ValorMovimiento::getValorId)
            .distinct()
            .toList();
      List<Valor> valores = valorService.findAllByIds(valorIds);
      log.debug("valores={}", valores);

      List<CuentaMovimiento> clienteMovimientos = contabilidadService.registraContabilidad(
            clienteMovimiento, savedValorMovimientos, valores, articuloMovimientos, facturacionDTO,
            comprobante, parametro);

      reserva.setFacturada((byte) 1);
      reserva.setVerificada((byte) 1);
      reserva = reservaService.update(reserva, reserva.getReservaId());

      return clienteMovimiento;

   }

   private BigDecimal calcularPrecioSinIva(BigDecimal precioUnitario, byte iva105, byte exento,
         Parametro parametro) {
      if (exento == 1) {
         return precioUnitario;
      }
      var coeficiente = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
      if (iva105 == 1) {
         coeficiente = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
      }
      var precioUnitarioSinIva = precioUnitario.divide(BigDecimal.ONE.add(coeficiente), 5,
            RoundingMode.HALF_UP);
      return precioUnitarioSinIva.setScale(2, RoundingMode.HALF_UP);
   }

   private void logOrderNote(OrderNote orderNote) {
      try {
         log.debug("orderNote={}", JsonMapper
               .builder()
               .findAndAddModules()
               .build()
               .writerWithDefaultPrettyPrinter()
               .writeValueAsString(orderNote));
      } catch (JsonProcessingException e) {
         log.debug("orderNote jsonify error {}", e.getMessage());
      }
   }

   private void logVoucher(Voucher voucher) {
      try {
         log.debug("voucher={}", JsonMapper
               .builder()
               .findAndAddModules()
               .build()
               .writerWithDefaultPrettyPrinter()
               .writeValueAsString(voucher));
      } catch (JsonProcessingException e) {
         log.debug("voucher jsonify error {}", e.getMessage());
      }
   }

   private void logClienteMovimiento(ClienteMovimiento clienteMovimiento) {
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

   private void logArticuloMovimiento(ArticuloMovimiento articuloMovimiento) {
      try {
         log.debug("articuloMovimiento={}", JsonMapper
               .builder()
               .findAndAddModules()
               .build()
               .writerWithDefaultPrettyPrinter()
               .writeValueAsString(articuloMovimiento));
      } catch (JsonProcessingException e) {
         log.debug("articuloMovimiento jsonify error {}", e.getMessage());
      }
   }

   private String getObservacionesPrecioPromedio(Reserva reserva) {
      OffsetDateTime fechaIn = reserva.getFechaInServicio();
      OffsetDateTime fechaOutMinus1 = reserva.getFechaOutServicio().minusDays(1);

      boolean hasWeekdayPrice = false;
      boolean hasWeekendPrice = false;

      OffsetDateTime currentDate = fechaIn;
      while (!currentDate.isAfter(fechaOutMinus1)) {
         var dayOfWeek = currentDate.getDayOfWeek();

         if (dayOfWeek == java.time.DayOfWeek.FRIDAY || dayOfWeek == java.time.DayOfWeek.SATURDAY) {
            hasWeekendPrice = true;
         } else {
            hasWeekdayPrice = true;
         }

         if (hasWeekdayPrice && hasWeekendPrice) {
            return "Precio Unitario ARS corresponde al precio promedio por noche.";
         }

         currentDate = currentDate.plusDays(1);
      }

      return "";
   }

   private void validateReserva(Reserva reserva) {
      if (reserva.getAnulada() == (byte) 1) {
         throw new ReservaException("Reserva anulada");
      }
      if (reserva.getPagaCacheuta() == (byte) 1) {
         throw new ReservaException("Reserva marcada como Paga Cacheuta");
      }
      if (reserva.getFacturadoFuera() == (byte) 1) {
         throw new ReservaException("Reserva facturada en otro punto de venta");
      }
      if (reserva.getFacturada() == (byte) 1) {
         throw new ReservaException("Reserva ya facturada");
      }
      if (reserva.getCliente() == null) {
         throw new ReservaException("Reserva no tiene cliente asociado");
      }
   }

   private void validateFacturacion(Cliente cliente, Comprobante comprobante, List<ValorMovimiento> valorMovimientos,
         FacturacionDto facturacionDTO) {
      int posicionIva = cliente.getPosicion().getPosicionId();
      String tipoComprobante = (posicionIva == 1 || posicionIva == 4) ? "A" : "B";
      if (!comprobante.getLetraComprobante().equals(tipoComprobante)) {
         throw new FacturacionException("El tipo de comprobante " + comprobante.getLetraComprobante()
               + " no es apropiado para la posición del cliente " + posicionIva);
      }

      if (tipoComprobante.equals("A") && !CuitValidator.validate(cliente.getCuit())) {
         throw new FacturacionException("CUIT " + cliente.getCuit() + " inválido");
      }

      if (comprobante.getCuentaCorriente() != 0 && !valorMovimientos.isEmpty()) {
         throw new FacturacionException("CtaCte no debe contener Valor Movimientos");
      }

      if (comprobante.getCuentaCorriente() == 0 && valorMovimientos.isEmpty()) {
         throw new FacturacionException("CtaCte debe contener Valor Movimientos");
      }

      if (facturacionDTO.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
         throw new FacturacionException("Importe total debe ser mayor a 0");
      }

      if (comprobante.getCuentaCorriente() == 0) {
         BigDecimal total = facturacionDTO.getTotal();
         BigDecimal totalValorMovimientos = valorMovimientos.stream()
               .map(ValorMovimiento::getImporte)
               .reduce(BigDecimal.ZERO, BigDecimal::add);
         if (total.compareTo(totalValorMovimientos) != 0) {
            throw new FacturacionException(
                  "Importe total no coincide con la suma de los importes de Valor Movimientos");
         }
      }

   }

}
