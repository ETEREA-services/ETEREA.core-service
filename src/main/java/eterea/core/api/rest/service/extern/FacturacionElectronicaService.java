package eterea.core.api.rest.service.extern;

import eterea.core.api.rest.kotlin.model.Negocio;
import eterea.core.api.rest.kotlin.model.dto.FacturacionDTO;
import eterea.core.api.rest.service.NegocioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FacturacionElectronicaService {

    private final NegocioService negocioService;

    @Autowired
    public FacturacionElectronicaService(NegocioService negocioService) {
        this.negocioService = negocioService;
    }

    private String getUrl(Integer negocioId) {
        Negocio negocio = negocioService.findByNegocioId(negocioId);
        return "http://" + negocio.getFacturaServer() + ":" + negocio.getFacturaPort() + "/facturacionService";
    }

    public FacturacionDTO facturar(FacturacionDTO facturacionDTO, Integer negocioId) {
        WebClient webClient = WebClient.create(getUrl(negocioId));
        facturacionDTO = webClient.post()
                .body(BodyInserters.fromValue(facturacionDTO))
                .retrieve()
                .onStatus(
                        // Manejar códigos de error específicos si es necesario
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> {
                            // En caso de error, manejar el cuerpo de la respuesta
                            Mono<String> errorMono = response.bodyToMono(String.class);
                            return errorMono.flatMap(errorMessage -> {
                                // Puedes imprimir el mensaje de error o manejarlo de otra manera
                                log.debug(errorMessage);
                                return Mono.error(new WebClientResponseException("Error en la respuesta", response.statusCode().value(), errorMessage, null, null, null));
                            });
                        }
                )
                .bodyToMono(FacturacionDTO.class)
                .block();
        return facturacionDTO;
    }

}
