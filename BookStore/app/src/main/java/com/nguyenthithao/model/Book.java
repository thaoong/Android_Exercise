package com.nguyenthithao.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookID;
    private String bookName;
    private float unitPrice;
    private int imageID;
    private Publisher publisher;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Book (String bookID, String bookName, float unitPrice, int imageID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.imageID = imageID;
    }

    public Book() {
    }

    public Book(String bookID, String bookName, float unitPrice) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.unitPrice = unitPrice;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        String msg = bookID+"\n"+bookName+"\n"+unitPrice+"đ";
        return msg;
    }
}
