package com.example.project5_sm;

import java.util.ArrayList;

/**
 * Manages a collection of store orders.
 * Handles order creation, retrieval, and export functionality.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class StoreOrders {

    private final ArrayList<Order> storeList;
    private final ArrayList<Order> storeOrdersPlaced;

    private static int orderNumber = 0;

    /**
     * Constructs a new StoreOrders instance with an initial order.
     */
    public StoreOrders() {
        this.storeList = new ArrayList<>();
        this.storeOrdersPlaced = new ArrayList<>();
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order firstOrder = new Order(0, pizzaList);
        this.storeList.add(firstOrder);
    }

    /**
     * Gets the next available order number.
     * @return The next available order number.
     */
    public int nextAvailableNumber() {
        return orderNumber;
    }

    /**
     * Finds the index of the given order in the list.
     * @param order The order to find.
     * @return The index of the order, or -1 if not found.
     */
    public int findIndexOfOrder(Order order) {
        for (int i = 0; i < this.storeList.size(); i++) {
            if (storeList.get(i).getOrderNumber() == order.getOrderNumber()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a new order to the list and increments the order number.
     * @param order The order to add.
     */
    public void addOrder(Order order) {
        int index = findIndexOfOrder(order);
        this.storeList.set(index, order);
        this.storeOrdersPlaced.add(index, order);
        orderNumber++;

        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order setOrder = new Order(orderNumber, pizzaList);
        this.storeList.add(setOrder);

    }

    /**
     * Gets a list of order numbers.
     * @return The list of order numbers.
     */
    public ArrayList<Integer> getOrderNumbers() {
        ArrayList<Integer> num = new ArrayList<>();
        for (Order storeOrder : this.storeList) {
            int temp = storeOrder.getOrderNumber();
            num.add(temp);
        }
        return num;
    }

    /**
     * Finds and returns the order with the specified order number.
     * @param orderNumber The order number to find.
     * @return The order with the specified order number.
     */
    public Order find(int orderNumber) {
        for (Order storeOrder : this.storeList) {
            if (storeOrder.getOrderNumber() == orderNumber) {
                return storeOrder;
            }
        }
        return storeList.get(0);
    }

    /**
     * Gets the total number of orders.
     * @return The total number of orders.
     */
    public int numOrders() {
        return this.storeList.size();
    }

}
