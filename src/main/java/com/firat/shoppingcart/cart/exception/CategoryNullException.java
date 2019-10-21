package com.firat.shoppingcart.cart.exception;

public class CategoryNullException extends RuntimeException {
    public CategoryNullException(String message) {
        super(message);
    }
    public CategoryNullException(){
        super();
    }
}
