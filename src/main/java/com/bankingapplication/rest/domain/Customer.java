package com.bankingapplication.rest.domain;

import com.bankingapplication.rest.domain.base.BaseMaster;
import com.bankingapplication.rest.domain.base.CustomSeqGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Customer entity is used to define customer related properties.
 *
 * @author Nitesh Kumar
 */

@Table(name = "CUSTOMER")
@Entity
public class Customer extends BaseMaster implements Serializable {
    private static final long serialVersionUID = -6759774343110776659L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @GenericGenerator(
            name = "customer_seq",
            strategy = "com.bankingapplication.rest.domain.base.CustomSeqGenerator",
            parameters = {
                    @Parameter(name = CustomSeqGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = CustomSeqGenerator.VALUE_PREFIX_PARAMETER, value = "C_"),
                    @Parameter(name = CustomSeqGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    private String id;

    @Column(name = "NAME")
    @NotNull
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
