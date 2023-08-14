package com.example.demo;

import java.util.ArrayList;

public class Login {
    private String username;
    private String password;
    private String premium;
    private double fee;
    private ArrayList<Playlist> playlists= new ArrayList<>();


    public Login(String user, String pass, String prem) {
        username = user;
        password= pass;
        premium = prem;
        if (premium != "A") {
            fee=0;
        } else {
            fee=5;
        }
    }
    public String getUsername(){
        return username;
    }
    public void setPremium(){
        premium="A";
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
        if(premium=="A" || playlists.size() < 2) {
            playlists.add(new Playlist(playlists.size()+1,name,this));
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
    public String isPremium(){
        return premium;
    }

    public ArrayList<Song> getAllSavedSongs(){
        ArrayList<Song> pom = new ArrayList<>();
        for(Playlist pl: playlists){
            pom.addAll(pl.getSongs());
        }
        return pom;
    }
}
