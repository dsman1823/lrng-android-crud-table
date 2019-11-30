package com.example.lrng_android_crud_table;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lrng_android_crud_table.listener.OrderCreateOnClickListener;
import com.example.lrng_android_crud_table.repository.OrderRepo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createOrderButton = findViewById(R.id.buttonCreateOrder);
        createOrderButton.setOnClickListener(new OrderCreateOnClickListener(this));
        updateData();
    }

    public void updateData() {
        countRecords();
    }

    public void countRecords() {
        TextView textViewRecordCount = findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(new OrderRepo(this).count() + " records found.");
    }
}
