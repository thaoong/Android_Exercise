package com.nguyenthithao.model;

public class Song {
    private String songID;
    private String name;
    private String singer;
    private boolean isLiked;

    public Song() {
    }

    public Song(String songID, String name, String singer, boolean isLiked) {
        this.songID = songID;
        this.name = name;
        this.singer = singer;
        this.isLiked = isLiked;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
