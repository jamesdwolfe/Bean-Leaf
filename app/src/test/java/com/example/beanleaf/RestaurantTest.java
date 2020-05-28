package com.example.beanleaf;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class RestaurantTest {
    Restaurant r = new Restaurant(1, "Starbuzz", new ArrayList<Item>(), new ArrayList<Order>(),
            1, "12:00", "9:00", 18.89001, -112.98927);

    @Test
    public void getID() {
        assertEquals(r.getID(), 1);
    }

    @Test
    public void setID() {
        r.setID(2);
        assertEquals(r.getID(), 2);
    }

    @Test
    public void getName() {
        assertEquals(r.getName(), "Starbuzz");
    }

    @Test
    public void setName() {
        r.setName("Joe's Coffee");
        assertEquals(r.getName(), "Joe's Coffee");
    }

    @Test
    public void getMenu() {
        assertEquals(r.getMenu().size(), 0);
    }

    @Test
    public void setMenu() {
        Item i = new Item(1, "Black Coffee", 2, "Coffee",
                (float) 2.49, 200, "A cup of black coffee", new ArrayList<String>());
        ArrayList<Item> menu = new ArrayList<>();
        menu.add(i);
        r.setMenu(menu);
        assertEquals(r.getMenu().size(), 1);
        assertEquals(r.getMenu().get(0).getName(), "Black Coffee");
    }

    @Test
    public void getCurrentOrders() {
        assertEquals(r.getCurrentOrders().size(), 0);
    }

    @Test
    public void setCurrentOrders() {
        Order o = new Order(1, 2, 3, (float) 7.89, new ArrayList<Item>(), "4:37");
        ArrayList<Order> currentOrders = new ArrayList<>();
        currentOrders.add(o);
        r.setCurrentOrders(currentOrders);
        assertEquals(r.getCurrentOrders().size(), 1);
        assertEquals(r.getCurrentOrders().get(0).getRestaurant(), 2);
    }

    @Test
    public void getOwner() {
        assertEquals(r.getOwner(), 1);
    }

    @Test
    public void setOwner() {
        r.setOwner(5);
        assertEquals(r.getOwner(), 5);
    }

    @Test
    public void getOpeningTime() {
        assertEquals(r.getOpeningTime(), "12:00");
    }

    @Test
    public void setOpeningTime() {
        r.setOpeningTime("11:00");
        assertEquals(r.getOpeningTime(), "11:00");
    }

    @Test
    public void getClosingTime() {
        assertEquals(r.getClosingTime(), "9:00");
    }

    @Test
    public void setClosingTime() {
        r.setClosingTime("10:00");
        assertEquals(r.getClosingTime(), "10:00");
    }

    @Test
    public void getLocationLat() {
        assertEquals(r.getLocationLat(), 18.89001, 0.000001);
    }

    @Test
    public void setLocationLat() {
        r.setLocationLat(-89.89001);
        assertEquals(r.getLocationLat(), -89.89001, 0.000001);
    }

    @Test
    public void getLocationLng() {
        assertEquals(r.getLocationLng(), -112.98927, 0.000001);
    }

    @Test
    public void setLocationLng() {
        r.setLocationLng(-89.89001);
        assertEquals(r.getLocationLng(), -89.89001, 0.000001);
    }
}
