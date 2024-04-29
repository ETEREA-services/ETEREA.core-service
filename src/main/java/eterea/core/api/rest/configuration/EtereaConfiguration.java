/**
 * 
 */
package eterea.core.api.rest.configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author daniel
 *
 */
@Configuration
@EnableJpaAuditing
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "eterea.core.api.rest.client")
@PropertySource("classpath:config/eterea.properties")
public class EtereaConfiguration {

}
