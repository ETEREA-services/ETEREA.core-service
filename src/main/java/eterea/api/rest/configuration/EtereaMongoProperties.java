/**
 * 
 */
package eterea.api.rest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author daniel
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.mongo")
public class EtereaMongoProperties {

	private String server;
	private Long port;

}
