package com.firat.shoppingcart;

/**
 * defines a product adding strategy into the shopping cart
 * @param <T>
 */
public abstract class CartAdder<T> {
    public Result add(T p, int quantity, ShoppingCart cart){return null;}
}
