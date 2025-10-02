package eterea.core.service.service;

import org.springframework.stereotype.Service;

import eterea.core.service.exception.ProductoClienteComisionException;
import eterea.core.service.kotlin.model.ProductoClienteComision;
import eterea.core.service.repository.ProductoClienteComisionRepository;

@Service
public class ProductoClienteComisionService {

   private final ProductoClienteComisionRepository repository;

   public ProductoClienteComisionService(ProductoClienteComisionRepository repository) {
      this.repository = repository;
   }

   public ProductoClienteComision findByProductoIdAndClienteId(Long productoId, Long clienteId) {
      return repository
            .findByProductoIdAndClienteId(productoId, clienteId)
            .orElseThrow(() -> new ProductoClienteComisionException(productoId, clienteId));
   }

   public ProductoClienteComision findByClienteIdAndReservaIdAndArticuloId(Long clienteId, Long reservaId, String articuloId) {
      return repository.findByClienteIdAndReservaIdAndArticuloId(clienteId, reservaId, articuloId)
            .orElseThrow(() -> new ProductoClienteComisionException(clienteId, reservaId, articuloId));
   }

}
