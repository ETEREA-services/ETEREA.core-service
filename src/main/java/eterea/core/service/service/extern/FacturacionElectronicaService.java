package eterea.core.service.service.extern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.client.afip.FacturacionAfipClient;
import eterea.core.service.model.Snapshot;
import eterea.core.service.model.Track;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.SnapshotService;
import eterea.core.service.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class FacturacionElectronicaService {

    private final FacturacionAfipClient facturacionAfipClient;
    private final SnapshotService snapshotService;
    private final TrackService trackService;

    public FacturacionElectronicaService(FacturacionAfipClient facturacionAfipClient, SnapshotService snapshotService, TrackService trackService) {
        this.facturacionAfipClient = facturacionAfipClient;
        this.snapshotService = snapshotService;
        this.trackService = trackService;
    }

    public FacturacionDto makeFactura(FacturacionDto facturacionDto, Track track) {
        log.debug("Processing FactuacionElectronicaService.makeFactura");
        var json = logFacturacion(facturacionDto);
        if (track == null) {
            track = trackService.startTracking("make-factura");
        }
        var snapshot = Snapshot.builder()
                .uuid(UUID.randomUUID().toString())
                .descripcion("make-factura-facturacion-pre")
                .payload(json)
                .trackUuid(track.getUuid())
                .previousUuid(null)
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
                .trackUuid(track.getUuid())
                .previousUuid(snapshot.getUuid())
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
