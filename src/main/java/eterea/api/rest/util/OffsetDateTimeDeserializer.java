/**
 * 
 */
package eterea.api.rest.util;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Slf4j
public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

	@Override
	public OffsetDateTime deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JacksonException {
		log.debug("Parser -> {}", parser.getValueAsString());
		return OffsetDateTime.now();
	}

}
