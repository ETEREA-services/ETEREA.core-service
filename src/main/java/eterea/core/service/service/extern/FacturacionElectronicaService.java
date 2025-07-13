package eterea.core.service.service.extern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.client.afip.FacturacionAfipClient;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class FacturacionElectronicaService {

    private final FacturacionAfipClient facturacionAfipClient;
    private final TrackService trackService;

    public FacturacionElectronicaService(FacturacionAfipClient facturacionAfipClient,
                                         TrackService trackService
    ) {
        this.facturacionAfipClient = facturacionAfipClient;
        this.trackService = trackService;
    }

    public FacturacionDto makeFactura(FacturacionDto facturacionDto) {
        log.debug("Processing FactuacionElectronicaService.makeFactura");
        logFacturacion(facturacionDto);
        facturacionDto = facturacionAfipClient.facturador(facturacionDto);
        logFacturacion(facturacionDto);
        return facturacionDto;
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
