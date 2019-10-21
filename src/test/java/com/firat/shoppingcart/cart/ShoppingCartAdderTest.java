package com.firat.shoppingcart.cart;

import org.junit.Assert;
import org.junit.Test;

public class ShoppingCartAdderTest {
    @Test
    public void add_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,90,new ShoppingCartAdder());
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        Assert.assertEquals(0,adder.add(iphoneXs,1,cart).getStatus());
    }
    @Test
    public void add_nullProduct_success(){
        Category category = new Category("İletişim");
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        Assert.assertEquals(1,adder.add(null,1,cart).getStatus());
    }
    @Test
    public void add_emptyCategoryName_success(){
        Category category = new Category("                 ");
        Product iphoneXs = new Product("Iphone XS",category,90,new ShoppingCartAdder());
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        Assert.assertEquals(1,adder.add(iphoneXs,1,cart).getStatus());
    }
    @Test
    public void add_emptyProductName_success(){
        Category category = new Category("iletişim");
        Product iphoneXs = new Product("",category,90,new ShoppingCartAdder());
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        Assert.assertEquals(1,adder.add(iphoneXs,1,cart).getStatus());
    }
    @Test
    public void add_emptyAddStrategy_success(){
        Category category = new Category("iletişim");
        Product iphoneXs = new Product("",category,90,null);
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartAdder adder = new ShoppingCartAdder();
        Assert.assertEquals(1,adder.add(iphoneXs,1,cart).getStatus());

    }

}
