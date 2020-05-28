package com.example.beanleaf;//Class Object for Customer

import android.util.Log;

import java.util.ArrayList;

public class Customer extends Person implements java.io.Serializable {
    private int caffeineIntakeToday;
    private ArrayList<Order> orderHistory;

    public Customer(){

    }
    public Customer(int ID, String name, String gender, String email, String password, String phoneNunber, double locationLat, double locationLng, int caffeineIntakeToday, ArrayList<Order> orderHistory) {
        super(name, gender, email, password, ID, phoneNunber, locationLat, locationLng);

        this.caffeineIntakeToday = caffeineIntakeToday;
        this.orderHistory = orderHistory;
    }

    public int getCaffeineIntakeToday() {
        return caffeineIntakeToday;
    }

    public void setCaffeineIntakeToday(int caffeineIntakeToday) {
        this.caffeineIntakeToday = caffeineIntakeToday;
    }

    public ArrayList<Order> getOrderHistory() {

        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public Order placeOrder(Item item ){

        return null;
    }
    public Item[] viewOrder(){
        return null;
    }

}
