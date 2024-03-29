package com.bankingapplication.rest.repository;

import com.bankingapplication.rest.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AccountRepository is used to get account related data from database
 *
 * @author Nitesh Kumar
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Page<Account> findByCustomerId(String customerId, Pageable pageable);

    Optional<Account> findByAccountNumber(String accountNumber);
}
