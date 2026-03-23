package eterea.core.service.client.core.builder;

import eterea.core.service.client.core.ParametroClient;
import feign.Feign;
import feign.Logger;

public class ParametroClientBuilder {

    public static ParametroClient buildClient(String baseUrl) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");
                })
                .target(ParametroClient.class, baseUrl + "/api/core/parametro");
    }

}
