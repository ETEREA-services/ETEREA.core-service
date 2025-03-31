package eterea.core.service.service;

import org.springframework.stereotype.Service;

import eterea.core.service.kotlin.model.ProductoWeb;
import eterea.core.service.kotlin.repository.ProductoWebRepository;

@Service
public class ProductoWebService {

   private final ProductoWebRepository repository;

   public ProductoWebService(ProductoWebRepository productoWebRepository) {
      this.repository = productoWebRepository;
   }

   public ProductoWeb findBySku(String sku) {
      return repository.findBySku(sku).orElseThrow(() -> new ProductoWebException(sku));
   }
}
