package com.github.miyasumas.jaxrs.api.resource;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.github.miyasumas.jaxrs.api.entity.Sample;
import com.github.miyasumas.jaxrs.api.message.ItemsMessage;

/**
 * Sample
 * @author MIYASAKA Yasumasa
 * @since 2014/10/24
 */
@Path("sample")
public class SampleResource {

	@GET
	@Produces("application/json")
	public ItemsMessage<Sample> search(@QueryParam("q") String q) {
		List<Sample> result = new LinkedList<>();
		if (q != null) {
			Sample sample = new Sample();
			sample.setText(q);
			result.add(sample);
		}
		return new ItemsMessage.Builder<Sample>().total(result.size()).setItems(result).build();
	}
}
