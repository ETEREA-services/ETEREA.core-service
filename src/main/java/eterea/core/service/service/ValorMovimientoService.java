package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ValorMovimientoException;
import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.kotlin.repository.ValorMovimientoRepository;
import eterea.core.service.model.dto.CobroInternoDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ValorMovimientoService {

   private final ValorMovimientoRepository repository;

   public ValorMovimientoService(ValorMovimientoRepository repository
   ) {
      this.repository = repository;
   }

   public ValorMovimiento add(ValorMovimiento valorMovimiento) {
      return repository.save(valorMovimiento);
   }

   public ValorMovimiento update(ValorMovimiento newValorMovimiento, Long valorMovimientoId) {
      return repository.findByValorMovimientoId(valorMovimientoId).map(valorMovimiento -> {
         valorMovimiento = new ValorMovimiento.Builder()
              .valorMovimientoId(valorMovimientoId)
              .negocioId(newValorMovimiento.getNegocioId())
              .valorId(newValorMovimiento.getValorId())
              .proveedorId(newValorMovimiento.getProveedorId())
              .clienteId(newValorMovimiento.getClienteId())
              .fechaEmision(newValorMovimiento.getFechaEmision())
              .fechaVencimiento(newValorMovimiento.getFechaVencimiento())
              .comprobanteId(newValorMovimiento.getComprobanteId())
              .numeroComprobante(newValorMovimiento.getNumeroComprobante())
              .importe(newValorMovimiento.getImporte())
              .numeroCuenta(newValorMovimiento.getNumeroCuenta())
              .fechaContable(newValorMovimiento.getFechaContable())
              .ordenContable(newValorMovimiento.getOrdenContable())
              .proveedorMovimientoId(newValorMovimiento.getProveedorMovimientoId())
              .clienteMovimientoId(newValorMovimiento.getClienteMovimientoId())
              .titular(newValorMovimiento.getTitular())
              .banco(newValorMovimiento.getBanco())
              .receptor(newValorMovimiento.getReceptor())
              .estadoId(newValorMovimiento.getEstadoId())
              .fechaEntrega(newValorMovimiento.getFechaEntrega())
              .tanda(newValorMovimiento.getTanda())
              .tandaIndex(newValorMovimiento.getTandaIndex())
              .cierreCajaId(newValorMovimiento.getCierreCajaId())
              .nivel(newValorMovimiento.getNivel())
              .observaciones(newValorMovimiento.getObservaciones())
              .build();
         return repository.save(valorMovimiento);
      }).orElseThrow(() -> new ValorMovimientoException(valorMovimientoId));
   }

   // public List<ValorMovimientoDto> findAllMovimientos(LocalDate desde,
   //         LocalDate hasta,
   //         boolean cierreCajaOnly,
   //         boolean ingresosOnly) {
   //     return repository
   //             .findAllByFechaContableBetween(
   //                     OffsetDateTime.of(desde.atTime(LocalTime.MIN), ZoneOffset.UTC),
   //                     OffsetDateTime.of(hasta.atTime(LocalTime.MIN), ZoneOffset.UTC), cierreCajaOnly,
   //                     ingresosOnly)
   //             .stream()
   //             .map(valorMovimientoDtoMapper)
   //             .collect(Collectors.toList());
   // }

   public List<CobroInternoDto> findAllCobroInterno(LocalDate desde, LocalDate hasta) {
      return repository.findAllCobroInternoByFechaContableBetween(
           OffsetDateTime.of(desde.atTime(LocalTime.MIN), ZoneOffset.UTC),
           OffsetDateTime.of(hasta.atTime(LocalTime.MAX), ZoneOffset.UTC));
   }

}
