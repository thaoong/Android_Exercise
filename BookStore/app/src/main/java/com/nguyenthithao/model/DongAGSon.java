package com.nguyenthithao.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DongAGSon implements Serializable {
    private ArrayList<DongAItem>items;
    public ArrayList<DongAItem> getItems() {
        return items;
    }
    public void setItems(ArrayList<DongAItem> items){
        this.items = items;
    }
}
