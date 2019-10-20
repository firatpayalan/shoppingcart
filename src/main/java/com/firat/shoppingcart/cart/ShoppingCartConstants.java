package com.firat.shoppingcart.cart;

public class ShoppingCartConstants {

    //shopping cart takes only $SHOPPING_CART_LIMIT product
    public static final int SHOPPING_CART_LIMIT                     =   100;
    public static final String FAIL_ADD_PRODUCT_INVALID_QUANTITY    =   "You have one item to add into shopping cart at least";
    public static final String FAIL_ADD_PRODUCT_INSUFFICIENT_SPACE  =   "Shopping Cart Limit Exceeded";
    public static final String FAIL_ADD_CATEGORY_TITLE_NULL =   "Category name must be provided";
    public static final String FAIL_ADD_PRODUCT_NULL                =   "Product data could not be null";
    public static final String FAIL_ADD_PRODUCT_TITLE_NULL                =   "Product title could not be null";
    public static final String FAIL_ADD_CATEGORY_TITLE_EMPTY =   "Category title could not be empty";
    public static final String FAIL_ADD_PRODUCT_TITLE_EMPTY =   "Product title could not be empty";
    public static final String FAIL_ADD_CATEGORY_NULL               =   "Category data could not be null";
    public static final String FAIL_ADD_STRATEGY_NULL      =   "Shopping Cart Adding module does not registered";
}
