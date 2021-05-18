package com.example.myapp.onlinemedicine;

import com.example.myapp.onlinemedicine.model.Cart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartTest {
    Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
        cart = new Cart("101", "Medicine Name", "10", "5", "2");
    }

    @Test
    public void testGetPid() {
        Assert.assertEquals("101", cart.getPid());
    }

    @Test
    public void testGetPname() {
        Assert.assertEquals("Medicine Name", cart.getPname());
    }

    @Test
    public void testGetPrice() {
        Assert.assertEquals("10", cart.getPrice());
    }

    @Test
    public void testGetDiscount() {
        Assert.assertEquals("5", cart.getDiscount());
    }

    @Test
    public void testGetQuantity() {
        Assert.assertEquals("2", cart.getQuantity());
    }
}
