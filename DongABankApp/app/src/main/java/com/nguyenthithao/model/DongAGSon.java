package com.nguyenthithao.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DongAGSon implements Serializable {
    private ArrayList<Item> items;
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items){
        this.items = items;
    }
}