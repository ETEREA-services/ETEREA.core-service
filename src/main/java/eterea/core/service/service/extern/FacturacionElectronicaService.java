package eterea.core.service.service.extern;

import eterea.core.service.client.afip.FacturacionAfipClient;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.service.NegocioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FacturacionElectronicaService {

    private final FacturacionAfipClient facturacionAfipClient;

    public FacturacionElectronicaService(FacturacionAfipClient facturacionAfipClient) {
        this.facturacionAfipClient = facturacionAfipClient;
    }

    public FacturacionDto makeFactura(FacturacionDto facturacionDTO) {
        return facturacionAfipClient.facturador(facturacionDTO);
    }

}
