package com.nguyenthithao.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String ID;
    private String ProductName;

    private float UnitPrice;
    private String ImageLink;

    public Product() {
    }

    public Product(String ID, String productName, float unitPrice, String imageLink) {
        this.ID = ID;
        ProductName = productName;
        UnitPrice = unitPrice;
        ImageLink = imageLink;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
}