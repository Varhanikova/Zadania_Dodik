package com.example.demo;

import java.util.ArrayList;

public class Login {
    private String username;
    private String password;
    private boolean premium;
    private double fee;
    private ArrayList<Playlist> playlists= new ArrayList<>();
    public Login(String user, String pass, boolean prem) {
        username = user;
        password= pass;
        premium = prem;
        if(prem){
            fee=5;
        }else{
            fee=0;
        }
    }
    public String getUsername(){
        return username;
    }
    public void setPremium(){
        premium=true;
    }
    public void addFee(double p_fee){
        fee+=p_fee;
    }
    public double getFee() {
        return fee;
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
            pom+="<p> " + pl.getNazov() + ":  </p>";
            pom+=pl.playSongs();
        }
        return pom;
    }
    public boolean isPremium(){
        return premium;
    }

    public ArrayList<Song> getAllSavedSongs(){
        ArrayList<Song> pom = new ArrayList<>();
        for(Playlist pl: playlists){
            for(Song sng: pl.getSongs()){
                pom.add(sng);
            }
        }
        return pom;
    }
}
