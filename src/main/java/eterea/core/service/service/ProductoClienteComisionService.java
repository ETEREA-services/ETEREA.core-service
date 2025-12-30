package eterea.core.service.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import eterea.core.service.exception.ProductoClienteComisionException;
import eterea.core.service.kotlin.model.ProductoClienteComision;
import eterea.core.service.repository.ProductoClienteComisionRepository;

@Service
public class ProductoClienteComisionService {

   private static final Logger logger = LoggerFactory.getLogger(ProductoClienteComisionService.class);
   private final ProductoClienteComisionRepository repository;

   public ProductoClienteComisionService(ProductoClienteComisionRepository repository) {
      this.repository = repository;
   }

   public ProductoClienteComision findByProductoIdAndClienteId(Integer productoId, Long clienteId) {
      return repository
            .findByProductoIdAndClienteId(productoId, clienteId)
            .orElseGet(() -> {
               ProductoClienteComision defaultProductoClienteComision = new ProductoClienteComision();
               defaultProductoClienteComision.setProductoId(productoId);
               defaultProductoClienteComision.setClienteId(clienteId);
               defaultProductoClienteComision.setComision(BigDecimal.ZERO);
               return defaultProductoClienteComision;
            });
   }

   public ProductoClienteComision findByClienteIdAndReservaIdAndArticuloId(Long clienteId, Long reservaId,
         String articuloId) {
      var results = repository.findByClienteIdAndReservaIdAndArticuloId(clienteId, reservaId, articuloId);
      if (results.size() > 1) {
         logger.warn("Multiple ProductoClienteComision found: clienteId={}, reservaId={}, articuloId={}, count={}, "
               + "selecting first by productoClienteComisionId ASC",
               clienteId, reservaId, articuloId, results.size());
      }
      return results.stream()
            .findFirst()
            .orElseThrow(() -> new ProductoClienteComisionException(clienteId, reservaId, articuloId));
   }

}
