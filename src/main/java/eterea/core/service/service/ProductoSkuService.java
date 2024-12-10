package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ProductoSkuException;
import eterea.core.service.kotlin.model.ProductoSku;
import eterea.core.service.kotlin.repository.ProductoSkuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ProductoSkuService {

    private final ProductoSkuRepository repository;

    public ProductoSkuService(ProductoSkuRepository repository) {
        this.repository = repository;
    }

    public ProductoSku findBySku(String sku, byte lunes, byte martes, byte miercoles, byte jueves, 
                                byte viernes, byte sabado, byte domingo, byte feriado) {
        log.debug("Processing findBySku - sku: {}, lunes: {}, martes: {}, miercoles: {}, jueves: {}, viernes: {}, sabado: {}, domingo: {}, feriado: {}", 
                  sku, lunes, martes, miercoles, jueves, viernes, sabado, domingo, feriado);
        // Crear un mapa de días y sus valores
        Map<String, Byte> dias = Map.of(
            "lunes", lunes,
            "martes", martes,
            "miercoles", miercoles,
            "jueves", jueves,
            "viernes", viernes,
            "sabado", sabado,
            "domingo", domingo,
            "feriado", feriado
        );

        // Buscar el primer día que coincida
        return dias.entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(entry -> {
                try {
                    String methodName = "findBySkuAnd" + entry.getKey().substring(0, 1).toUpperCase() + 
                                      entry.getKey().substring(1);
                    Method method = repository.getClass().getMethod(methodName, String.class, byte.class);
                    return (Optional<ProductoSku>) method.invoke(repository, sku, (byte) 1);
                } catch (Exception e) {
                    throw new RuntimeException("Error al buscar producto por SKU", e);
                }
            })
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst()
            .orElseThrow(() -> new ProductoSkuException(sku));
    }

}
