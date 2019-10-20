package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.Result;
import com.firat.shoppingcart.cart.ShoppingCart;

/**
 * defines a product adding strategy into the shopping cart
 * @param <T>
 */
public abstract class CartAdder<T> {
    public Result add(T p, int quantity, ShoppingCart cart){return null;}
}