package com.firat.shoppingcart.discount.coupon;

import com.firat.shoppingcart.cart.ShoppingCart;
import com.firat.shoppingcart.discount.Discount;
import com.firat.shoppingcart.discount.DiscountApplicable;
import com.firat.shoppingcart.discount.exception.CalculatorNullException;

import java.util.Optional;

/**
 * Coupon is the special Discount class.
 */
public class Coupon extends Discount implements DiscountApplicable {
    private CouponCalculator calculator;

    public Coupon(int minimumAmount, double discountValue, CouponCalculator applier) {
        super(discountValue,minimumAmount);
        this.calculator = applier;
    }

    public double discount(double price){
        return Optional.ofNullable(this.calculator)
                .orElseThrow(CalculatorNullException::new)
                .calculate(this,price);
    }

    @Override
    public void apply(ShoppingCart cart) {
        if (cart.getTotalPrice() > this.getItemThreshold())
            cart.getDiscounts().add(this);
    }
}
