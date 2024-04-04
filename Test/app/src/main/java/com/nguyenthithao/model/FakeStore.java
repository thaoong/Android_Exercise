package com.nguyenthithao.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class FakeStore implements Serializable {
    private String id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String imageurl;

    private Bitmap image;
    private float rate;
    private float count;

    public FakeStore() {
    }

    public FakeStore(String id, String title, float price, String description, String category, String imageurl, Bitmap image, float rate, float count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageurl = imageurl;
        this.image = image;
        this.rate = rate;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
