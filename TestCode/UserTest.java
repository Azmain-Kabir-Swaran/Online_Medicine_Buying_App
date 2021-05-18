package com.example.myapp.onlinemedicine;

import com.example.myapp.onlinemedicine.model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    User user;

    @Before
    public void setUp() {
        user = new User();
        user = new User("Azmain", "0123456789", "qwerty", "Img File", "32/A, Dhanmondi");
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Azmain", user.getName());
    }

    @Test
    public void testGetPhone() {
        Assert.assertEquals("0123456789", user.getPhone());
    }

    @Test
    public void testUserPassword() {
        Assert.assertEquals("qwerty", user.getPassword());
    }

    @Test
    public void testGetUserAddress() {
        Assert.assertEquals("32/A, Dhanmondi", user.getAddress());
    }
}
