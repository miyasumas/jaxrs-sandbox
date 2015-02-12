package com.github.miyasumas.jaxrs.view.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.glassfish.jersey.server.mvc.Template;

/**
 * Hello
 * 
 * @author MIYASAKA Yasumasa
 * @since 2014/11/04
 */
@Path("hello")
public class HelloJspResource {

	@GET
	@Template(name = "/hello.jsp")
	public Map<String, Object> search(@QueryParam("q") String q) {
		Map<String, Object> result = new HashMap<>();
		result.put("items", Arrays.asList("a", "b", "c"));
		return result;
	}
}
