package com.google.sso.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	final static Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "PUT, PATCH, POST, GET, OPTIONS, DELETE");

		if (request.getMethod().equals("OPTIONS")) {
			LOGGER.debug("Setting CORS Headers");
			response.setHeader("Access-Control-Allow-Headers", "accept,content-type,host,user-agent");
			LOGGER.debug("Received OPTIONS request and responding with 204");
			response.setStatus(204);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}