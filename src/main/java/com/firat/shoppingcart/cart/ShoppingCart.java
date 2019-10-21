package com.firat.shoppingcart.cart;

import com.firat.shoppingcart.cart.exception.CategoryNullException;
import com.firat.shoppingcart.cart.exception.ProductNullException;
import com.firat.shoppingcart.cost.CostCalculator;
import com.firat.shoppingcart.cost.DeliveryCostCalculator;
import com.firat.shoppingcart.discount.Discount;
import com.firat.shoppingcart.discount.campaign.Campaign;
import com.firat.shoppingcart.discount.coupon.Coupon;
import com.firat.shoppingcart.discount.exception.DiscountNullException;

import java.util.*;

import static com.firat.shoppingcart.cart.ShoppingCartConstants.FAIL_ADD_PRODUCT_NULL;

public class ShoppingCart {
    // CategoryName as key, ListOfProducts as values. contains all product into the Shopping Cart
    private Map<String, List<ShoppingCartItem>> cart;
    // applied discounts registers into the List
    private List<Discount> discounts;

    public ShoppingCart(){
        this.cart = new HashMap<String, List<ShoppingCartItem>>();
        this.discounts = new ArrayList<>();
    }

    private static Double calculateAmountOfCategory(List<ShoppingCartItem> item) {
        double price = 0;
        for (ShoppingCartItem shoppingCartItem : item) {
            if (shoppingCartItem != null) {
                price += shoppingCartItem.getQuantity() *
                        shoppingCartItem.getProduct().getPrice();
            }
        }
        return price;
    }

    private static Integer collectAllProducts(Map.Entry<String, List<ShoppingCartItem>> shoppingCartMap) {
        return shoppingCartMap.getValue()
                .stream()
                .map(ShoppingCartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    /**
     * adds a new product into the shopping cart
     * @param product   quality of data
     * @param quantity  quantity of data
     */
    public void addItem(Product product, int quantity){
        Optional.ofNullable(product)
                .orElseThrow(() -> new ProductNullException(FAIL_ADD_PRODUCT_NULL))
                .add(this, quantity);
    }

    /**
     * try to apply discount into the cart
     * @param discount either be Coupon or Campaign
     */
    public void applyDiscount(Discount discount){
        Optional.ofNullable(discount)
                .orElseThrow(DiscountNullException::new)
                .apply(this);
    }

    /**
     * @return total size of product in the cart
     */
    public int size(){
        return cart
                .entrySet()
                .stream()
                .map(ShoppingCart::collectAllProducts)
                .reduce(0,Integer::sum);
    }

    /**
     * @param category
     * @return calculates total price by each category added into the shopping cart
     */
    public Double findPriceByCategory(Category category){
        // parent category may have a discount.
        // discount should be applied children under the parent campaign
        Category existedCategory = Optional.ofNullable(category)
                .orElseThrow(CategoryNullException::new);

        List<String> childCategoryNames
                = existedCategory.allChildren(new ArrayList<>(), existedCategory);

        return childCategoryNames
                .stream()
                .map(this::getPriceOfCategory)
                .reduce((double)0,Double::sum);

    }

    /**
     * @return discount amount that made by eligible campaigns
     */
    public Double getCampaignDiscount() {
        return this.discounts
                .stream()
                .filter(discount -> discount instanceof Campaign)
                .map(discount -> ((Campaign) discount).discount(findPriceByCategory(((Campaign) discount).getCategory())))
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
                .map(discount -> ((Coupon) discount).discount(getTotalPrice() - getCampaignDiscount()))
                .reduce((double)0,Double::sum);
    }



    /**
     * @return total amount that have not been affected by the discounts.
     */
    public Double getTotalPrice() {
        return cart
                .entrySet()
                .stream()
                .map(i->ShoppingCart.calculateAmountOfCategory(i.getValue()))
                .findFirst()
                .orElse((double)0);
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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    /**
     *
     */
    public void print(){
        this.cart.forEach((key, value) -> {
            System.out.printf(">>Category Name: %s \n",key);
            value.forEach(shoppingCartItem -> {
                System.out.printf("\t >>Product Name: %s \n",shoppingCartItem.getProduct().getTitle());
                System.out.printf("\t >>Quantity: %s \n",shoppingCartItem.getQuantity());
                System.out.printf("\t >>Unit Price: %s \n",shoppingCartItem.getProduct().getPrice());
            });
        });
        System.out.printf(">>Total Price: %s \n",getTotalPrice());
        System.out.printf(">>Total Discount: %s \n",(getCampaignDiscount()+getCouponDiscount()));
        System.out.printf(">>Total Amount: %s \n",getTotalAmountAfterDiscounts());
        System.out.printf(">>Delivery Amount: %s \n",getDeliveryCost());
    }

    private Double getPriceOfCategory(String categoryName) {
        Optional<List<ShoppingCartItem>> shoppingCartItems = Optional.ofNullable(cart.get(categoryName));
        return shoppingCartItems
                .map(ShoppingCart::calculateAmountOfCategory)
                .orElse((double) 0);
    }
    /**
     *
     * highlight me and see the pikachu ϞϞ(๑⚈ ․̫ ⚈๑)∩
     */

}
