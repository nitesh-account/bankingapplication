package com.bankingapplication.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class LowBalanceException extends RuntimeException {

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
