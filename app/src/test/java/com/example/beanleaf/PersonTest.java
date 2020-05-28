package com.example.beanleaf;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    Person p = new Person("Alice", "Female", "alice@gmail.com", "12345",
            1, "1234567890", 18.89001, -112.98927);

    @Test
    public void getName() {
        assertEquals(p.getName(), "Alice");
    }

    @Test
    public void setName() {
        p.setName("Bob");
        assertEquals(p.getName(), "Bob");
    }

    @Test
    public void getGender() {
        assertEquals(p.getGender(), "Female");
    }

    @Test
    public void setGender() {
        p.setGender("Male");
        assertEquals(p.getGender(), "Male");
    }

    @Test
    public void getEmail() {
        assertEquals(p.getEmail(), "alice@gmail.com");
    }

    @Test
    public void setEmail() {
        p.setEmail("bob@gmail.com");
        assertEquals(p.getEmail(), "bob@gmail.com");
    }

    @Test
    public void getPassword() {
        assertEquals(p.getPassword(), "12345");
    }

    @Test
    public void setPassword() {
        p.setPassword("54321");
        assertEquals(p.getPassword(), "54321");
    }

    @Test
    public void getID() {
        assertEquals(p.getID(), 1);
    }

    @Test
    public void setID() {
        p.setID(2);
        assertEquals(p.getID(), 2);
    }

    @Test
    public void getPhoneNUmber() {
        assertEquals(p.getPhoneNunber(), "1234567890");
    }

    @Test
    public void setPhoneNumber() {
        p.setPhoneNunber("0987654321");
        assertEquals(p.getPhoneNunber(), "0987654321");
    }

    @Test
    public void getLocationLat() {
        assertEquals(p.getLocationLat(), 18.89001, 0.000001);
    }

    @Test
    public void setLocationLat() {
        p.setLocationLat(-89.89001);
        assertEquals(p.getLocationLat(), -89.89001, 0.000001);
    }

    @Test
    public void getLocationLng() {
        assertEquals(p.getLocationLng(), -112.98927, 0.000001);
    }

    @Test
    public void setLocationLng() {
        p.setLocationLng(-89.89001);
        assertEquals(p.getLocationLng(), -89.89001, 0.000001);
    }
}
