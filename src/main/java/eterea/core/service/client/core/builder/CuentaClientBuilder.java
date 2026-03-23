package eterea.core.service.client.core.builder;

import eterea.core.service.client.core.CuentaClient;
import feign.Feign;
import feign.Logger;

public class CuentaClientBuilder {

    public static CuentaClient buildClient(String baseUrl) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");
                })
                .target(CuentaClient.class, baseUrl + "/api/core/cuenta");
    }

}
