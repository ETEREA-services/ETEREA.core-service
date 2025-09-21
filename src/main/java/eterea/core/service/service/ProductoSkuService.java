package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ProductoSkuException;
import eterea.core.service.kotlin.model.ProductoSku;
import eterea.core.service.kotlin.repository.ProductoSkuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductoSkuService {

    private final ProductoSkuRepository repository;

    public ProductoSku findBySku(String sku, byte lunes, byte martes, byte miercoles, byte jueves,
                                 byte viernes, byte sabado, byte domingo, byte feriado) {
        log.debug("Processing findBySku - sku: {}, lunes: {}, martes: {}, miercoles: {}, jueves: {}, viernes: {}, sabado: {}, domingo: {}, feriado: {}",
                  sku, lunes, martes, miercoles, jueves, viernes, sabado, domingo, feriado);

        if (lunes == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndLunes(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }
        if (martes == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndMartes(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }
        if (miercoles == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndMiercoles(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }
        if (jueves == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndJueves(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }
        if (viernes == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndViernes(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }
        if (sabado == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndSabado(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }
        if (domingo == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndDomingo(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }
        if (feriado == 1) {
            Optional<ProductoSku> result = repository.findBySkuAndFeriado(sku, (byte) 1);
            if (result != null && result.isPresent()) return result.get();
        }

        throw new ProductoSkuException(sku);
    }

}