package com.bankingapplication.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AccountCloseException class is used to handle negative balance exception
 *
 * @author Nitesh Kumar
 */
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class AccountCloseException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(AccountCloseException.class);
	private static final long serialVersionUID = -3916525550413865316L;


    public AccountCloseException() {
        super();
    }

    public AccountCloseException(String message) {
        super(message);
    }

    public AccountCloseException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
