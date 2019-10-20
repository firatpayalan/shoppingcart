package com.firat.shoppingcart.discount.campaign;

/**
 * makes a rate based campaign calculation
 */
public class RateCampaignCalculator extends CampaignCalculator {

    @Override
    public double calculate(Campaign campaign, double totalAmount) {
        return (totalAmount*campaign.getDiscountValue()) / 100;
    }
}
