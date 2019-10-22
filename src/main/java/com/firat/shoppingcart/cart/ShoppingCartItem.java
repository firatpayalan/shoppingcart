package com.firat.shoppingcart.cart;

import java.util.Objects;

/**
 * an item that placed in the Shopping Cart
 */
public class ShoppingCartItem {
    private Product product;
    // amount of the product
    private int quantity;

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem shoppingCartItem = (ShoppingCartItem) o;
        return Objects.equals(product,shoppingCartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
