package com.example.demo;

import java.util.ArrayList;

public class Login {
    private String username;
    private String password;
    private boolean premium;
    private ArrayList<Playlist> playlists= new ArrayList<>();
    public Login(String user, String pass, boolean prem) {
        username = user;
        password= pass;
        premium = prem;

    }
    public String getUsername(){
        return username;
    }
    public void setPremium(){
        premium=true;
    }
    public String getPassword(){
        return password;
    }
    public boolean addPlayList(String name){
        if(premium || playlists.size() < 2) {
            playlists.add(new Playlist(name));
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<Playlist> getPlaylists(){
        return playlists;
    }
    public String vypisHudby(){
        String pom="";
        for(Playlist pl: playlists){
            for(Song sng: pl.getSongs()){
                pom+="<p> " + sng.getAutor() + ": " + sng.getName() + " </p>";
            }
        }
        return pom;
    }
    public boolean isPremium(){
        return premium;
    }

}
