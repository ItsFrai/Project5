package com.example.project5_sm;

public class Item {
    private String pizzaName;
    private int image;
    private String toppings;
    private String sauce;
    //private String unitPrice; //for demo purpose, the unitPrice is of String type





    /**
     * Parameterized constructor.
     * @param pizzaName
     * @param image
     * @param toppings
     * @param sauce
     */
    public Item(String pizzaName, int image, String toppings, String sauce) {
        this.pizzaName = pizzaName;
        this.image = image;
        this.toppings = toppings;
        this.sauce = sauce;
    }

    /**
     * Getter method that returns the item name of an item.
     * @return the item name of an item.
     */
    public String getPizzaName() {
        return pizzaName;
    }

    /**
     * Getter method that returns the image of an item.
     * @return the image of an item.
     */
    public int getImage() {
        return image;
    }

    /**
     * Getter method that returns the toppings.
     * @return the toppings of the item.
     */
    public String getToppings() {
        return "Toppings: " + toppings;
    }

    /**
     * Getter method that returns the sauce.
     * @return the sauce of the item.
     */
    public String getSauce() {
        return "Sauce: " + sauce;
    }


}