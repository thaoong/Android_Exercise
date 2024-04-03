package com.nguyenthithao.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Product implements Serializable {
    private String ProductID;
    private String ProductName;
    private float UnitPrice;
    private String ImageLink;
    private String CategoryID;

    public Product() {
    }

    public Product(String productID, String productName, float unitPrice, String imageLink, String categoryID) {
        ProductID = productID;
        ProductName = productName;
        UnitPrice = unitPrice;
        ImageLink = imageLink;
        CategoryID = categoryID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }
}
