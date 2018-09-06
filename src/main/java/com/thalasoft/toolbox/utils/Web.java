package com.thalasoft.toolbox.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

public class Web {

	public static HttpHeaders getAllHeaders(HttpServletResponse response) {
		HttpHeaders headers = new HttpHeaders();
		for (String key : response.getHeaderNames()) {
			String value = response.getHeader(key);
			headers.add(key, value);
		}
		return headers;
	}

	public static HttpHeaders getAllHeaders(HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			headers.add(key, value);
		}
		return headers;
	}

}
