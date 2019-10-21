package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.discount.campaign.AmountCampaignCalculator;
import com.firat.shoppingcart.discount.campaign.Campaign;
import com.firat.shoppingcart.discount.campaign.RateCampaignCalculator;
import com.firat.shoppingcart.discount.coupon.AmountCouponCalculator;
import com.firat.shoppingcart.discount.coupon.Coupon;
import com.firat.shoppingcart.discount.coupon.RateCouponCalculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * naming convention is
 *
 * methodName_case_expectedUnitTestStatus
 * For example;
 * addItem_categoryIsNull_success
 * methodName: addItem
 * case: category object is null
 * expected unit test status: success
 */
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
    public void addItem_categoryIsNull_success(){
        Product iphoneXs = new Product("Iphone XS",null,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Assert.assertEquals(0,shoppingCart.getCart().size());
    }
    @Test
    public void addItem_productIsNull_success(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(null,1);
        Assert.assertEquals(0,shoppingCart.getCart().size());
    }

    @Test
    public void addItem_productTitleIsNull_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product(null,category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Assert.assertEquals(0,shoppingCart.getCart().size());
    }

    //has to be append
    @Test
    public void  addItem_duplicate_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        Product iphoneXsDuplicate = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        shoppingCart.addItem(iphoneXsDuplicate,1);
        Assert.assertEquals(2,shoppingCart.getCart().get(category.getTitle()).size());
    }


    @Test
    public void addItem_minimumValue_success(){
        //1 is minimum value
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,0);
        Assert.assertEquals(0,shoppingCart.size());
    }

    @Test
    public void addItem_maximumValue_success(){
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
    public void applyDiscount_nullCategory_success(){
        Product iphoneXs = new Product("Iphone XS",null,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Campaign campaign = new Campaign(null,500,new AmountCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        Assert.assertEquals(0,Double.compare(shoppingCart.getCampaignDiscount(),0));
    }

    @Test
    public void applyDiscount_campaignNullStrategy_success(){
        Category parent = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",parent,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Campaign campaign = new Campaign(parent,500,null,1);
        shoppingCart.applyDiscount(campaign);
        Assert.assertEquals(0,Double.compare(shoppingCart.getCampaignDiscount(),0));
    }
    @Test
    public void applyDiscount_couponNullStrategy_success(){
        Category parent = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",parent,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,500,null);
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getCouponDiscount(),0));
    }
    @Test
    public void applyDiscount_nullDiscount_success(){
        Category parent = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",parent,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Campaign campaign = new Campaign(parent,500,null,1);
        shoppingCart.applyDiscount(null);
        Assert.assertEquals(0,Double.compare(shoppingCart.getCampaignDiscount(),0));
    }

    @Test
    public void applyDiscount_campaignTypeAmount_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,2);
        Campaign campaign = new Campaign(category,500,new AmountCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),17500));
    }
    @Test
    public void applyDiscount_campaignTypeRate_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,2);
        Campaign campaign = new Campaign(category,50,new RateCampaignCalculator(),1);
        shoppingCart.applyDiscount(campaign);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),9000));
    }

    @Test
    public void applyDiscount_couponTypeAmount_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,1000,new AmountCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),8000));
    }

    @Test
    public void applyDiscount_couponTypeRate_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,9000,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,50,new RateCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),4500));
    }

    @Test
    public void applyDiscount_couponTypeAmountNotApplicable_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,90,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,50,new AmountCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),90));
    }

    @Test
    public void applyDiscount_couponTypeRateNotApplicable_success(){
        Category category = new Category("İletişim");
        Product iphoneXs = new Product("Iphone XS",category,90,new ShoppingCartAdder());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(iphoneXs,1);
        Coupon coupon = new Coupon(100,50,new RateCouponCalculator());
        shoppingCart.applyDiscount(coupon);
        Assert.assertEquals(0,Double.compare(shoppingCart.getTotalAmountAfterDiscounts(),90));
    }

    @Test
    public void applyDiscount_campaignAndcoupon_success(){
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
