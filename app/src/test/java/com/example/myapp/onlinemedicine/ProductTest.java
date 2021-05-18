package com.example.myapp.onlinemedicine;

import com.example.myapp.onlinemedicine.model.Product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {
    Product product;

    @Before
    public void setUp() {
        product = new Product();
        product = new Product("Product Name", "Description", "500", "Img File", "Category", "Product ID", "Date", "Time");
    }

    @Test
    public void testGetPname() {
        Assert.assertEquals("Product Name", product.getPname());
    }

    @Test
    public void testGetDescription() {
        Assert.assertEquals("Description", product.getDescription());
    }

    @Test
    public void testGetPrice() {
        Assert.assertEquals("500", product.getPrice());
    }

    @Test
    public void testGetImage() {
        Assert.assertEquals("Img File", product.getImage());
    }

    @Test
    public void testGetCategory() {
        Assert.assertEquals("Category", product.getCategory());
    }

    @Test
    public void testGetPid() {
        Assert.assertEquals("Product ID", product.getPid());
    }

    @Test
    public void testGetDate() {
        Assert.assertEquals("Date", product.getDate());
    }

    @Test
    public void testGetTime() {
        Assert.assertEquals("Time", product.getTime());
    }
}
