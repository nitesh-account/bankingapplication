package com.bankingapplication.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * LowBalanceException class is used to handle negative balance exception
 *
 * @author Nitesh Kumar
 */
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class LowBalanceException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(LowBalanceException.class);
	private static final long serialVersionUID = -3916525550413865316L;


    public LowBalanceException() {
        super();
    }

    public LowBalanceException(String message) {
        super(message);
    }

    public LowBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
