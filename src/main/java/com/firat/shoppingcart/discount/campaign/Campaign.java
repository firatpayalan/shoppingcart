package com.firat.shoppingcart.discount.campaign;

import com.firat.shoppingcart.cart.Category;
import com.firat.shoppingcart.cart.ShoppingCart;
import com.firat.shoppingcart.discount.Discount;
import com.firat.shoppingcart.discount.DiscountApplicable;
import com.firat.shoppingcart.discount.exception.CalculatorNullException;

import java.util.Optional;

/**
 * Campaign is the special Discount class.
 */
public class Campaign extends Discount implements DiscountApplicable{
    // campaigns have been applied to categories.
    private Category category;
    // calculation strategy.
    private CampaignCalculator campaignCalculator;

    public Campaign(Category category, double discountValue, CampaignCalculator campaignCalculator, int itemThreshold) {
        super(discountValue,itemThreshold);
        this.category = category;
        this.campaignCalculator = campaignCalculator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *
     * @param totalAmount rate-based calculations needs a initial amount
     * @return calculated campaign amount
     */
    public double discount(double totalAmount){
        return Optional.ofNullable(this.campaignCalculator)
                .orElseThrow(CalculatorNullException::new)
                .calculate(this,totalAmount);
    }

    @Override
    public void apply(ShoppingCart cart) {

        if (cart.size() >= this.getItemThreshold())
            cart.getDiscounts().add(this);
    }
}
