package eterea.core.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

   @Bean
   public WebMvcConfigurer corsConfig() {
      return new WebMvcConfigurer() {
         public void addCorsMappings(@NonNull CorsRegistry registry) {
            registry.addMapping("/**")
//                 .allowedOrigins("http://10.147.30.50:4200", "http://10.147.17.50:4200", "http://10.11.50.5:4200")
                 .allowedOrigins("*")
                 .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name())
                 .allowedHeaders(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION);
         }
      };
   }
}