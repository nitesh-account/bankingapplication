package com.bankingapplication.rest.domain;
/**
 * Enumeration of types of accounts
 *  
 * @author Nitesh Kumar
 *
 */
public enum AccountType {
	CURRENT("current"), SAVINGS("savings");

	String value;
	public String getValue() {
		return value;
	}

	private AccountType(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return value;
	}
}
