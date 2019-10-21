package com.firat.shoppingcart.cart.exception;

public class ProductNullException extends RuntimeException {
    public ProductNullException(String message) {
        super(message);
    }
    public ProductNullException(){
        super();
    }
}
