package eterea.core.service.client.core.builder;

import eterea.core.service.client.core.ArticuloClient;
import feign.Feign;
import feign.Logger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.cloud.openfeign.support.FeignHttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class ArticuloClientBuilder {

    @SuppressWarnings("unchecked")
    private static FeignHttpMessageConverters getHttpMessageConverters() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        StaticListableBeanFactory beanFactory = new StaticListableBeanFactory();
        beanFactory.addBean("jacksonConverter", converter);
        ObjectProvider<HttpMessageConverter<?>> convertersProvider = (ObjectProvider) beanFactory.getBeanProvider(HttpMessageConverter.class);

        StaticListableBeanFactory emptyFactory = new StaticListableBeanFactory();
        ObjectProvider<HttpMessageConverterCustomizer> customizersProvider = emptyFactory.getBeanProvider(HttpMessageConverterCustomizer.class);

        return new FeignHttpMessageConverters(convertersProvider, customizersProvider);
    }

    public static ArticuloClient buildClient(String baseUrl) {
        FeignHttpMessageConverters converters = getHttpMessageConverters();
        StaticListableBeanFactory beanFactory = new StaticListableBeanFactory();
        beanFactory.addBean("converters", converters);
        ObjectProvider<FeignHttpMessageConverters> provider = beanFactory.getBeanProvider(FeignHttpMessageConverters.class);

        return Feign.builder()
                .encoder(new SpringEncoder(provider))
                .decoder(new SpringDecoder(provider))
                .contract(new SpringMvcContract())
                .logger(new Logger.ErrorLogger())
                .requestInterceptor(template -> {
                    template.header("X-Service-Name", "core-service");  // o el header que use tu gateway
                })
                .target(ArticuloClient.class, baseUrl + "/api/core/articulo");
    }

}