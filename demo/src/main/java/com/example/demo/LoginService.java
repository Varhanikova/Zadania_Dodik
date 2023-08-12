package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class LoginService {
    private ArrayList<Login> logins =  new ArrayList<>();
    private Login actualUser=null;
    @Autowired
    private SongService songService;


    public LoginService(){
       logins.add(new Login("user","pass",false));
    }
    public Login getActualUser(){
        return actualUser;
    }
    public void setActualUser(Login user){
        actualUser=user;
    }
    @GetMapping("test")
    public void Test(){
        actualUser = logins.get(0);
        actualUser.addPlayList("moj");
        Playlist pl = findPlaylist("moj");
        for(Song sng :songService.getSongs()){
            pl.addSong(sng);
        }
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
        Login l = new Login(username,password,premium);
        logins.add(l);
        if(premium){
            l.addFee(5);
            return "Login with username " +username + " added as premium user!";
        }
        return "Login with username " +username + " added!";
    }
    @GetMapping("prihlasenia")
    public String vypisPrihlasenia(){
        String pom="";
        for(Login log: logins){
            pom+="<p> "+ log.getUsername() + " </p>";
        }
        return pom;
    }
    public ArrayList<Login> getLogins(){
        return logins;
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
        pl.addSong(sng);
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
    public ArrayList<Song> getSongsByActualUserPlaylist(String name){
        Playlist pl = findPlaylist(name);
        if(pl==null){
            return null;
        }
        Collections.shuffle(pl.getSongs());
        return pl.getSongs();
    }
}
