package eterea.core.service.client.core.builder;

import eterea.core.service.client.core.ArticuloClient;
import feign.Feign;
import feign.Logger;

public class ArticuloClientBuilder {

    public static ArticuloClient buildClient(String baseUrl) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");
                })
                .target(ArticuloClient.class, baseUrl + "/api/core/articulo");
    }

}
