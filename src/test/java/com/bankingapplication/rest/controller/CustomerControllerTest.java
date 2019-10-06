package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.domain.Customer;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * CustomerControllerTest is used to test customer related API's.
 *
 * @author Nitesh Kumar
 */

public class CustomerControllerTest extends AbstractControllerTest {

	@Test
	public void shouldAddCustomer() throws Exception {
		// given
		String customerBody = "{\"customerName\":\"Nitesh\", \"phoneNumber\":\"78345899002\"}";
		Customer customer = new Customer("Admin","Nitesh", (long) 123345677,"78345899002","KL455788");

		// when
		when(customerService.save(customer)).thenReturn(customer);

		// then
		mockMvc.perform(post("/api/customers")
				.content(customerBody)
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	public void shouldReturnFoundCustomer() throws Exception {
		// given
		Customer customer = new Customer("Admin","Nitesh", (long) 123345677,"78345899002","KL455788");

		// when
		when(customerService.findByCustomerId("C_00001")).thenReturn(customer);

		// then
		mockMvc.perform(get("/api/customers/C_00001").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customerName", is("Nitesh")))
				.andExpect(jsonPath("$.phoneNumber", is("78345899002")));

	}
}