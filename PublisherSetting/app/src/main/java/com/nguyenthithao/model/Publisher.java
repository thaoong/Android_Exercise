package com.nguyenthithao.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Publisher implements Serializable {
    private String publisherID;
    private String publisherName;
    private ArrayList<Book>books;

    public Publisher() {
        books = new ArrayList<>();
    }

    public Publisher(String publisherID, String publisherName) {
        this();
        this.publisherID = publisherID;
        this.publisherName = publisherName;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public String toString() {
        String msg = publisherID+"-"+publisherName;
        return msg;
    }
}
