package com.example.project5_sm;

import androidx.annotation.NonNull;

import java.util.ArrayList;
/**
 * This class represents a BaconCheeseBurger Pizza which extends off Pizza.
 * It includes specific methods and constants related to Breakfast Pizza.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */

public class BaconCheeseBurger extends Pizza{
    /**
     * Constructs a BaconCheeseBurger pizza with predefined toppings, sauce, and size.
     */
    public BaconCheeseBurger(){
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.BEEF));
        toppings.add(String.valueOf(Topping.BACON));
        toppings.add(String.valueOf(Topping.HAM));
        toppings.add(String.valueOf(Topping.ONION));




        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }

    /**
     * Calculates the price for the BaconCheeseBurger pizza.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double price() {
        double total = 14.99;

        if (this.extraCheese) {
            total += 1.0;
        }
        if (this.extraSauce) {
            total += 1.0;
        }
        if (this.size == Size.Medium) {
            total += 2.0;
        }
        if (this.size == Size.Large) {
            total += 4.0;
        }

        return total;
    }

    /**
     * Gets the pizza_type for the toString method.
     *
     * @return The pizza_type as a String.
     */
    @Override
    public String pizzaType() {
        return "BaconCheeseBurger";
    }

    /**
     * Gets the String representation of the pizza.
     *
     * @return The String representation of the pizza.
     */
    @NonNull
    @Override
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();
    }

}


