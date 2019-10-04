package com.bankingapplication.rest.repository;

import com.bankingapplication.rest.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Page<Account> findByCustomerId(String customerId, Pageable pageable);
}
