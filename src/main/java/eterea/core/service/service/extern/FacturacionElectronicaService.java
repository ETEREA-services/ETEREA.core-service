package eterea.core.service.service.extern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.client.afip.FacturacionAfipClient;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.model.Snapshot;
import eterea.core.service.service.NegocioService;
import eterea.core.service.service.SnapshotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class FacturacionElectronicaService {

    private final FacturacionAfipClient facturacionAfipClient;
    private final SnapshotService snapshotService;

    public FacturacionElectronicaService(FacturacionAfipClient facturacionAfipClient, SnapshotService snapshotService) {
        this.facturacionAfipClient = facturacionAfipClient;
        this.snapshotService = snapshotService;
    }

    public FacturacionDto makeFactura(FacturacionDto facturacionDto) {
        log.debug("Processing FactuacionElectronicaService.makeFactura");
        var json = logFacturacion(facturacionDto);
        var snapshot = Snapshot.builder()
                .uuid(UUID.randomUUID().toString())
                .descripcion("make-factura-facturacion-pre")
                .payload(json)
                .build();
        logSnapshot(snapshot);
        snapshot = snapshotService.add(snapshot);
        logSnapshot(snapshot);
        facturacionDto = facturacionAfipClient.facturador(facturacionDto);
        json = logFacturacion(facturacionDto);
        snapshot = Snapshot.builder()
                .uuid(UUID.randomUUID().toString())
                .descripcion("make-factura-facturacion-post")
                .payload(json)
                .build();
        logSnapshot(snapshot);
        snapshot = snapshotService.add(snapshot);
        logSnapshot(snapshot);
        return facturacionDto;
    }

    private String logSnapshot(Snapshot snapshot) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(snapshot);
            log.debug("Snapshot={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("Snapshot jsonify error {}", e.getMessage());
            return null;
        }
    }

    private String logFacturacion(FacturacionDto facturacionDTO) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(facturacionDTO);
            log.debug("FacturacionDto={}", json);
            return json;
        } catch (JsonProcessingException e) {
            log.debug("FacturacionDto jsonify error {}", e.getMessage());
            return null;
        }
    }

}
