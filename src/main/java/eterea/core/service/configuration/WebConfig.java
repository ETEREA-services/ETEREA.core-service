package eterea.core.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "*",
                                "http://localhost:4200",
                                "http://192.168.101.31",
                                "http://10.147.17.28",
                                "http://eterea.termaliasa.com",
                                "https://eterea.termaliasa.com"
                        )
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name()
                        )
                        .allowedHeaders(
                                HttpHeaders.CONTENT_TYPE,
                                HttpHeaders.AUTHORIZATION
                        );
            }
        };
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer() {
        return pageableResolver -> {
            pageableResolver.setMaxPageSize(100);
            pageableResolver.setPageParameterName("page");
            pageableResolver.setSizeParameterName("size");
        };
    }
}