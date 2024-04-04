package com.nguyenthithao.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String ID;
    private String ProductName;

    private String ProductCode;
    private float UnitPrice;
    private String ImageLink;

    public Product() {
    }

    public Product(String ID, String productName, String productCode, float unitPrice, String imageLink) {
        this.ID = ID;
        ProductName = productName;
        ProductCode = productCode;
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

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
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