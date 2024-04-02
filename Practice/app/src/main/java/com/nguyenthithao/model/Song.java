package com.nguyenthithao.model;

public class Song {
    private String ma;
    private String tenbaihat;
    private String tacgia;
    private int favorite;

    public Song() {
    }

    public Song(String ma, String tenbaihat, String tacgia, int favorite) {
        this.ma = ma;
        this.tenbaihat = tenbaihat;
        this.tacgia = tacgia;
        this.favorite = favorite;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
