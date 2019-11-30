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

public class OrderItemLongClickListener implements View.OnLongClickListener {
    MainActivity context;
    Integer orderId;

    public OrderItemLongClickListener(MainActivity context) {
        this.context = context;
    }

    @Override
    public boolean onLongClick(View v) {
        orderId = Integer.parseInt(v.getTag().toString());
        final CharSequence[] items = {"Edit", "Delete"};

        new AlertDialog.Builder(context).setTitle("Student Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(orderId);
                        }
                        context.updateData();
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

    private void editRecord(final Integer orderId) {
        final OrderRepo orderRepo = new OrderRepo(context);
        Order order = orderRepo.readSingleRecord(orderId);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.order_input_form, null, false);
        final EditText editTextOrderClient = formElementsView.findViewById(R.id.editTextOrderClient);
        final EditText editTestOrderCost = formElementsView.findViewById(R.id.editTextOrderCost);
        final EditText editTestOrderDate = formElementsView.findViewById(R.id.editTextOrderDate);
        editTestOrderCost.setText(order.getCost().toString());
        editTestOrderDate.setText(order.getDate());
        editTextOrderClient.setText(order.getClient());
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Order orderToUpdate = new Order(
                                        orderId,
                                        editTextOrderClient.getText().toString(),
                                        Integer.parseInt(editTestOrderCost.getText().toString()),
                                        editTestOrderDate.getText().toString()
                                );
                                boolean updatedSuccessfully = orderRepo.update(orderToUpdate);

                                if (updatedSuccessfully) {
                                    Toast.makeText(context, "Order record was updated.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                }
                                context.updateData();
                                dialog.cancel();
                            }

                        }).show();
    }
}
