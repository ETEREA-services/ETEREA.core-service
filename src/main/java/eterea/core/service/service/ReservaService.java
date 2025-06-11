/**
 *
 */
package eterea.core.service.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import eterea.core.service.exception.ArticuloException;
import eterea.core.service.exception.ClienteMovimientoException;
import eterea.core.service.kotlin.exception.ReservaException;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.ConceptoFacturado;
import eterea.core.service.kotlin.model.Empresa;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ReservaArticulo;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.kotlin.repository.ReservaRepository;
import eterea.core.service.model.dto.reserva.CreateReservaDto;
import eterea.core.service.service.facade.PrecioService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 */
@Service
@Slf4j
public class ReservaService {

   private final ReservaRepository repository;
   private final ClienteMovimientoService clienteMovimientoService;
   private final VoucherService voucherService;
   private final ComprobanteService comprobanteService;
   private final ReservaArticuloService reservaArticuloService;
   private final ArticuloService articuloService;
   private final ArticuloMovimientoService articuloMovimientoService;
   private final ConceptoFacturadoService conceptoFacturadoService;
   private final EmpresaService empresaService;
   private final VoucherProductoService voucherProductoService;
   private final PrecioService precioService;
   private final ClienteService clienteService;

   public ReservaService(ReservaRepository repository,
         ClienteMovimientoService clienteMovimientoService,
         VoucherService voucherService,
         ComprobanteService comprobanteService,
         ReservaArticuloService reservaArticuloService,
         ArticuloService articuloService,
         ArticuloMovimientoService articuloMovimientoService,
         ConceptoFacturadoService conceptoFacturadoService,
         EmpresaService empresaService,
         VoucherProductoService voucherProductoService,
         PrecioService precioService,
         ClienteService clienteService) {
      this.repository = repository;
      this.clienteMovimientoService = clienteMovimientoService;
      this.voucherService = voucherService;
      this.comprobanteService = comprobanteService;
      this.reservaArticuloService = reservaArticuloService;
      this.articuloService = articuloService;
      this.articuloMovimientoService = articuloMovimientoService;
      this.conceptoFacturadoService = conceptoFacturadoService;
      this.empresaService = empresaService;
      this.voucherProductoService = voucherProductoService;
      this.precioService = precioService;
      this.clienteService = clienteService;
   }

