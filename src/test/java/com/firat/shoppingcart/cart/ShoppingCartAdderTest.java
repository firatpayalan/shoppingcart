package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.cart.exception.CategoryNullException;
import com.firat.shoppingcart.cart.exception.ProductNullException;
import com.firat.shoppingcart.cart.exception.StrategyAdderNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class ShoppingCartAdderTest {
    @Test
    public void add_success(){
        Category category = new Category("İletişim");
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        Product iphoneXs = new Product("Iphone XS",category,90,adder);
        adder.add(iphoneXs,1,cart);
        Assert.assertEquals(1,cart.getCart().get(category.getTitle()).size());
    }
    @Test(expected = ProductNullException.class)
    public void add_nullProduct_productNullException_expected(){
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        adder.add(null,1,cart);
    }
    @Test(expected = CategoryNullException.class)
    public void add_emptyCategoryName_categoryNullException_expected(){
        Category category = new Category("                 ");
        Product iphoneXs = new Product("Iphone XS",category,90,new ShoppingCartAdder());
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        adder.add(iphoneXs,1,cart);
    }
    @Test(expected = ProductNullException.class)
    public void add_emptyProductName_productNullException_expected(){
        Category category = new Category("iletişim");
        Product iphoneXs = new Product("",category,90,new ShoppingCartAdder());
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        adder.add(iphoneXs,1,cart);
    }

}
