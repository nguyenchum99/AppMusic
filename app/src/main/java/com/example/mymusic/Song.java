package com.example.mymusic;

public class Song {

    private int idImage;
    private int id;
    private  String title;
    private String artist;

    public Song(String title, String artist,int idImage, int id) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.idImage = idImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
