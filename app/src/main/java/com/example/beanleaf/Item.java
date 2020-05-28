package com.example.beanleaf;

import java.util.ArrayList;

public class Item implements java.io.Serializable {
    private int ID;
    private String Name;
    private int Restaurant;
    private String Category;
    private float Price;
    private int CaffeineIntake;
    private String Description;
    private ArrayList<String> Ingredients;

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

    public int getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(int restaurant) {
        Restaurant = restaurant;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getCaffeineIntake() {
        return CaffeineIntake;
    }

    public void setCaffeineIntake(int caffeineIntake) {
        CaffeineIntake = caffeineIntake;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        Ingredients = ingredients;
    }

    public Item(int ID, String Name, int Restaurant, String Cateogry, float Price, int CaffeineIntake, String Description, ArrayList<String> Ingredients){
        this.ID=ID;
        this.Name=Name;
        this.Restaurant=Restaurant;
        this.Category=Cateogry;
        this.Price=Price;
        this.CaffeineIntake=CaffeineIntake;
        this.Description=Description;
        this.Ingredients=Ingredients;
    }

}
