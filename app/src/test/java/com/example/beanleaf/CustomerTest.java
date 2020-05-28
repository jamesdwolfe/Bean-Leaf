package com.example.beanleaf;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerTest {

    @Before
    public void setUp() throws Exception {

    }

    ArrayList<Order> history = new ArrayList<Order>();
    Customer sampleCustomer = new Customer();

    @Test
    public void getCaffeineIntakeToday() {

        sampleCustomer.setCaffeineIntakeToday(0);
        assertEquals(sampleCustomer.getCaffeineIntakeToday(), 0);
    }

    @Test
    public void setCaffeineIntakeToday() {
        sampleCustomer.setCaffeineIntakeToday(0);
        assertEquals(sampleCustomer.getCaffeineIntakeToday(), 0);
    }

    @Test
    public void getOrderHistory() {
        sampleCustomer.setOrderHistory(history);
        assertEquals(sampleCustomer.getOrderHistory(), history);
    }

    @Test
    public void setOrderHistory() {
        sampleCustomer.setOrderHistory(history);
        assertEquals(sampleCustomer.getOrderHistory(), history);
    }

}