package com.firat.shoppingcart.cart;

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
}
