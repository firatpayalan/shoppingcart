package com.firat.shoppingcart;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * implementation allows the dynamic cargo pricing based on the formula
 */
public class DeliveryCostCalculator extends CostCalculator{
    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = 2.99;
    }

    @Override
    public Double calculate(ShoppingCart cart){
        //numberOfDeliveries is calculated by the number of distinct categories in the cart.
        int numberOfDeliveries = cart.getCart()
                .entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .size();

        //numberOfProducts is the total products in the cart.
        int numberOfProducts = cart.getCart()
                .entrySet()
                .stream()
                .map(mapShoppingCart->mapShoppingCart.getValue()
                        .stream()
                        .map(ShoppingCartItem::getQuantity)
                        .reduce(0,Integer::sum))
                .reduce(0,Integer::sum);
        return (costPerDelivery*numberOfDeliveries) + (costPerProduct*numberOfProducts) + fixedCost;
    }
}
