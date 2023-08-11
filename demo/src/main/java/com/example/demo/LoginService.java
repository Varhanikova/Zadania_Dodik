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
        logins.add(new Login("user","pass",false));
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
    public String addLogin(@RequestBody Login login){
        logins.add(login);
        return "Login with username " +login.getUsername() + " added!";
    }
    @PostMapping("prihlasenie/pridaj/{username}/{password}/{premium}")
    public String addLogin(@PathVariable String username, @PathVariable String password, @PathVariable(required = false) boolean premium){
//        if(premium==null){
//            premium= false;
//        }
        logins.add(new Login(username,password,premium));
        if(premium){
            return "Login with username " +username + " added as premium user!";
        }
        return "Login with username " +username + " added!";
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
        if (actualUser==null) {
            return "No user!";
        }
        if(!actualUser.addPlayList(name)){
            return "No other playlist is allowed! " + actualUser.getUsername()+" switch to premium!";
        }
        return "Playlist " + name + " added to user " + actualUser.getUsername();
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
