package com.example.myapp.onlinemedicine;

import com.example.myapp.onlinemedicine.model.Order;
import com.google.firebase.database.ThrowOnExtraProperties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    Order order;

    @Before
    public void setUp() {
        order = new Order();
        order = new Order("Order Name", "0123456789", "32/A, Dhanmondi", "Dhaka", "1219", "18/05/2021", "1:14 PM", "500");
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Order Name", order.getName());
    }

    @Test
    public void testGetPhone() {
        Assert.assertEquals("0123456789", order.getPhone());
    }

    @Test
    public void testGetAddress() {
        Assert.assertEquals("32/A, Dhanmondi", order.getAddress());
    }

    @Test
    public void testGetCity() {
        Assert.assertEquals("Dhaka", order.getCity());
    }

    @Test
    public void testGetState() {
        Assert.assertEquals("1219", order.getState());
    }

    @Test
    public void testGetDate() {
        Assert.assertEquals("18/05/2021", order.getDate());
    }

    @Test
    public void testGetTime() {
        Assert.assertEquals("1:14 PM", order.getTime());
    }

    @Test
    public void testGetTotalPrice() {
        Assert.assertEquals("500", order.getTotalPrice());
    }
}
