package com.example.project5_sm;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * This class represents a Veggie Pizza which extends off Pizza.
 * It includes specific methods and constants related to Veggie Pizza.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */

public class Veggie extends Pizza{
    /**
     * Constructs a Veggie pizza with predefined toppings, sauce, and size.
     */
    public Veggie(){
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.MUSHROOM));
        toppings.add(String.valueOf(Topping.SPINACH));
        toppings.add(String.valueOf(Topping.BLACK_OLIVE));
        toppings.add(String.valueOf(Topping.GREEN_PEPPER));
        toppings.add(String.valueOf(Topping.ONION));




        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }

    /**
     * Calculates the price for the Veggie pizza.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double price() {
        double total = 11.99;

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
        return "Veggie";
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
