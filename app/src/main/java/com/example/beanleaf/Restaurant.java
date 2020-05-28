package com.example.beanleaf;

import java.util.ArrayList;

public class Restaurant implements java.io.Serializable {
    private int ID;
    private String Name;
    private ArrayList<Item> menu;
    private ArrayList<Order> currentOrders;
    private int Owner;
    private String openingTime;
    private String closingTime;
    private double locationLat;
    private double locationLng;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Item> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Item> menu) {
        this.menu = menu;
    }

    public ArrayList<Order> getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(ArrayList<Order> currentOrders) {
        this.currentOrders = currentOrders;
    }

    public int getOwner() {
        return Owner;
    }

    public void setOwner(int owner) {
        Owner = owner;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public double getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(double locationLng) {
        this.locationLng = locationLng;
    }

    public Restaurant(int ID, String Name, ArrayList<Item> menu, ArrayList<Order> currentOrders, int Owner, String openingTime, String closingTime, double locationLat, double locationLng){
        this.ID=ID;
        this.Name=Name;
        this.menu=menu;
        this.currentOrders=currentOrders;
        this.Owner=Owner;
        this.openingTime=openingTime;
        this.closingTime=closingTime;
        this.locationLat=locationLat;
        this.locationLng=locationLng;
    }


}
