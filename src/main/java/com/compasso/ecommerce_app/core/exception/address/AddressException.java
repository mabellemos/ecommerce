package com.compasso.ecommerce_app.core.exception.address;

public class AddressException extends Exception{

    private static final long serialVersionUID = 1L;

    public AddressException() {
        super();
    }

    public AddressException(String message) {
        super(message);
    }

    public AddressException(String message, Exception cause) {
        super(message, cause);
    }

    public AddressException(Exception e) {
        super(e);
    }
}