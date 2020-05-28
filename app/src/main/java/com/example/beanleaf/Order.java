package com.example.beanleaf;

import java.util.ArrayList;

public class Order implements java.io.Serializable{
    private int ID;
    private int Restaurant;
    private int Customer;
    private float TotalPrice;
    private ArrayList<Item> Items;
    private String OrderTime;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(int restaurant) {
        Restaurant = restaurant;
    }

    public int getCustomer() {
        return Customer;
    }

    public void setCustomer(int customer) {
        Customer = customer;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        TotalPrice = totalPrice;
    }

    public ArrayList<Item> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Item> items) {
        Items = items;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public Order( int ID, int Restaurant, int Customer, float TotalPrice, ArrayList<Item> Items, String OrderTime){
        this.ID=ID;
        this.Restaurant=Restaurant;
        this.Customer=Customer;
        this.TotalPrice=TotalPrice;
        this.Items=Items;
        this.OrderTime=OrderTime;
    }

}
