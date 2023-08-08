package com.team4.project;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements Filter {

	JWTUtil jwtUtil = new JWTHelper();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	/*
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if (uri.startsWith("/gateway")) {
			System.out.println("Checking the header");
			// continue on to get-token endpoint
			String authheader = req.getHeader("authorization");
			if (authheader != null && authheader.length() > 7 && authheader.startsWith("Bearer")) {
				String jwt_token = authheader.substring(7, authheader.length());
				if (jwtUtil.verifyToken(jwt_token)) {
					chain.doFilter(request, response);
					return;
				}
			}
		}

		else {
			// check JWT token
			System.out.println("Not an authorized resource...");
			chain.doFilter(request, response);
			return;

		}
		res.sendError(HttpServletResponse.SC_FORBIDDEN, "failed authentication");

	}
	*/
}
