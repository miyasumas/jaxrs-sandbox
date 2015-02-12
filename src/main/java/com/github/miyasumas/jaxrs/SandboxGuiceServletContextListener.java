package com.github.miyasumas.jaxrs;


import javax.servlet.annotation.WebListener;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Guice 初期化
 * @author miyasumas
 * @since 2014/09/23
 */
@WebListener
public class SandboxGuiceServletContextListener extends GuiceServletContextListener {

	/**
	 * {@inheritDoc}
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 */
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new SandboxModule());
	}
}
