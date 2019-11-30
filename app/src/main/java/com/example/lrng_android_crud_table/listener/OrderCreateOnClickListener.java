package com.example.lrng_android_crud_table.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lrng_android_crud_table.MainActivity;
import com.example.lrng_android_crud_table.R;
import com.example.lrng_android_crud_table.model.Order;
import com.example.lrng_android_crud_table.repository.OrderRepo;

public class OrderCreateOnClickListener implements View.OnClickListener {
    MainActivity context;

    public OrderCreateOnClickListener(MainActivity mainActivity) {
        this.context = mainActivity;
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.order_input_form, null, false);
        final EditText editTextOrderClient = formElementsView.findViewById(R.id.editTextOrderClient);
        final EditText editTextOrderDate = formElementsView.findViewById(R.id.editTextOrderDate);
        final EditText editTextOrderCost = formElementsView.findViewById(R.id.editTextOrderCost);
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Student")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Order order = new Order(editTextOrderClient.getText().toString(),
                                        Integer.parseInt(editTextOrderCost.getText().toString()),
                                        editTextOrderDate.getText().toString());

                                boolean createSuccessful = new OrderRepo(context).create(order);
                                if (createSuccessful) {
                                    Toast.makeText(context, "Order information was saved.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to save Order information.", Toast.LENGTH_SHORT).show();
                                }
                                context.updateData();
                                dialog.cancel();
                            }

                        }).show();
    }
}
