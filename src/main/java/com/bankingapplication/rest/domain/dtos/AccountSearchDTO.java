package com.bankingapplication.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AccountSearchDTO is used to define properties for custom related data set.
 *
 * @author Nitesh Kumar
 */

public class AccountSearchDTO {
    private Logger logger = LoggerFactory.getLogger(AccountSearchDTO.class);
    private String customerName;
    private String customerId;
    private String accountStatus;
    private String accountNumber;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountSearchDTO{" +
                "customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
