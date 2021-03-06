package com.firat.shoppingcart.discount.coupon;
/**
 *  makes a rate based coupon calculation
 */
public class RateCouponCalculator extends CouponCalculator {
    @Override
    public double calculate(Coupon coupon, double amount) {
        return (amount*coupon.getDiscountValue())/100;
    }
}
