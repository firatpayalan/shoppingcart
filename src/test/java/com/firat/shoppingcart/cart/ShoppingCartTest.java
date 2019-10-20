package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.discount.campaign.AmountCampaignCalculator;
import com.firat.shoppingcart.discount.campaign.Campaign;
import com.firat.shoppingcart.discount.campaign.RateCampaignCalculator;
import com.firat.shoppingcart.discount.coupon.AmountCouponCalculator;
import com.firat.shoppingcart.discount.coupon.Coupon;
import com.firat.shoppingcart.discount.coupon.RateCouponCalculator;
import org.junit.Assert;
import org.junit.Test;

public class ShoppingCartTest {
    @Test
    public void addItem_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Assert.assertEquals(1,shoppingCart.getCart().size());
    }
    @Test
    public void addItem_null_category_failed(){
        Product iphoneXs = new Product("Iphone XS",null,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Assert.assertEquals(0,shoppingCart.getCart().size());
    }
    @Test
    public void addItem_null_product(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(null,1);
        Assert.assertEquals(0,shoppingCart.getCart().size());
    }

    @Test
    public void addItem_null_productTitle_failed(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product(null,category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Assert.assertEquals(0,shoppingCart.getCart().size());
    }

    //has to be append
    @Test
    public void  addItem_duplicate(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        Product iphoneXsDuplicate = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        shoppingCart.addItem(iphoneXsDuplicate,1);
        Assert.assertEquals(2,shoppingCart.getCart().get(category.getTitle()).size());
    }


    @Test
    public void addItem_minimumValue(){
        //1 is minimum value
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,0);
        Assert.assertEquals(0,shoppingCart.size());
    }

    @Test
    public void addItem_maximumValue(){
        //100 is maximum value
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,101);
        Assert.assertEquals(0,shoppingCart.getCart().size());
    }
    @Test
    public void applyDiscount_parentCampaign_success(){
        Category child = new Category("cep telefonu");
        Category parent = new Category("İletişim");
        parent.setChild(child);
        Product iphoneXs = new Product("Iphone XS",child,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Campaign campaign = new Campaign(parent,1000,new AmountCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),8000));
    }

    @Test
    public void applyDiscount_campaign_typeAmount_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,2);
        Campaign campaign = new Campaign(category,500,new AmountCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),17500));
    }
    @Test
    public void applyDiscount_campaign_typeRate_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,2);
        Campaign campaign = new Campaign(category,50,new RateCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),9000));
    }

    @Test
    public void applyDiscount_coupon_typeAmount_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,1000,new AmountCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),8000));
    }

    @Test
    public void applyDiscount_coupon_typeRate_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,50,new RateCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),4500));
    }

    @Test
    public void applyDiscount_coupon_typeAmount_notApplicable(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,90,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,50,new AmountCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),90));
    }

    @Test
    public void applyDiscount_coupon_typeRate_notApplicable(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,90,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,50,new RateCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),90));
    }

    @Test
    public void applyDiscount_campaign_and_coupon_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,1200,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,5);
        Campaign campaign = new Campaign(category,50,new RateCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        Coupon coupon = new Coupon(2000,500,new AmountCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),2500));
    }
}