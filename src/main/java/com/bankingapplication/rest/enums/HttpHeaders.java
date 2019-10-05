package com.bankingapplication.rest.enums;

/**
 * 
 * @author Nitesh Kumar
 *
 */
public enum HttpHeaders {

	USER_NAME("x-username");

	String value;

	public String getValue() {
		return value;
	}

	private HttpHeaders(String value) {
	this.value = value;
}
}
