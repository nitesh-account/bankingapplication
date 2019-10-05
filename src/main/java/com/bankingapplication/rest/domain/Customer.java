package com.bankingapplication.rest.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bankingapplication.rest.domain.base.BaseMaster;
import com.bankingapplication.rest.domain.base.CustomSequenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Table(name = "CUSTOMER")
@Entity
public class Customer extends BaseMaster implements Serializable {
    private static final long serialVersionUID = -6759774343110776659L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @GenericGenerator(
            name = "customer_seq",
            strategy = "com.bankingapplication.rest.domain.base.CustomSequenceGenerator",
            parameters = {
                    @Parameter(name = CustomSequenceGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = CustomSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "C_"),
                    @Parameter(name = CustomSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    private String id;

    @Column(name = "NAME")
    private String customerName;

    @Column(name = "DATE_OF_BIRTH", nullable = true)
    private Long dateOfBirth;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "IDENTIFICATION_NUMBER", unique = true, nullable = false)
    private String identificationNumber;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public Customer() {
    }

    public Customer(String createdBy, String customerName, Long dateOfBirth, String phoneNumber, String identificationNumber) {
        super(createdBy);
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.identificationNumber = identificationNumber;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(dateOfBirth, customer.dateOfBirth) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, dateOfBirth, phoneNumber);
    }
}
