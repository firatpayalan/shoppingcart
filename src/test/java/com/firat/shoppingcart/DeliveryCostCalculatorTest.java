package com.firat.shoppingcart;

import org.junit.Assert;
import org.junit.Test;

public class DeliveryCostCalculatorTest {
    @Test
    public void calculate_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2,1);
        Assert.assertEquals(0,Double.compare(5.99,deliveryCostCalculator.calculate(shoppingCart)));
    }
}
