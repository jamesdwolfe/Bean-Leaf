package com.example.beanleaf;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    requestObj r = new requestObj(1, "username", "name", "address", "12:00", new byte[5]);

    @Test
    public void getId() {
        assertEquals(r.getId(), 1);
    }

    @Test
    public void setId() {
        r.setId(2);
        assertEquals(r.getId(), 2);
    }

    @Test
    public void getName() {
        assertEquals(r.getName(), "name");
    }

    @Test
    public void setName() {
        r.setName("NAME");
        assertEquals(r.getName(), "NAME");
    }

    @Test
    public void getAddress() {
        assertEquals(r.getAddress(), "address");
    }

    @Test
    public void setAddress() {
        r.setAddress("ADDRESS");
        assertEquals(r.getAddress(), "ADDRESS");
    }

    @Test
    public void getTimestamp() {
        assertEquals(r.getTimestamp(), "12:00");
    }

    @Test
    public void setTimestamp() {
        r.setTimestamp("2:00");
        assertEquals(r.getTimestamp(), "2:00");
    }

    @Test
    public void getImage() {
        assertArrayEquals(r.getImage(), new byte[5]);
    }

    @Test
    public void setImage() {
        r.setImage(new byte[20]);
        assertArrayEquals(r.getImage(), new byte[20]);
    }

    @Test
    public void getUsername() {
        assertEquals(r.getUsername(), "username");
    }

    @Test
    public void setUsername() {
        r.setUsername("USERNAME");
        assertEquals(r.getUsername(), "USERNAME");
    }
}
