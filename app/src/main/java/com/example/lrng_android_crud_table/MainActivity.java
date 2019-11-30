package com.example.lrng_android_crud_table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.lrng_android_crud_table.listener.OrderCreateOnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createOrderButton = findViewById(R.id.buttonCreateOrder);
        createOrderButton.setOnClickListener(new OrderCreateOnClickListener(this));
    }
}
