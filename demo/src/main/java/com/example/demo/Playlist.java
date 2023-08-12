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
    public String getNazov(){return nazov;}
    public ArrayList<Song> getSongs(){
        return songs;
    }
    public String playSongs(){
        String pom="";
        for(Song sng: songs){
            pom+="<p> " + sng.getAutor()+": "+ sng.getName()+" </p>";
        }
        return pom;
    }

}
