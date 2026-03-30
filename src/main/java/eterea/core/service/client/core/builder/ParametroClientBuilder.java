package eterea.core.service.client.core.builder;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import eterea.core.service.client.core.ParametroClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

public class ParametroClientBuilder {

    public static ParametroClient buildClient(String baseUrl) {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .decoder(new JacksonDecoder(
                        JsonMapper.builder()
                                .addModule(new KotlinModule.Builder().build())
                                .build()))
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");
                })
                .target(ParametroClient.class, baseUrl + "/api/core/parametro");
    }

}
