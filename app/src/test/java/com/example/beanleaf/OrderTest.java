package com.example.beanleaf;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderTest {
    Order o = new Order(1, 2, 3, (float) 7.89, new ArrayList<Item>(), "4:37");

    @Test
    public void getID() {
        assertEquals(o.getID(), 1);
    }

    @Test
    public void setID() {
        o.setID(2);
        assertEquals(o.getID(), 2);
    }

    @Test
    public void getRestaurant() {
        assertEquals(o.getRestaurant(), 2);
    }

    @Test
    public void setRestaurant() {
        o.setRestaurant(3);
        assertEquals(o.getRestaurant(), 3);
    }

    @Test
    public void getCustomer() {
        assertEquals(o.getCustomer(), 3);
    }

    @Test
    public void setCustomer() {
        o.setCustomer(4);
        assertEquals(o.getCustomer(), 4);
    }

    @Test
    public void getTotalPrice() {
        assertEquals(o.getTotalPrice(), 7.89, 0.001);
    }

    @Test
    public void setTotalPrice() {
        o.setTotalPrice((float) 6.99);
        assertEquals(o.getTotalPrice(), 6.99, 0.001);
    }

    @Test
    public void getItems() {
        assertEquals(o.getItems().size(), 0);
    }

    @Test
    public void setItems() {
        Item i = new Item(1, "Black Coffee", 2, "Coffee",
                (float) 2.49, 200, "A cup of black coffee", new ArrayList<String>());
        ArrayList<Item> items = new ArrayList<>();
        items.add(i);
        o.setItems(items);
        assertEquals(o.getItems().size(), 1);
        assertEquals(o.getItems().get(0).getName(), "Black Coffee");
    }

    @Test
    public void getOrderTime() {
        assertEquals(o.getOrderTime(), "4:37");
    }

    @Test
    public void setOrderTime() {
        o.setOrderTime("2:19");
        assertEquals(o.getOrderTime(), "2:19");
    }
}
