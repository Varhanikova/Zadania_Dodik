package com.example.demo;

import java.util.ArrayList;

public class Login {
    private String username;
    private String password;
    private ArrayList<Playlist> playlists= new ArrayList<>();
    public Login(String user, String pass) {
        username = user;
        password= pass;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void addPlayList(String name){
        playlists.add(new Playlist(name));
    }
}
