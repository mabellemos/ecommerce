package com.compasso.ecommerce_app.core.exception.customer;

public class CustomerException extends Exception{

    private static final long serialVersionUID = 1L;

    public CustomerException() {
        super();
    }

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Exception cause) {
        super(message, cause);
    }

    public CustomerException(Exception e) {
        super(e);
    }
}