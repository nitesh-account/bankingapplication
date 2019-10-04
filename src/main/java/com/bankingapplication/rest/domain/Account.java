package com.bankingapplication.rest.domain;

import com.bankingapplication.rest.domain.base.BaseMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Table(name="ACCOUNT")
@Entity
public class Account extends BaseMaster implements Serializable {
	private static final long serialVersionUID = -6380749575516426900L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="ACCOUNT_NUMBER",updatable = false)
	private String accountNumber;

	@Column(name="ACCOUNT_NAME")
	private String accountName;

	@Column(name="ACCOUNT_TYPE")
	private AccountType accountType;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="BALANCE")
	@NotNull
	@Min(0)
	private Double balance;
	
	@Column(name="OPENING_DATE")
	private Long openingDate;

	@Column(name="CLOSING_DATE")
	private Long closingDate;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Long openingDate) {
		this.openingDate = openingDate;
	}

	public Long getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Long closingDate) {
		this.closingDate = closingDate;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return accountNumber.equals(account.accountNumber) &&
				accountName.equals(account.accountName) &&
				balance.equals(account.balance) &&
				openingDate.equals(account.openingDate) &&
				closingDate.equals(account.closingDate) &&
				customer.equals(account.customer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
	}
}
