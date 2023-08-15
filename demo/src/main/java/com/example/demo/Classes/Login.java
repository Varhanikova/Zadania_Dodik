package com.example.demo.Classes;

import java.util.ArrayList;
import java.util.Objects;

public class Login {
    private String username;
    private String password;
    private String premium;
    private double fee;
    //private ArrayList<Playlist> playlists= new ArrayList<>();


    public Login(String user, String pass, String prem) {
        username = user;
        password= pass;
        premium = prem;
        if (!Objects.equals(premium, "A")) {
            fee=0;
        } else {
            fee=5;
        }
    }

    public Login(String user, String pass, String prem,double pfee) {
        username = user;
        password= pass;
        premium = prem;
        fee=pfee;
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
//
//    public ArrayList<Playlist> getPlaylists(){
//        return playlists;
//    }
//    public String vypisHudby(){
//        String pom="";
//        for(Playlist pl: playlists){
//            pom+="<p> " + pl.getNazov() + ":  </p>";
//            pom+=pl.playSongs();
//        }
//        return pom;
//    }
    public String isPremium(){
        return premium;
    }

//    public ArrayList<Song> getAllSavedSongs(){
//        ArrayList<Song> pom = new ArrayList<>();
//        for(Playlist pl: playlists){
//            pom.addAll(pl.getSongs());
//        }
//        return pom;
//    }
}
