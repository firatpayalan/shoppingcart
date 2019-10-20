package com.firat.shoppingcart.discount.coupon;

/**
 *  makes an amount based coupon calculation
 */
public class AmountCouponCalculator extends CouponCalculator {
    @Override
    public double calculate(Coupon coupon, double amount) {
        return coupon.getDiscountValue();
    }
}
