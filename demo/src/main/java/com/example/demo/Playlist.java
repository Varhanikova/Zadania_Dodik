package com.example.demo;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String nazov;
    private Login login;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist(int p_id,String p_nazov,Login l){
        nazov = p_nazov;
        id = p_id;
        login = l;
    }

    public int getId() {
        return id;
    }

    public Login getLogin() {
        return login;
    }

//    public void addSong(Song sng){
//        songs.add(sng);
//    }
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
