package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

public class LoginService {
    private ArrayList<Login> logins =  new ArrayList<>();
    private Login actualUser=null;

    @GetMapping("prihlasenie/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password){
        Login log = new Login(username,password);
        for(Login login: logins){
            if(login.getUsername().equalsIgnoreCase(username) && login.getPassword().equalsIgnoreCase(password)){
                actualUser=log;
                return "Logged as " + username;
            }
        }
        return "Wrong credentials!";
    }
    @PostMapping("prihlasenie/pridaj")
    public String login(@RequestBody Login login){
        logins.add(login);
        return "Login with username " +login.getUsername() + " added!";
    }
    @GetMapping("prihlasenia")
    public String getLogins(){
        String pom="";
        for(Login log: logins){
            pom+="<p> "+ log.getUsername() + " </p>";
        }
        return pom;
    }
    @PostMapping("prihlasenie/pridajPlaylist/{name}")
    public String addPlayList(@PathVariable String name){
        actualUser.addPlayList(name);
        return "Playlist " + name + " added to user " + actualUser;
    }
}
