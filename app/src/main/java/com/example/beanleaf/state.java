package com.example.beanleaf;

import android.app.Application;

public class state extends Application {

    public String username;
    public boolean isLoggedIn;
    public boolean isCustomer;
    public state(){

    }
    public state(String username, boolean isLoggedIn, boolean isCustomer) {
        this.username = username;
        this.isLoggedIn = isLoggedIn;
        this.isCustomer = isCustomer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }
}
