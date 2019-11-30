package com.example.lrng_android_crud_table.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.lrng_android_crud_table.MainActivity;
import com.example.lrng_android_crud_table.R;

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
        final EditText editTextOrderCost = formElementsView.findViewById(R.id.editTestOrderCost);
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Student")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }

                        }).show();
    }
}
