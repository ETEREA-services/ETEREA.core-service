/**
 * 
 */
package eterea.api.rest.configuration;

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
@PropertySource("classpath:config/eterea.properties")
public class EtereaConfiguration {

}
