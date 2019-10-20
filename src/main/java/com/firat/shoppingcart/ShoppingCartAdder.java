package com.firat.shoppingcart;

import java.util.ArrayList;
import java.util.Optional;

import static com.firat.shoppingcart.ShoppingCartConstants.*;

/**
 * responsible to add a product into the shopping cart.
 * contains similar rules before the add process.
 */
public class ShoppingCartAdder extends CartAdder<Product> {

    @Override
    public Result add(Product p, int quantity, ShoppingCart cart) {
        if (quantity < 1)
            return new Result(1,FAIL_ADD_PRODUCT_INVALID_QUANTITY);
        if (cart.size() + quantity > SHOPPING_CART_LIMIT)
            return new Result(1,FAIL_ADD_PRODUCT_INSUFFICIENT_SPACE);
        // new category
        if (cart.getCart().get(p.getCategory().getTitle()) == null){
            cart.getCart()
                    .put(p.getCategory().getTitle(),new ArrayList<ShoppingCartItem>()
                    {
                        {
                            add(new ShoppingCartItem(p,quantity));
                        }
                    });
            return new Result();
        }
        // category may already existed in the cart
        cart.getCart().get(p.getCategory().getTitle()).add(new ShoppingCartItem(p,quantity));
        return new Result();
    }
}
