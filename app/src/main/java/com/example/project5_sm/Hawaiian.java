package com.example.project5_sm;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * This class represents a Hawaiian Pizza which extends off Pizza.
 * It includes specific methods and constants related to Hawaiian Pizza.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class Hawaiian extends Pizza {
    /**
     * Constructs a Hawaiian pizza with predefined toppings, sauce, and size.
     */
    public Hawaiian() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.PINEAPPLE));
        toppings.add(String.valueOf(Topping.BACON));
        toppings.add(String.valueOf(Topping.HAM));


        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }

    /**
     * Calculates the price for the Hawaiian pizza.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double price() {
        double total = 13.99;

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
        return "Hawaiian";
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
