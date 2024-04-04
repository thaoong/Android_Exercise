package com.nguyenthithao.model;

import java.io.Serializable;
import java.util.ArrayList;

public class FakeStoreGSon implements Serializable {
    private ArrayList<FakeStore> items;
    public ArrayList<FakeStore> getItems() {
        return items;
    }
    public void setItems(ArrayList<FakeStore> items){
        this.items = items;
    }
}
