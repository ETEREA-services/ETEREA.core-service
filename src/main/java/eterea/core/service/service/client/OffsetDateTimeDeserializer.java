/**
 * 
 */
package eterea.core.service.service.client;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * @author daniel
 *
 */
public class OffsetDateTimeDeserializer implements JsonDeserializer<OffsetDateTime> {

	@Override
	public OffsetDateTime deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if (jsonElement.getAsString().equals(""))
			return null;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssz");
		OffsetDateTime offsetDateTime = null;
		if (jsonElement.getAsString().length() == 10) {
			offsetDateTime = OffsetDateTime.parse(jsonElement.getAsString() + " 00:00:00+00:00", dateTimeFormatter);
		} else {
			offsetDateTime = OffsetDateTime.parse(jsonElement.getAsString() + "+00:00", dateTimeFormatter);
		}
		return offsetDateTime;
	}

}
