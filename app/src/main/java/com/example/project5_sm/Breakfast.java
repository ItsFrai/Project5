package com.example.project5_sm;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Breakfast extends Pizza{
    /**
     * Constructs a Breakfast pizza with predefined toppings, sauce, and size.
     */
    public Breakfast(){
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.EGGS));
        toppings.add(String.valueOf(Topping.BACON));
        toppings.add(String.valueOf(Topping.HAM));
        toppings.add(String.valueOf(Topping.GREEN_PEPPER));
        toppings.add(String.valueOf(Topping.BEEF));




        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }

    /**
     * Calculates the price for the Breakfast pizza.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double price() {
        double total = 16.99;

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
        return "Breakfast";
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

