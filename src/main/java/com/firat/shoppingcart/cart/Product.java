package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.Result;
import com.firat.shoppingcart.cart.exception.StrategyAdderNotFoundException;

import java.util.Objects;
import java.util.Optional;

public class Product extends ParentProduct {
    private String title;
    private Category category;
    private Double price;
    // contains shopping cart addition strategy
    private CartAdder adder;

    public Product(String title, Category category, double price,CartAdder adder) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.adder = adder;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }


    /**
     * executes the addition strategy
     * @param cart shopping cart
     * @param quantity amount of the product
     */
    public void add(ShoppingCart cart, int quantity){
        Optional.ofNullable(this.adder)
                .orElseThrow(StrategyAdderNotFoundException::new)
                .add(this,quantity,cart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(title, product.title) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category);
    }
}