   public List<Reserva> findTopPendientes() {
      return repository
            .findTop100ByVerificadaAndFacturadaAndEliminadaAndPagaCacheutaAndFacturadoFueraAndAnuladaAndClienteIdGreaterThan(
                  (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 0L,
                  Sort.by("fechaToma").descending().and(Sort.by("clienteId")));
   }

   public Reserva findByReservaId(Long reservaId) {
      return repository.findByReservaId(reservaId).orElseThrow(() -> new ReservaException(reservaId));
   }

   @Transactional
   public void completeArticulos(Long clienteMovimientoId) {
      ClienteMovimiento clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);
      try {
         log.debug("ClienteMovimiento = {}", JsonMapper.builder().findAndAddModules().build()
               .writerWithDefaultPrettyPrinter().writeValueAsString(clienteMovimiento));
      } catch (JsonProcessingException e) {
         log.debug("ClienteMovimiento error = {}", e.getMessage());
      }

      if (clienteMovimiento.getReservaId() == 0)
         return;
      Reserva reserva = repository.findByReservaId(clienteMovimiento.getReservaId()).get();
      try {
         log.debug("Reserva = {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(reserva));
      } catch (JsonProcessingException e) {
         log.debug("Reserva error = {}", e.getMessage());
      }
      Comprobante comprobante = comprobanteService.findByComprobanteId(clienteMovimiento.getComprobanteId());
      try {
         log.debug("Comprobante = {}", JsonMapper.builder().findAndAddModules().build()
               .writerWithDefaultPrettyPrinter().writeValueAsString(comprobante));
      } catch (JsonProcessingException e) {
         log.debug("Comprobante error = {}", e.getMessage());
      }
      String numeroVoucher = "";
      Voucher voucher = null;
      if (reserva.getVoucherId() != null && reserva.getVoucherId() > 0) {
         voucher = voucherService.findByReservaId(clienteMovimiento.getReservaId());
         if (!voucher.getNumeroVoucher().trim().isEmpty())
            numeroVoucher = " - No.Voucher: " + voucher.getNumeroVoucher();
      }
      int item = 0;
      List<ArticuloMovimiento> articuloMovs = new ArrayList<>();

      for (ReservaArticulo reservaArticulo : reservaArticuloService
            .findAllByReservaId(clienteMovimiento.getReservaId())) {
         try {
            log.debug("ReservaArticulo = {}", JsonMapper.builder().findAndAddModules().build()
                  .writerWithDefaultPrettyPrinter().writeValueAsString(reservaArticulo));
         } catch (JsonProcessingException e) {
            log.debug("ReservaArticulo error = {}", e.getMessage());
         }
         if (!reservaArticulo.getObservaciones().trim().isEmpty())
            reservaArticulo.setObservaciones(reservaArticulo.getObservaciones() + numeroVoucher);
         addArticulo(clienteMovimiento, reservaArticulo, comprobante, ++item, reserva.getFacturarExtranjero(),
               articuloMovs);
      }
      log.debug("Fin proceso de completar articulos");
   }

   @Transactional
   protected void addArticulo(ClienteMovimiento clienteMovimiento, ReservaArticulo reservaArticulo,
         Comprobante comprobante, Integer item, Byte facturaExtranjero, List<ArticuloMovimiento> articuloMovs) {
      Articulo articulo = articuloService.findByArticuloId(reservaArticulo.getArticuloId());
      try {
         log.debug("Articulo = {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(articulo));
      } catch (JsonProcessingException e) {
         log.debug("Articulo error = {}", e.getMessage());
      }
      BigDecimal factorIva = new BigDecimal("1.21");
      if (articulo.getIva105() == 1)
         factorIva = new BigDecimal("1.105");
      if (articulo.getExento() == 1)
         factorIva = new BigDecimal("1.0");
      Integer factor = comprobante.getDebita() == 1 ? -1 : 1;

      if (articulo.getCentroStockId() != 1 || facturaExtranjero == 0) {
         BigDecimal precioUnitarioSinIva = reservaArticulo.getPrecioUnitario().divide(factorIva, 2,
               RoundingMode.HALF_UP);
         BigDecimal precioUnitarioConIva = precioUnitarioSinIva.multiply(factorIva);
         ArticuloMovimiento articuloMovimiento = new ArticuloMovimiento.Builder()
               .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
               .comprobanteId(clienteMovimiento.getComprobanteId())
               .negocioId(clienteMovimiento.getNegocioId())
               .item(item)
               .fechaMovimiento(clienteMovimiento.getFechaComprobante())
               .fechaFactura(clienteMovimiento.getFechaComprobante())
               .cierreCajaId(clienteMovimiento.getCierreCajaId())
               .articuloId(reservaArticulo.getArticuloId())
               .centroStockId(articulo.getCentroStockId())
               .precioUnitarioSinIva(precioUnitarioSinIva)
               .precioUnitarioConIva(precioUnitarioConIva)
               .precioUnitario(precioUnitarioConIva)
               .cantidad(new BigDecimal(factor * reservaArticulo.getCantidad()))
               .total(precioUnitarioConIva.multiply(new BigDecimal(reservaArticulo.getCantidad())))
               .numeroCuenta(articulo.getCuentaVentas())
               .iva105(articulo.getIva105())
               .exento(articulo.getExento())
               .build();

         try {
            log.debug("ArticuloMovimiento before = {}", JsonMapper.builder().findAndAddModules().build()
                  .writerWithDefaultPrettyPrinter().writeValueAsString(articuloMovimiento));
         } catch (JsonProcessingException e) {
            log.debug("ArticuloMovimiento before error = {}", e.getMessage());
         }

         articuloMovimiento = articuloMovimientoService.add(articuloMovimiento);

         try {
            log.debug("ArticuloMovimiento after = {}", JsonMapper.builder().findAndAddModules().build()
                  .writerWithDefaultPrettyPrinter().writeValueAsString(articuloMovimiento));
         } catch (JsonProcessingException e) {
            log.debug("ArticuloMovimiento after error = {}", e.getMessage());
         }

         articuloMovs.add(articuloMovimiento);

         if (!reservaArticulo.getObservaciones().trim().isEmpty()) {
            ConceptoFacturado conceptoFacturado = new ConceptoFacturado();
            conceptoFacturado.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
            conceptoFacturado.setNumeroLinea(1);
            conceptoFacturado.setConcepto(reservaArticulo.getObservaciones());
            conceptoFacturado.setArticuloMovimientoId(articuloMovimiento.getArticuloMovimientoId());

            try {
               log.debug("ConceptoFacturado before = {}", JsonMapper.builder().findAndAddModules().build()
                     .writerWithDefaultPrettyPrinter().writeValueAsString(conceptoFacturado));
            } catch (JsonProcessingException e) {
               log.debug("ConceptoFacturado before error = {}", e.getMessage());
            }

            conceptoFacturado = conceptoFacturadoService.add(conceptoFacturado);

            try {
               log.debug("ConceptoFacturado after = {}", JsonMapper.builder().findAndAddModules().build()
                     .writerWithDefaultPrettyPrinter().writeValueAsString(conceptoFacturado));
            } catch (JsonProcessingException e) {
               log.debug("ConceptoFacturado after error = {}", e.getMessage());
            }

         }
      }
   }

   public Reserva copyFromVoucher(Voucher voucher) {
      return new Reserva.Builder()
            .voucherId(voucher.getVoucherId())
            .clienteId(voucher.getClienteId())
            .fechaToma(voucher.getFechaToma())
            .fechaInServicio(voucher.getFechaServicio())
            .fechaOutServicio(voucher.getFechaServicio())
            .fechaVencimiento(voucher.getFechaVencimiento())
            .nombrePax(voucher.getNombrePax())
            .cantidadPaxs(voucher.getPaxs())
            .observaciones(voucher.getObservaciones())
            .reservaOrigenId(voucher.getReservaOrigenId())
            .pagaCacheuta(voucher.getPagaCacheuta())
            .build();
   }

   public Reserva add(Reserva reserva) {
      return repository.save(reserva);
   }

   public Reserva update(Reserva newReserva, Long reservaId) {
      return repository.findByReservaId(reservaId).map(reserva -> {
         reserva = new Reserva.Builder()
               .reservaId(reservaId)
               .negocioId(newReserva.getNegocioId())
               .clienteId(newReserva.getClienteId())
               .fechaToma(newReserva.getFechaToma())
               .fechaInServicio(newReserva.getFechaInServicio())
               .fechaOutServicio(newReserva.getFechaOutServicio())
               .fechaVencimiento(newReserva.getFechaVencimiento())
               .horaVencimiento(newReserva.getHoraVencimiento())
               .avisoMail(newReserva.getAvisoMail())
               .pendiente(newReserva.getPendiente())
               .confirmada(newReserva.getConfirmada())
               .facturada(newReserva.getFacturada())
               .anulada(newReserva.getAnulada())
               .eliminada(newReserva.getEliminada())
               .verificada(newReserva.getVerificada())
               .nombrePax(newReserva.getNombrePax())
               .cantidadPaxs(newReserva.getCantidadPaxs())
               .observaciones(newReserva.getObservaciones())
               .voucherId(newReserva.getVoucherId())
               .pagaComision(newReserva.getPagaComision())
               .observacionesComision(newReserva.getObservacionesComision())
               .comisionPagada(newReserva.getComisionPagada())
               .pagaCacheuta(newReserva.getPagaCacheuta())
               .facturadoFuera(newReserva.getFacturadoFuera())
               .reservaArticulos(newReserva.getReservaArticulos())
               .usuario(newReserva.getUsuario())
               .contacto(newReserva.getContacto())
               .reservaOrigenId(newReserva.getReservaOrigenId())
               .facturarExtranjero(newReserva.getFacturarExtranjero())
               .fechaAbierta(newReserva.getFechaAbierta())
               .build();
         return repository.save(reserva);
      }).orElseThrow(() -> new ReservaException(reservaId));
   }

   @Transactional
   public void generarReservaArticulo(Reserva reserva, List<VoucherProducto> voucherProductos) {
      Empresa empresa = empresaService.findTop();
      // Proceso que depura los artículos a eliminar y agregar
      // Para eliminar parto de todos los que ya están guardados y sacaré los que hay
      // que guardar que ya estaban, los que queden serán eliminados
      Map<String, ReservaArticulo> collectionEliminar = reservaArticuloService
            .findAllByVoucherId(reserva.getReservaId(), reserva.getVoucherId()).stream()
            .collect(Collectors.toMap(ReservaArticulo::getArticuloId, reservaArticulo -> reservaArticulo));
      // Para agregar parto de todos los artículos que corresponden al voucher y
      // elimino los que ya están guardados, los que queden seran agregados
      Map<String, Articulo> collectionAgregar = articuloService.findAllByVoucher(voucherProductos).stream().collect(
            Collectors.toMap(Articulo::toString, Function.identity(), ((articulo, otherArticulo) -> articulo)));

      // Busco las claves a eliminar en ambas colecciones
      var claves = new ArrayList<String>();
      for (ReservaArticulo reservaArticulo : collectionEliminar.values()) {
         if (collectionAgregar.containsKey(reservaArticulo.getArticuloId())) {
            claves.add(reservaArticulo.getArticuloId());
         }
      }

      // Elimino las claves de ambas colecciones
      for (String clave : claves) {
         collectionEliminar.remove(clave);
         collectionAgregar.remove(clave);
      }

      // Elimino de la DB los registros de reservaarticulo sobrantes
      for (ReservaArticulo reservaArticulo : collectionEliminar.values()) {
         reservaArticuloService.deleteByReservaArticuloId(reservaArticulo.getReservaArticuloId());
      }

      // Agrego los artículos nuevos en reservaarticulo
      List<ReservaArticulo> reservaArticulos = new ArrayList<>();
      for (Articulo articulo : collectionAgregar.values()) {
         var precioArticulo = precioService.getUnitPriceByArticuloIdAndFecha(articulo.getArticuloId(),
               reserva.getFechaInServicio());
         reservaArticulos.add(new ReservaArticulo.Builder()
               .negocioId(empresa.getNegocioId())
               .reservaId(reserva.getReservaId())
               .voucherId(reserva.getVoucherId())
               .articuloId(articulo.getArticuloId())
               .precioUnitarioSinComision(precioArticulo)
               .articulo(articulo)
               .build());

      }
      reservaArticulos = reservaArticuloService.saveAll(reservaArticulos);

      try {
         log.debug("reservaArticulos={}", JsonMapper.builder().findAndAddModules().build()
               .writerWithDefaultPrettyPrinter().writeValueAsString(reservaArticulos));
      } catch (JsonProcessingException e) {
         log.debug("something went wrong");
      }

      recalcularVoucher(reserva.getReservaId(), reserva.getVoucherId());
   }

   @Transactional
   public void recalcularVoucher(Long reservaId, Long voucherId) {
      Voucher voucher = voucherService.findByVoucherId(voucherId);

      int contador = 0;
      for (ReservaArticulo reservaArticulo : reservaArticuloService.findAllByVoucherId(reservaId, voucherId)) {
         contador++;
         var cantidadPaxs = 0;
         for (VoucherProducto voucherProducto : voucherProductoService.findAllByArticuloId(voucherId,
               reservaArticulo.getArticuloId())) {
            cantidadPaxs += voucherProducto.getCantidadPaxs();
         }
         reservaArticulo.setCantidad(cantidadPaxs);

         if (reservaArticulo.getPrecioUnitarioSinComision().compareTo(BigDecimal.ZERO) == 0) {
            reservaArticulo.setPrecioUnitarioSinComision(reservaArticulo.getArticulo().getPrecioVentaConIva());
         }

         reservaArticulo
               .setComision(comisionArticulo(reservaId, reservaArticulo.getArticuloId(), voucher.getClienteId()));
         BigDecimal complemento = BigDecimal.ONE.subtract(reservaArticulo.getComision()).setScale(2,
               RoundingMode.HALF_UP);
         reservaArticulo.setPrecioUnitario(reservaArticulo.getPrecioUnitarioSinComision().multiply(complemento)
               .setScale(2, RoundingMode.HALF_UP));
         reservaArticulo.setObservaciones("");

         if (contador == 1 && voucher.getReservaId() > 0) {
            reservaArticulo.setObservaciones("RVA " + voucher.getReservaId() + " " + voucher.getNombrePax() + " x "
                  + voucher.getPaxs() + " " + voucher.getProductos() + " "
                  + voucher.getFechaServicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
         }

         reservaArticulo = reservaArticuloService.update(reservaArticulo, reservaArticulo.getReservaArticuloId());
         try {
            log.debug("reserva_articulo={}", JsonMapper.builder().findAndAddModules().build()
                  .writerWithDefaultPrettyPrinter().writeValueAsString(reservaArticulo));
         } catch (JsonProcessingException e) {
            log.debug("reserva_articulo=null");
         }

      }
   }

   public List<ReservaArticulo> findAllReservaArticulos(Long reservaId) {
      return reservaArticuloService.findAllByReservaId(reservaId);
   }

   private BigDecimal comisionArticulo(Long reservaId, String articuloId, Long clienteId) {
      // TODO: simplificado para avanzar
      return BigDecimal.ZERO;
   }

   /**
    * @author: sebastian
    */
   public Reserva createReserva(CreateReservaDto createReservaDto) {
      // TODO: Decidir que hacer con ReservaHistorial, en VB6 crea un registro por
      // cada reserva
      Cliente cliente = clienteService.findByNumeroDocumento(createReservaDto.clienteNumeroDocumento());
      Reserva newReserva = createReservaDto.reserva();

      newReserva.setClienteId(cliente.getClienteId());
      newReserva.setCliente(cliente);
      Reserva savedReserva = repository.save(newReserva);

      List<String> articuloIds = createReservaDto.reservaArticulos().stream()
            .map(ReservaArticulo::getArticuloId)
            .toList();
      List<Articulo> articulos = articuloService.findAllByIds(articuloIds);

      List<ReservaArticulo> reservaArticulos = createReservaDto.reservaArticulos();
      for (ReservaArticulo reservaArticulo : reservaArticulos) {
         Articulo articulo = articulos.stream()
               .filter(a -> a.getArticuloId().equals(reservaArticulo.getArticuloId()))
               .findFirst()
               .orElseThrow(() -> new ArticuloException(reservaArticulo.getArticuloId()));
         BigDecimal precioArticulo = precioService.getUnitPriceByArticuloIdAndFecha(articulo.getArticuloId(),
               newReserva.getFechaInServicio());

         reservaArticulo.setArticulo(articulo);
         reservaArticulo.setArticuloId(articulo.getArticuloId());
         reservaArticulo.setPrecioUnitarioSinComision(precioArticulo);
         reservaArticulo.setReservaId(savedReserva.getReservaId());
         reservaArticulo.setNegocioId(savedReserva.getNegocioId());

         BigDecimal complemento = BigDecimal.ONE.subtract(reservaArticulo.getComision()).setScale(2,
               RoundingMode.HALF_UP);
         reservaArticulo.setPrecioUnitario(reservaArticulo.getPrecioUnitarioSinComision().multiply(complemento)
               .setScale(2, RoundingMode.HALF_UP));
      }
      reservaArticulos = reservaArticuloService.saveAll(reservaArticulos);

      return savedReserva;
   }

   public Reserva findLastReserva() {
      return repository.findTopByOrderByReservaIdDesc().orElseThrow(() -> new ReservaException("No hay reservas"));
   }

   public boolean isReservaFacturada(Long reservaId) {
      try {
         clienteMovimientoService.findFirstClienteMovimientoByReservaId(reservaId);
         return true;
      } catch (ClienteMovimientoException e) {
         return false;
      }
   }

   public List<Reserva> findLastDaysConfirmadas(int days) {
      return repository.findAllByFechaTomaBetweenAndConfirmada(
            OffsetDateTime.now().minusDays(days), OffsetDateTime.now(), (byte) 1);
   }

   public List<Reserva> findLastDaysConfirmadasAndNoFacturadas(int days) {
      List<Reserva> reservas = findLastDaysConfirmadas(days);
      log.debug("Reservas ultimos {} dias confirmadas: {}", days, reservas);
      List<Long> reservaIds = reservas.stream().map(Reserva::getReservaId).toList();
      List<ClienteMovimiento> clienteMovimientos = clienteMovimientoService.findAllByReservaIds(reservaIds);
      List<Reserva> reservasNoFacturadas = reservas.stream()
            .filter(reserva -> !clienteMovimientos.stream()
                  .anyMatch(cm -> cm.getReservaId().equals(reserva.getReservaId())))
            .toList();
      log.debug("Reservas ultimos {} dias confirmadas y no facturadas: {}", days, reservasNoFacturadas);
      return reservasNoFacturadas;
   }
}
