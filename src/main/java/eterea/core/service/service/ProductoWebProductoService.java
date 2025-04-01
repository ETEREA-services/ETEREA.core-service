package eterea.core.service.service;

import java.util.List;

import eterea.core.service.kotlin.model.ProductoWeb;
import eterea.core.service.kotlin.model.ProductoWebProducto;
import eterea.core.service.kotlin.model.TipoDia;
import eterea.core.service.kotlin.model.TipoPax;
import eterea.core.service.kotlin.repository.ProductoWebProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoWebProductoService {

   private final ProductoWebProductoRepository repository;

   public ProductoWebProductoService(ProductoWebProductoRepository repository) {
      this.repository = repository;
   }

   public List<ProductoWebProducto> findByProductoWebAndTipoPaxAndTipoDia(ProductoWeb productoWeb, TipoPax tipoPax, TipoDia tipoDia) {
      return repository.findByProductoWebAndTipoPaxAndTipoDia(productoWeb, tipoPax, tipoDia);
   }
}
