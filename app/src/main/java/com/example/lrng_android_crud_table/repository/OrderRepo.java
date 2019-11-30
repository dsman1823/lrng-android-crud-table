package com.example.lrng_android_crud_table.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lrng_android_crud_table.model.Order;

import java.util.ArrayList;
import java.util.List;

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

    public List<Order> read() {

        List<Order> recordsList = new ArrayList<>();

        String sql = "SELECT * FROM orders ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                int cost = cursor.getInt(cursor.getColumnIndex("cost"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String client = cursor.getString(cursor.getColumnIndex("client"));
                recordsList.add(new Order(id, client, cost, date));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }


}
