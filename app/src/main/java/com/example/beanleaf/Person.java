package com.example.beanleaf;//Class object for Person

public class Person implements java.io.Serializable {
    private String name;
    private String gender;
    private String email;
    private String password;
    private int ID;
    private String phoneNunber;
    private double locationLat;
    private double locationLng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPhoneNunber() {
        return phoneNunber;
    }

    public void setPhoneNunber(String phoneNunber) {
        this.phoneNunber = phoneNunber;
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

    public Person(){

    }
    public Person(String name, String gender, String email, String password, int ID, String phoneNunber, double locationLat, double locationLng) {
        this.name = name;
        this.gender=gender;
        this.email = email;
        this.password = password;
        this.ID = ID;
        this.phoneNunber = phoneNunber;
        this.locationLat = locationLat;
        this.locationLng = locationLng;
    }

}
