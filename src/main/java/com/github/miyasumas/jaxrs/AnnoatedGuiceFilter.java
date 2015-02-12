package com.github.miyasumas.jaxrs;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import com.google.inject.servlet.GuiceFilter;

/**
 * TODO 説明を記入して下さい
 * 
 * @author MIYASAKA Yasumasa
 * @author Last changed by:$Author$
 * @version $Rev$ $Date$
 * @since 2015/02/12
 */
@WebFilter(urlPatterns = "/*")
public class AnnoatedGuiceFilter extends GuiceFilter {

	/** 
	 * {@inheritDoc}
	 * @see com.google.inject.servlet.GuiceFilter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(filterConfig);
		super.init(filterConfig);
	}
}
