package com.nguyenthithao.model;

import android.graphics.Bitmap;

import java.io.Serializable;
 public class Item implements Serializable {
    private String type;
    private String imageurl;
    private Bitmap hinh;
    private String muatienmat;
    private String muack;
    private String bantienmat;
    private String banck;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public Bitmap getHinh() {
        return hinh;
    }
    public void setHinh(Bitmap hinh) {
        this.hinh = hinh;
    }

    public String getMuatienmat() {
        return muatienmat;
    }
    public void setMuatienmat(String muatienmat) {
        this.muatienmat = muatienmat;
    }
    public String getMuack() {
        return muack;
    }
    public void setMuack(String muack) {
        this.muack = muack;
    }
    public String getBantienmat() {
        return bantienmat;
    }
    public void setBantienmat(String bantienmat) {
        this.bantienmat = bantienmat;
    }
    public String getBanck() {
        return banck;
    }
    public void setBanck(String banck) {
        this.banck = banck;
    }
}
