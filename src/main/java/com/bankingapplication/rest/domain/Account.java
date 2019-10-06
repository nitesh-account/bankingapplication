package com.bankingapplication.rest.domain;

import com.bankingapplication.rest.domain.base.BaseMaster;
import com.bankingapplication.rest.domain.base.CustomSequenceGenerator;
import com.bankingapplication.rest.enums.AccountType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Objects;

/**
 * Account entity is used to define account related properties.
 *
 * @author Nitesh Kumar
 */

@Table(name="ACCOUNT")
@Entity
public class Account extends BaseMaster implements Serializable {
	private static final long serialVersionUID = -6380749575516426900L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
	@GenericGenerator(
			name = "account_seq",
			strategy = "com.bankingapplication.rest.domain.base.CustomSequenceGenerator",
			parameters = {
					@org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.INCREMENT_PARAM, value = "50"),
					@org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),
					@org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
	private String accountNumber;

	@Column(name="ACCOUNT_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="BALANCE")
	@Min(0)
	private Double balance = Double.valueOf(0);
	
	@Column(name="OPENING_DATE")
	private Long openingDate;

	@Column(name="CLOSING_DATE")
	private Long closingDate;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public Account() {
	}

	public Account(String createdBy, AccountType accountType, String accountStatus, @Min(0) Double balance, Long openingDate, Long closingDate, Customer customer) {
		super(createdBy);
		this.accountType = accountType;
		this.accountStatus = accountStatus;
		this.balance = balance;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		this.customer = customer;
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
