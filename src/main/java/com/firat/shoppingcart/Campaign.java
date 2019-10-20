package com.firat.shoppingcart;

/**
 * Campaign is the special Discount class.
 */
public class Campaign extends Discount {
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
    public double apply(double totalAmount){
        return this.campaignCalculator.calculate(this,totalAmount);
    }

}
