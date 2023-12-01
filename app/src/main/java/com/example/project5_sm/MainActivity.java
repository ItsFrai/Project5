package com.example.project5_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final StoreOrders orders = new StoreOrders();
    private static final ArrayList<Integer> placed_orders = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buildyourrownclickbutton(View view) {
        Intent intent = new Intent(this, BuildYourOwnActivity.class);
        startActivity(intent);
    }
    public void specialitypizzabutton(View view) {
        Intent intent  = new Intent(this, SpecialtyPizza.class);
        startActivity(intent);
    }
    public void currentorderbutton(View view) {
        Intent intent  = new Intent(this, CurrentOrder.class);
        startActivity(intent);
    }
    public void storeorderbutton(View view) {
        Intent intent  = new Intent(this, StoreOrdersControl.class);
        startActivity(intent);
    }

    public ArrayList<Integer> get_placed() {
        return placed_orders;
    }

    public StoreOrders getStores() {
        return orders;
    }

    public MainActivity get_control() {
        return this;
    }

}