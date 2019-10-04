package com.bankingapplication.rest.enums;

public enum TransactionType {
	DEPOSIT("deposit"),WITHDRAW("withdraw");
	String value;
	public String getValue() {
		return value;
	}

	private TransactionType(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return value;
	}
}
