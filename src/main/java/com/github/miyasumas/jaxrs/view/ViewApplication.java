package com.github.miyasumas.jaxrs.view;

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
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;

/**
 * 画面側の JAX-RS 設定
 * 
 * @author MIYASAKA Yasumasa
 * @author Last changed by:$Author$
 * @version $Rev$ $Date$
 * @since 2015/02/12
 */
@ApplicationPath("/view")
public class ViewApplication extends ResourceConfig {

	/**
	 * ログ
	 */
	private static final Logger logger = LogManager.getLogger(ViewApplication.class);

	@Inject
	public ViewApplication(ServiceLocator serviceLocator, ServletContext context) {
		packages(getClass().getPackage().getName());
		register(JspMvcFeature.class);
		register(MultiPartFeature.class);
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
