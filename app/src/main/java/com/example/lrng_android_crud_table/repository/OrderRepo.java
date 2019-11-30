package com.example.lrng_android_crud_table.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lrng_android_crud_table.model.Order;

public class OrderRepo extends DatabaseHandler {

    public OrderRepo(Context context) {
        super(context);
    }

    public boolean create(Order order) {

        ContentValues values = new ContentValues();

        values.put("client", order.getClient());
        values.put("date", order.getDate());
        values.put("cost", order.getCost());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("orders", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM orders";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }


}
