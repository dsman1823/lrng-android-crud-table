package com.example.lrng_android_crud_table;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lrng_android_crud_table.listener.OrderCreateOnClickListener;
import com.example.lrng_android_crud_table.model.Order;
import com.example.lrng_android_crud_table.repository.OrderRepo;

import java.util.List;

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
        updateRecordList();
    }

    public void countRecords() {
        TextView textViewRecordCount = findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(new OrderRepo(this).count() + " records found.");
    }

    public void updateRecordList() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<Order> students = new OrderRepo(this).read();

        if (students.size() > 0) {

            for (Order obj : students) {

                String textViewContents = String.format("%s\t%d\t%s", obj.getClient(),
                        obj.getCost(),
                        obj.getDate());

                TextView orderItemView = new TextView(this);
                orderItemView.setPadding(0, 10, 0, 10);
                orderItemView.setText(textViewContents);
                orderItemView.setTag(Integer.toString(obj.getId()));
                linearLayoutRecords.addView(orderItemView);
            }

        } else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
