package com.bankingapplication.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TransactionDTO is used to define properties for custom related data set.
 *
 * @author Nitesh Kumar
 */

public class TransactionDTO {
    private Logger logger = LoggerFactory.getLogger(TransactionDTO.class);

    private String transactionId;
    private String accountNumber;
    private Double transactionAmount;
    private Double balance;
    private String customerId;
    private String customerName;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "transactionId='" + transactionId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", transactionAmount='" + transactionAmount + '\'' +
                ", balance='" + balance + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
