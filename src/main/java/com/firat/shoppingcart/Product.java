package com.firat.shoppingcart;

/**
 *
 */
public class Product {
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

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

    /**
     * executes the addition strategy
     * @param cart shopping cart
     * @param quantity amount of the product
     */
    public void add(ShoppingCart cart,int quantity){
        this.adder.add(this,quantity,cart);
    }
}
