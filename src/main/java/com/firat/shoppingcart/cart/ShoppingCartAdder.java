package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firat.shoppingcart.cart.ShoppingCartConstants.*;

/**
 * responsible to add a product into the shopping cart.
 * contains similar rules before the add process.
 */
public class ShoppingCartAdder extends CartAdder<Product> {

    @Override
    public Result add(Product p, int quantity, ShoppingCart cart) {

        // minus values have prohibited
        if (quantity < 1)
            return new Result(1,FAIL_ADD_PRODUCT_INVALID_QUANTITY);
        // shopping cart has limited size
        if (cart.size() + quantity > SHOPPING_CART_LIMIT)
            return new Result(1,FAIL_ADD_PRODUCT_INSUFFICIENT_SPACE);
        // product value wont be null
        Optional<Product> product = Optional.ofNullable(p);
        if (!product.isPresent())
            return new Result(1,FAIL_ADD_PRODUCT_NULL);
        // product title wont be null
        Optional<String> productTitle = Optional.ofNullable(product.get().getTitle());
        if (!productTitle.isPresent())
            return new Result(1,FAIL_ADD_PRODUCT_TITLE_NULL);
        // product title wont be empty
        if (productTitle.get().trim().isEmpty())
            return new Result(1,FAIL_ADD_PRODUCT_TITLE_EMPTY);
        // category data in product object might be null
        Optional<Category> category = Optional.ofNullable(product.get().getCategory());
        if (!category.isPresent())
            return new Result(1,FAIL_ADD_CATEGORY_NULL);

        Optional<String> categoryTitle = Optional.ofNullable(category.get().getTitle());
        return categoryTitle.map(title->{
            // category title could not be empty.
            if (title.trim().isEmpty())
                return new Result(FAIL_ADD_CATEGORY_TITLE_EMPTY);

            Optional<List<ShoppingCartItem>> itemsOfCategory = Optional.ofNullable(cart.getCart().get(title));
            if (!itemsOfCategory.isPresent()){
                // new category
                cart.getCart()
                        .put(p.getCategory().getTitle().trim(),new ArrayList<ShoppingCartItem>()
                        {
                            {
                                add(new ShoppingCartItem(p,quantity));
                            }
                        });
                return new Result();
            }
            // category may already existed in the cart
            itemsOfCategory.ifPresent(i->
                i.add(new ShoppingCartItem(p,quantity))
            );
            return new Result();
        }).orElse(new Result(FAIL_ADD_CATEGORY_TITLE_NULL));
    }
}
