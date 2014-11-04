package com.github.miyasumas.jaxrs;
import com.google.inject.servlet.ServletModule;

/**
 * Guice 用モジュール
 * @author miyasumas
 * @since 2014/09/07
 */
public class SandboxModule extends ServletModule {

	/**
	 * {@inheritDoc}
	 * @see com.google.inject.servlet.ServletModule#configureServlets()
	 */
	@Override
	protected void configureServlets() {
	}
}
