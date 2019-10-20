package com.firat.shoppingcart;

import com.firat.shoppingcart.cart.Category;
import com.firat.shoppingcart.cart.Product;
import com.firat.shoppingcart.cart.ShoppingCart;
import com.firat.shoppingcart.cart.ShoppingCartAdder;
import com.firat.shoppingcart.discount.campaign.AmountCampaignCalculator;
import com.firat.shoppingcart.discount.campaign.Campaign;

public class ApplicationMain {
    public static void main(String[] args) {
        Category child = new Category("cep telefonu");
        Category parent = new Category("İletişim");
        parent.setChild(child);
        Product iphoneXs = new Product("Iphone XS",child,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Campaign campaign = new Campaign(parent,1000,new AmountCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        shoppingCart.print();

    }
}
