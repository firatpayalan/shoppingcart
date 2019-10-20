package com.firat.shoppingcart;
/**
 * Coupon is the special Discount class.
 */
public class Coupon extends Discount  {
    private CouponCalculator applier;

    public Coupon(int minimumAmount, double discountValue, CouponCalculator applier) {
        super(discountValue,minimumAmount);
        this.applier = applier;
    }

    public double apply(double price){
        return this.applier.calculate(this,price);
    }
}
