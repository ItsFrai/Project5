package com.example.project5_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
}