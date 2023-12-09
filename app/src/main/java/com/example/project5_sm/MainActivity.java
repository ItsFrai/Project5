package com.example.project5_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MainActivity represents the main screen of the application.
 * It serves as the entry point for various actions and navigation within the app.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class MainActivity extends AppCompatActivity implements Serializable {
    private static final StoreOrders orders = new StoreOrders();
    private static final ArrayList<Integer> placed_orders = new ArrayList<>();

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /**
     * Opens the Build Your Own Pizza activity when the corresponding button is clicked.
     * @param view The view that triggered the method.
     */
    public void buildyourrownclickbutton(View view) {
        Intent intent = new Intent(this, BuildYourOwnActivity.class);
        intent.putExtra("mainMenuController", this);
        startActivity(intent);
    }


    /**
     * Opens the Specialty Pizza activity when the corresponding button is clicked.
     * @param view The view that triggered the method.
     */
    public void specialitypizzabutton(View view) {
        Intent intent  = new Intent(this, SpecialtyPizza.class);
        intent.putExtra("mainMenuController", this);
        startActivity(intent);
    }

    /**
     * Opens the Current Order activity when the corresponding button is clicked.
     * @param view The view that triggered the method.
     */
    public void currentorderbutton(View view) {
        Intent intent  = new Intent(this, CurrentOrderActivity.class);
        intent.putExtra("mainMenuController", this);
        startActivity(intent);
    }

    /**
     * Opens the Store Orders Control activity when the corresponding button is clicked.
     * @param view The view that triggered the method.
     */
    public void storeorderbutton(View view) {
        Intent intent  = new Intent(this, StoreOrdersControl.class);
        intent.putExtra("mainMenuController", this);
        startActivity(intent);
    }


    /**
     * Gets the list of placed orders.
     * @return An ArrayList of placed order numbers.
     */
    public ArrayList<Integer> get_placed() {
        return placed_orders;
    }


    /**
     * Gets the StoreOrders instance.
     * @return The StoreOrders instance.
     */
    public StoreOrders getStores() {
        return orders;
    }


    /**
     * Gets the instance of the MainActivity.
     * @return The instance of MainActivity.
     */
    public MainActivity get_control() {
        return this;
    }

}