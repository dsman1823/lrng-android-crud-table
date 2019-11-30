package com.example.lrng_android_crud_table.model;

public class Order {
    private Integer id;
    private String client;
    private Integer cost;
    private String date;

    public Integer getId() {
        return id;
    }

    public Order(Integer id, String client, Integer cost, String date) {
        this.id = id;
        this.client = client;
        this.cost = cost;
        this.date = date;
    }

    public Order(String client, Integer cost, String date) {
        this(null, client, cost, date);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
