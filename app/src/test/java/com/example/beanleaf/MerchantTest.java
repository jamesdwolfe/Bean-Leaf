package com.example.beanleaf;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MerchantTest {
    Merchant m = new Merchant(1, "Alice", "Female", "alice@gmail.com",
            "12345", "1234567890", 80.190, -123.48,
            200, new ArrayList<Order>(), new ArrayList<Restaurant>());

    @Test
    public void getRestaurants() {
        assertEquals(m.getRestaurants().size(), 0);
    }

    @Test
    public void setRestaurants() {
        Restaurant r = new Restaurant(1, "Starbuzz", new ArrayList<Item>(), new ArrayList<Order>(), 1, "12:00", "9:00", 15.93, -117.29);
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r);
        m.setRestaurants(restaurants);
        assertEquals(m.getRestaurants().size(), 1);
        assertEquals(m.getRestaurants().get(0).getName(), "Starbuzz");
    }
}
