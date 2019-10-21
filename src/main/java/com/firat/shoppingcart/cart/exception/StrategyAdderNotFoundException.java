package com.firat.shoppingcart.cart.exception;

public class StrategyAdderNotFoundException extends RuntimeException {
    public StrategyAdderNotFoundException() {
    }

    public StrategyAdderNotFoundException(String message) {
        super(message);
    }
}
