package com.firat.shoppingcart.discount;

import com.firat.shoppingcart.cart.ShoppingCart;

public interface DiscountApplicable {
    void apply(ShoppingCart cart);

}
