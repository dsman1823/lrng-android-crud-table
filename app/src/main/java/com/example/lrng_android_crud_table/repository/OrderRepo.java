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


    public Order readSingleRecord(Integer orderId) {

        Order order = null;

        String sql = "SELECT * FROM orders WHERE id = " + orderId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            Integer cost = cursor.getInt(cursor.getColumnIndex("cost"));
            String client = cursor.getString(cursor.getColumnIndex("client"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            order = new Order(id, client, cost, date);
        }

        cursor.close();
        db.close();

        return order;
    }

    public boolean update(Order orderToUpdate) {

        ContentValues values = new ContentValues();

        values.put("cost", orderToUpdate.getCost());
        values.put("client", orderToUpdate.getClient());
        values.put("date", orderToUpdate.getDate());

        String where = "id = ?";

        String[] whereArgs = {Integer.toString(orderToUpdate.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("orders", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("orders", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;
    }
}
