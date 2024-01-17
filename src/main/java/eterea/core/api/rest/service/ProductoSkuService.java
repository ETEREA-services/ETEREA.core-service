package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.exception.ProductoSkuException;
import eterea.core.api.rest.kotlin.model.ProductoSku;
import eterea.core.api.rest.kotlin.repository.ProductoSkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoSkuService {

    private final ProductoSkuRepository repository;

    @Autowired
    public ProductoSkuService(ProductoSkuRepository repository) {
        this.repository = repository;
    }

    public ProductoSku findBySku(String sku, byte lunes, byte martes, byte miercoles, byte jueves, byte viernes, byte sabado, byte domingo, byte feriado) {
        Optional<ProductoSku> productoSku = null;
        if (lunes == 1) {
            productoSku = repository.findBySkuAndLunes(sku, lunes);
        }
        if (martes == 1) {
            productoSku = repository.findBySkuAndMartes(sku, martes);
        }
        if (miercoles == 1) {
            productoSku = repository.findBySkuAndMiercoles(sku, miercoles);
        }
        if (jueves == 1) {
            productoSku = repository.findBySkuAndJueves(sku, jueves);
        }
        if (viernes == 1) {
            productoSku = repository.findBySkuAndViernes(sku, viernes);
        }
        if (sabado == 1) {
            productoSku = repository.findBySkuAndSabado(sku, sabado);
        }
        if (domingo == 1) {
            productoSku = repository.findBySkuAndDomingo(sku, domingo);
        }
        if (feriado == 1) {
            productoSku = repository.findBySkuAndFeriado(sku, feriado);
        }
        return productoSku.orElseThrow(() -> new ProductoSkuException(sku));
    }

}
