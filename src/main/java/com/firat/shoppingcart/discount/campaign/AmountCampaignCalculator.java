package com.firat.shoppingcart.discount.campaign;

/**
 * makes an amount based campaign calculation
 */
public class AmountCampaignCalculator extends CampaignCalculator {

    @Override
    public double calculate(Campaign campaign, double totalAmount) {
        return campaign.getDiscountValue();
    }
}
