package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
public class LoginService {
    private ArrayList<Login> logins =  new ArrayList<>();
    private Login actualUser=null;
    @Autowired
    private SongService songService;

    public LoginService(){
        logins.add(new Login("user","pass"));
    }

    @PostMapping("prihlasenie/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password){
        Login log = new Login(username,password);
        for(Login login: logins){
            if(login.getUsername().equalsIgnoreCase(username) && login.getPassword().equalsIgnoreCase(password)){
                actualUser=login;
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
        if (actualUser!=null) {
            actualUser.addPlayList(name);
            return "Playlist " + name + " added to user " + actualUser.getUsername();
        }
        return "No user!";
    }
    @PostMapping("prihlasenie/pridajSong/{Playlist}/{name}")
    public String addSongToPlaylist(@PathVariable String Playlist, @PathVariable String name){
        if(actualUser!=null){
            for(Playlist pl: actualUser.getPlaylists()){
                if(pl.getNazov().equalsIgnoreCase(Playlist)){
                    for(Song sng: songService.getSongs()){
                        if(sng.getName().equalsIgnoreCase(name)){
                            pl.addSong(sng);
                            return "Song " + name + " added to playlist " + pl.getNazov();
                        }
                    }
                }
            }

        }
        return "No user!";
    }
    @GetMapping("prihlasenie/allSongs")
    public String allSongs(){
        if(actualUser!=null){
            return actualUser.vypisHudby();
        }
        return "No user!";
    }

}
