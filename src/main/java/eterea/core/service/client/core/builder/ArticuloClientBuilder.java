package eterea.core.service.client.core.builder;

import eterea.core.service.client.core.ArticuloClient;
import feign.Feign;
import feign.Logger;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class ArticuloClientBuilder {
    
    private static HttpMessageConverters getHttpMessageConverters() {
        return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
    }
    
    public static ArticuloClient buildClient(String baseUrl) {
        HttpMessageConverters converters = getHttpMessageConverters();
        return Feign.builder()
                .encoder(new SpringEncoder(() -> converters))
                .decoder(new SpringDecoder(() -> converters))
                .contract(new SpringMvcContract())
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");  // o el header que use tu gateway
                })
                .target(ArticuloClient.class, baseUrl + "/api/core/articulo");
    }

}