package com.bankingapplication.rest.domain;

import com.bankingapplication.rest.domain.base.BaseMaster;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Table(name="CUSTOMER")
@Entity
public class Customer extends BaseMaster implements Serializable{
	
	private static final long serialVersionUID = -6759774343110776659L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="ID",updatable = false)
	private String id;
	
	@Column(name="NAME")
	private String customerName;
	
	@Column(name="DATE_OF_BIRTH" ,nullable=true)
	private Long dateOfBirth;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	@Column(name="IDENTIFICATION_NUMBER", unique = true, nullable = false)
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
