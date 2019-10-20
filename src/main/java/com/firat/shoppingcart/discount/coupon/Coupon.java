package com.firat.shoppingcart.discount.coupon;

import com.firat.shoppingcart.discount.Discount;

import java.util.Optional;

/**
 * Coupon is the special Discount class.
 */
public class Coupon extends Discount {
    private CouponCalculator calculator;

    public Coupon(int minimumAmount, double discountValue, CouponCalculator applier) {
        super(discountValue,minimumAmount);
        this.calculator = applier;
    }

    public double apply(double price){
        return Optional.ofNullable(this.calculator)
                .map(calculator-> calculator.calculate(this,price))
                .orElse((double)0);
    }
}
