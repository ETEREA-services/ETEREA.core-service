package eterea.core.service.client.core.builder;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import eterea.core.service.client.core.ArticuloClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

public class ArticuloClientBuilder {

    public static ArticuloClient buildClient(String baseUrl) {
        JsonMapper jsonMapper = JsonMapper.builder()
                .addModule(new KotlinModule.Builder().build())
                .build();

        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder(jsonMapper))
                .decoder(new JacksonDecoder(jsonMapper))
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");
                    template.header("Content-Type", "application/json");
                })
                .target(ArticuloClient.class, baseUrl + "/api/core/articulo");
    }

}
