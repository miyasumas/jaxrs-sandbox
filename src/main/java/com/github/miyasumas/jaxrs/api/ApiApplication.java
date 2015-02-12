package com.github.miyasumas.jaxrs.api;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.inject.Injector;

/**
 * API側の JAX-RS 設定
 * 
 * @author MIYASAKA Yasumasa
 * @author Last changed by:$Author$
 * @version $Rev$ $Date$
 * @since 2015/02/12
 */
@ApplicationPath("/api/v1")
public class ApiApplication extends ResourceConfig {

	/**
	 * ログ
	 */
	private static final Logger logger = LogManager.getLogger(ApiApplication.class);

	@Inject
	public ApiApplication(ServiceLocator serviceLocator, ServletContext context) {
		packages(getClass().getPackage().getName());
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		register(Jdk8Module.class);
		register(new ContextResolver<ObjectMapper>() {
			@Override
			public ObjectMapper getContext(Class<?> type) {
				return new ObjectMapper();
			}
		});

		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		Injector injector = Injector.class.cast(context.getAttribute(Injector.class.getName()));
		guiceBridge.bridgeGuiceInjector(injector.createChildInjector());

		logger.info("Service Locator Id: {}", serviceLocator.getLocatorId());
		logger.info("Injector: {}", injector.getAllBindings());
		logger.info("Loaded: {}", getClass().getName());
	}
}
