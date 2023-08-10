package com.example.demo;

import java.util.ArrayList;

public class Playlist {
    private String nazov;
    private ArrayList<Song> songs = new ArrayList<>();
    public Playlist(String p_nazov){
        nazov = p_nazov;
    }
    public void addSong(Song sng){
        songs.add(sng);
    }

}
