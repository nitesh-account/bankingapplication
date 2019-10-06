package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.service.AccountService;
import com.bankingapplication.rest.service.CustomerService;
import com.bankingapplication.rest.service.TransactionService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected CustomerService customerService;

	@MockBean
	protected AccountService accountService;

	@MockBean
	protected TransactionService transactionService;

	@Before
	public void setUp() {
		Mockito.reset(customerService, accountService, transactionService);
	}

}
