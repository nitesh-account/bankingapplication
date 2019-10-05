package com.bankingapplication.rest.repository;

import com.bankingapplication.rest.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TransactionRepository is used to get transaction related data from database
 *
 * @author Nitesh Kumar
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
