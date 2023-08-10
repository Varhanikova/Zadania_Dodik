package com.example.demo;
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
        for(Login login: logins){
            if(login.getUsername().equals(username) && login.getPassword().equals(password)){
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
    @PostMapping("prihlasenie/pridajSong/{playlist}/{name}")
    public String addSongToPlaylist(@PathVariable String playlist, @PathVariable String name){
        if(actualUser==null) {
            return "No user!";
        }
        Playlist pl = findPlaylist(playlist);
        if(pl==null){
            return "No playlist " + playlist+ " found!";
        }
        Song sng = findSong(name);
        if(sng==null){
            return "no song " + name + " found!";
        }
        return "Song " + name + " added to playlist " + pl.getNazov();
    }
    public Playlist findPlaylist(String name){
        for(Playlist pl: actualUser.getPlaylists()){
            if(pl.getNazov().equalsIgnoreCase(name)){
                return pl;
            }
        }
        return null;
    }

    public Song findSong(String name){
        for(Song sng: songService.getSongs()){
            if(sng.getName().equalsIgnoreCase(name)){
                return sng;
            }
        }
        return null;
    }
    @GetMapping("prihlasenie/allSongs")
    public String allSongs(){
        if(actualUser!=null){
            return actualUser.vypisHudby();
        }
        return "No user!";
    }

}
