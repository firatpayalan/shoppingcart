package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.Result;
import com.firat.shoppingcart.cart.exception.CategoryNullException;
import com.firat.shoppingcart.cart.exception.ProductNullException;
import com.firat.shoppingcart.cart.exception.QuantityInvalidException;
import com.firat.shoppingcart.cart.exception.ShoppingCartLimitExceeded;

import javax.swing.text.html.Option;
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
    public void add(Product p, int quantity, ShoppingCart cart) {

        // minus values have prohibited
        if (quantity < 1)
            throw new QuantityInvalidException();
        // shopping cart has limited size
        if (cart.size() + quantity > SHOPPING_CART_LIMIT)
            throw new ShoppingCartLimitExceeded();

        Optional.ofNullable(p)
                .orElseThrow(ProductNullException::new);

        Optional.ofNullable(p.getTitle())
                .map(String::trim)
                .filter(trimmed -> !trimmed.isEmpty())
                .orElseThrow(() -> new ProductNullException(FAIL_ADD_PRODUCT_TITLE_NULL));


        Optional<Category> category = Optional.ofNullable(p.getCategory());
        if (!category.isPresent())
            throw new CategoryNullException(FAIL_ADD_CATEGORY_NULL);

        Optional<String> trimmedCategoryTitle = category.map(Category::getTitle)
                .map(String::trim)
                .filter(trimmed -> !trimmed.isEmpty());
        if (!trimmedCategoryTitle.isPresent())
            throw new CategoryNullException(FAIL_ADD_CATEGORY_TITLE_NULL);

        trimmedCategoryTitle
                .ifPresent(title -> {

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
                    }
                    // category may already existed in the cart
                    itemsOfCategory.ifPresent(i->
                            i.add(new ShoppingCartItem(p,quantity))
                    );
                });
    }
}
