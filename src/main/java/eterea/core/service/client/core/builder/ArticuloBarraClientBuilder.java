package eterea.core.service.client.core.builder;

import eterea.core.service.client.core.ArticuloBarraClient;
import feign.Feign;
import feign.Logger;

public class ArticuloBarraClientBuilder {

    public static ArticuloBarraClient buildClient(String baseUrl) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");
                })
                .target(ArticuloBarraClient.class, baseUrl + "/api/core/articulobarra");
    }

}
