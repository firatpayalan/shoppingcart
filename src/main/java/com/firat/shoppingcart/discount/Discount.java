package com.firat.shoppingcart.discount;

/**
 * pure discount class
 */
public class Discount {
    // the value either be applied into as amount or rate
    private double discountValue;
    // is the rule of applying discount into the shopping cart
    private int itemThreshold;

    public Discount(double discountValue, int itemThreshold) {
        this.discountValue = discountValue;
        this.itemThreshold = itemThreshold;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public void setItemThreshold(int itemThreshold) {
        this.itemThreshold = itemThreshold;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public int getItemThreshold() {
        return itemThreshold;
    }
}
