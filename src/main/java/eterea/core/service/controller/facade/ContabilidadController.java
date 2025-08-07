package eterea.core.service.controller.facade;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.model.dto.clientemovimiento.FacturaDetailsDto;
import eterea.core.service.service.facade.ContabilidadService;

@RestController
@RequestMapping({ "/api/core/contabilidad", "/contabilidad" })
public class ContabilidadController {

   private final ContabilidadService service;

   public ContabilidadController(ContabilidadService service) {
      this.service = service;
   }

   @GetMapping("/facturas/detalles")
   public ResponseEntity<List<FacturaDetailsDto>> getFacturasDetalles(
         @RequestParam(name = "desde", required = true) String desde,
         @RequestParam(name = "hasta", required = true) String hasta,
         @RequestParam(name = "comprobanteId", required = true) Integer comprobanteId) {
      OffsetDateTime desdeDate = OffsetDateTime.parse(desde);
      OffsetDateTime hastaDate = OffsetDateTime.parse(hasta);
      return ResponseEntity.ok(service.getFacturasDetalles(desdeDate, hastaDate, comprobanteId));
   }

   @GetMapping("/facturas/detalles/sliced")
   public ResponseEntity<Slice<FacturaDetailsDto>> getFacturasDetallesSliced(
         @RequestParam(name = "desde", required = true) String desde,
         @RequestParam(name = "hasta", required = true) String hasta,
         @RequestParam(name = "comprobanteId", required = true) Integer comprobanteId,
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "10") int size) {
      OffsetDateTime desdeDate = OffsetDateTime.parse(desde);
      OffsetDateTime hastaDate = OffsetDateTime.parse(hasta);
      Pageable pageable = PageRequest.of(page, size);
      return ResponseEntity.ok(service.getFacturasDetallesSliced(desdeDate, hastaDate, comprobanteId, pageable));
   }

   @GetMapping("/facturas/orphan/detalles")
   public ResponseEntity<List<FacturaDetailsDto>> getOrphanFacturasDetalles(
         @RequestParam(name = "desde", required = true) String desde,
         @RequestParam(name = "hasta", required = true) String hasta,
         @RequestParam(name = "comprobanteId", required = true) Integer comprobanteId) {
      OffsetDateTime desdeDate = OffsetDateTime.parse(desde);
      OffsetDateTime hastaDate = OffsetDateTime.parse(hasta);
      return ResponseEntity.ok(service.getOrphanFacturasDetalles(desdeDate, hastaDate, comprobanteId));
   }

   @PostMapping("/facturas/{clienteMovimientoId}/fix")
   public ResponseEntity<Void> fixOrphanFactura(
         @PathVariable(name = "clienteMovimientoId", required = true) Long clienteMovimientoId,
         @RequestParam(name = "ignorePluspagos", required = true) boolean ignorePluspagos) {
      service.fixOrphanFactura(clienteMovimientoId, ignorePluspagos);
      return ResponseEntity.ok().build();
   }

}
