package com.example.beanleaf;

import android.util.Log;

import java.util.ArrayList;

public class Merchant extends Customer implements java.io.Serializable {
    private ArrayList<Restaurant> Restaurants;

    public ArrayList<Restaurant> getRestaurants() {
        return Restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        Restaurants = restaurants;
    }

    public Merchant(){

    }

    public Merchant(int ID, String name, String gender, String email, String password, String phoneNunber, double locationLat, double locationLng, int caffeineIntakeToday, ArrayList<Order> orderHistory, ArrayList<Restaurant> Restaurants) {

        super(ID, name, gender, email, password, phoneNunber, locationLat, locationLng, caffeineIntakeToday, orderHistory);


        this.Restaurants = Restaurants;
    }


}
