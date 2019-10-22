package com.firat.shoppingcart.cost;

import com.firat.shoppingcart.cart.ShoppingCart;
import com.firat.shoppingcart.cart.ShoppingCartItem;
import com.firat.shoppingcart.cost.exception.ShoppingCartNullException;

import java.util.Map;
import java.util.Optional;
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
        return Optional.ofNullable(cart)
                .map(shoppingCart -> {
                    //numberOfDeliveries is calculated by the number of distinct categories in the cart.
                    int numberOfDeliveries = shoppingCart.getCart()
                            .entrySet()
                            .stream()
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList())
                            .size();

                    //numberOfProducts is the total different products in the cart.
                    long numberOfProducts = shoppingCart.getCart()
                            .entrySet()
                            .stream()
                            .map(mapShoppingCart -> (long) mapShoppingCart.getValue().size())
                            .reduce((long)0,Long::sum);

                    return (costPerDelivery*numberOfDeliveries) + (costPerProduct*numberOfProducts) + fixedCost;
                }).orElseThrow(ShoppingCartNullException::new);
    }
}
