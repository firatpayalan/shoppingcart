package com.firat.shoppingcart.cost;

import com.firat.shoppingcart.cart.Category;
import com.firat.shoppingcart.cart.Product;
import com.firat.shoppingcart.cart.ShoppingCart;
import com.firat.shoppingcart.cart.ShoppingCartAdder;
import com.firat.shoppingcart.cart.exception.QuantityInvalidException;
import com.firat.shoppingcart.cost.DeliveryCostCalculator;
import com.firat.shoppingcart.cost.exception.ShoppingCartNullException;
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

    @Test
    public void calculate_multipleProduct_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        Product huawei = new Product("huawei",category,100,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        shoppingCart.addItem(huawei,1);
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2,1);
        Assert.assertEquals(0,Double.compare(6.99,deliveryCostCalculator.calculate(shoppingCart)));
    }

    @Test(expected = QuantityInvalidException.class)
    public void calculate_zeroQuantity_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,0);
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2,1);
        deliveryCostCalculator.calculate(shoppingCart);
    }


    @Test
    public void calculate_multipleCategory_success(){
        Category communication = new Category("İletişim");
        Category gaming = new Category("Oyuncak");
        Product iphoneXs = new Product("Iphone XS",communication,9000,new ShoppingCartAdder());
        Product psFour = new Product("ps4",gaming,100,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        shoppingCart.addItem(psFour,1);
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2,1);
        Assert.assertEquals(0,Double.compare(8.99,deliveryCostCalculator.calculate(shoppingCart)));
    }

    @Test(expected = ShoppingCartNullException.class)
    public void calculate_shoppingCartNull_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2,1);
        deliveryCostCalculator.calculate(null);
    }
}
