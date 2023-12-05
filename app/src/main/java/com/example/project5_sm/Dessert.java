package com.example.project5_sm;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Dessert extends Pizza{
    /**
     * Constructs a Dessert pizza with predefined toppings, sauce, and size.
     */
    public Dessert() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.DARK_CHOCOLATE));
        toppings.add(String.valueOf(Topping.MILK_CHOCOLATE));
        toppings.add(String.valueOf(Topping.MARSHMELLO));


        this.toppings = toppings;
        this.sauce = Sauce.NUTELLA;
        this.size = Size.Small;
    }

    /**
     * Calculates the price for the Dessert pizza.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double price() {
        double total = 18.99;

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
        return "Dessert";
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


