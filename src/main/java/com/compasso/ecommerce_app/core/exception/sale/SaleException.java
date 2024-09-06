package com.compasso.ecommerce_app.core.exception.sale;

public class SaleException extends Exception{

    private static final long serialVersionUID = 1L;

    public SaleException () {
        super();
    }

    public SaleException (String message) {
        super(message);
    }

    public SaleException (String message, Exception cause) {
        super(message, cause);
    }

    public SaleException (Exception e) {
        super(e);
    }

}