package com.firat.shoppingcart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ShoppingCart {
    private final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);
    // CategoryName as key, ListOfProducts as values. contains all product into the Shopping Cart
    private Map<String, List<ShoppingCartItem>> cart;
    // applied discounts registers into the List
    private List<Discount> discounts;

    public ShoppingCart(){
        this.cart = new HashMap<String, List<ShoppingCartItem>>();
        this.discounts = new ArrayList<>();
    }

    /**
     * adds a new product into the shopping cart
     * @param product   quality of data
     * @param quantity  quantity of data
     */
    public void addItem(Product product, int quantity){
        Optional.ofNullable(product)
                .ifPresent(currentProduct->currentProduct.add(this,quantity));
    }

    /**
     * try to apply discount into the cart
     * @param discount either be Coupon or Campaign
     */
    public void applyDiscount(Discount discount){
        Optional.ofNullable(discount)
                .ifPresent(currentDiscount->{
                    // coupons have total amount threshold
                    if (currentDiscount instanceof Coupon && getTotalPrice() > currentDiscount.getItemThreshold())
                        this.discounts.add(currentDiscount);
                    // campaings have item-based threshold
                    else if (size() > currentDiscount.getItemThreshold())
                        this.discounts.add(currentDiscount);
                });
    }

    /**
     * @return total size of product in the cart
     */
    public int size(){
        return cart
                .entrySet()
                .stream()
                .map(shoppingCartMap->shoppingCartMap.getValue()
                        .stream()
                        .map(ShoppingCartItem::getQuantity)
                        .reduce(0,Integer::sum))
                .reduce(0,Integer::sum);
    }

    /**
     * @param category
     * @return calculates total price by each category added into the shopping cart
     */
    public Double findPriceByCategory(Category category){
        return Optional.ofNullable(category.getTitle())
                .map(title->{
                    double price=0;
                    List<ShoppingCartItem> shoppingCartItems = cart.get(title);

                    for (int i = 0; i < shoppingCartItems.size(); i++) {
                        if (shoppingCartItems.get(i)!=null)
                            price += shoppingCartItems.get(i).getQuantity() * shoppingCartItems.get(i).getProduct().getPrice();
                    }
                    return price;
                }).get();
    }

    /**
     * @return discount amount that made by eligible campaigns
     */
    public Double getCampaignDiscount() {
        return this.discounts
                .stream()
                .filter(discount -> discount instanceof Campaign)
                .map(discount -> ((Campaign) discount).apply(findPriceByCategory(((Campaign) discount).getCategory())))
                .reduce((double)0,Double::sum);
    }

    /**
     *
     * @return discount amount that made by eligible coupons
     */
    public Double getCouponDiscount() {
        return this.discounts
                .stream()
                .filter(discount->discount instanceof Coupon)
                .map(discount -> ((Coupon) discount).apply(getTotalPrice() - getCampaignDiscount()))
                .reduce((double)0,Double::sum);
    }

    /**
     * @return total amount that have not been affected by the discounts.
     */
    public Double getTotalPrice() {
        return cart
                .entrySet()
                .stream()
                .map(i->
                        i.getValue()
                                .stream()
                                .map(j->j.getQuantity()*j.getProduct().getPrice())
                                .reduce((double)0,Double::sum))
                .findFirst()
                .get();
    }

    /**
     * @return total amount that have been affected by the discounts
     */
    public Double getTotalAmountAfterDiscounts() {
        return getTotalPrice() - getCampaignDiscount() - getCouponDiscount();
    }

    /**
     * use to optimize delivery cost.
     * @return
     */
    public Double getDeliveryCost(){
        CostCalculator costCalculator = new DeliveryCostCalculator(1,1);
        return costCalculator.calculate(this);
    }

    /**
     * @return the shopping cart
     */
    public Map<String, List<ShoppingCartItem>> getCart() {
        return cart;
    }

    /**
     *
     */
    public void print(){
        this.cart.forEach((key, value) -> {
            logger.info(">>Category Name: {}", key);
            value.forEach(shoppingCartItem -> {
                logger.info("\t >>Product Name: {}", shoppingCartItem.getProduct().getTitle());
                logger.info("\t >>Quantity: {}", shoppingCartItem.getQuantity());
                logger.info("\t >>Unit Price: {}", shoppingCartItem.getProduct().getPrice());
            });
        });
        logger.info(">>Total Price: {}",getTotalPrice());
        logger.info(">>Total Discount: {}",(getCampaignDiscount()+getCouponDiscount()));
    }
    /**
     *
     * highlight me and see the pikachu ϞϞ(๑⚈ ․̫ ⚈๑)∩
     */

}
