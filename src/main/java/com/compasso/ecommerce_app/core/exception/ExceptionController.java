package com.compasso.ecommerce_app.core.exception;

import com.compasso.ecommerce_app.core.exception.address.AddressException;
import com.compasso.ecommerce_app.core.exception.category.CategoryException;

import com.compasso.ecommerce_app.core.exception.customer.CustomerException;
import com.compasso.ecommerce_app.core.exception.product.ProductException;
import com.compasso.ecommerce_app.core.exception.sale.SaleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value
            = {CategoryException.class})
    protected ResponseEntity<Object> notFound(CategoryException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value
            = {CustomerException.class})
    protected ResponseEntity<Object> notFound(CustomerException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value
            = {AddressException.class})
    protected ResponseEntity<Object> notFound(AddressException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value
            = {ProductException.class})
    protected ResponseEntity<Object> notFound(ProductException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value
            = {SaleException.class})
    protected ResponseEntity<Object> notFound(SaleException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}