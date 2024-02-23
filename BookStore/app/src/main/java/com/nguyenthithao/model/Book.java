package com.nguyenthithao.model;

public class Book {
    private String bookID;
    private String bookName;
    private float unitPrice;

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
