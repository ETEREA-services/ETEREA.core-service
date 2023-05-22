/**
 * 
 */
package eterea.core.api.rest.service.client;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eterea.core.api.rest.model.client.OrderWeb;

/**
 * @author daniel
 *
 */
@Service
public class OrderWebService {

	public List<OrderWeb> test() {
		List<OrderWeb> orderWebs = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeDeserializer());
		Gson gson = gsonBuilder.create();

		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get("/data/temp/orders-2022-09-12-16.json"));
			orderWebs = Arrays.asList(gson.fromJson(reader, OrderWeb[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return orderWebs;
	}

}
