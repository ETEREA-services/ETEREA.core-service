/**
 * 
 */
package eterea.api.rest.util;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

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
		String fecha = parser.getValueAsString();
		log.debug("Parser -> {} {}", fecha, fecha.length());
		if (fecha.equals(""))
			return null;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssz");
		if (fecha.length() == 10) {
			fecha = fecha + " 00:00:00+00:00";
			log.debug("Deserialize -> {} {}", fecha, fecha.length());
			return OffsetDateTime.parse(fecha, dateTimeFormatter);
		}
		if (fecha.length() == 29) {
			fecha = fecha.replace(".000", "");
			fecha = fecha.replace("T", " ");
			log.debug("Deserialize -> {} {}", fecha, fecha.length());
			return OffsetDateTime.parse(fecha, dateTimeFormatter);
		}

		return null;
	}

}
