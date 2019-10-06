package com.bankingapplication.rest.domain;

import com.bankingapplication.rest.domain.base.BaseMaster;
import com.bankingapplication.rest.domain.base.CustomSeqGenerator;
import com.bankingapplication.rest.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Transaction entity is used to define transaction related properties.
 *
 * @author Nitesh Kumar
 */

@Table(name="TRANSACTION")
@Entity
public class Transaction extends BaseMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @GenericGenerator(
        name = "transaction_seq", 
        strategy = "com.bankingapplication.rest.domain.base.CustomSeqGenerator",
        parameters = {
            @Parameter(name = CustomSeqGenerator.INCREMENT_PARAM, value = "50"),
            @Parameter(name = CustomSeqGenerator.VALUE_PREFIX_PARAMETER, value = "T_"),
            @Parameter(name = CustomSeqGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountNumber", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;

    @Column(name="TRANSACTION_TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name="TRANSACTION_AMOUNT")
    private Double amount;

    public Transaction(String createdBy, Account account, TransactionType transactionType, Double amount) {
        super(createdBy);
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(account, that.account) &&
                transactionType == that.transactionType &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, transactionType, amount);
    }
}
