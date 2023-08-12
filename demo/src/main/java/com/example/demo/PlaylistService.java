package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@RestController
public class PlaylistService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SongService songService;

    public PlaylistService(){

    }
    public void setLoginService(LoginService ls){
        loginService = ls;
    }
    @PostMapping("prihlasenie/pridajPlaylist/{name}")
    public String addPlayList(@PathVariable String name){
        if (loginService.getActualUser()==null) {
            return "No user!";
        }
        if(!loginService.getActualUser().addPlayList(name)){
            return "No other playlist is allowed! " + loginService.getActualUser().getUsername()+" switch to premium!";
        }
        return "Playlist " + name + " added to user " + loginService.getActualUser().getUsername();
    }
    @PostMapping("prihlasenie/pridajSong/{playlist}/{name}")
    public String addSongToPlaylist(@PathVariable String playlist, @PathVariable String name){
        if(loginService.getActualUser()==null) {
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
        for(Playlist pl: loginService.getActualUser().getPlaylists()){
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
        if(loginService.getActualUser()!=null){
            return loginService.getActualUser().vypisHudby();
        }
        return "No user!";
    }
    public ArrayList<Song> getSongsByActualUserPlaylist(String name){ //Playlist
        Playlist pl = findPlaylist(name);
        if(pl==null){
            return null;
        }
        Collections.shuffle(pl.getSongs());
        return pl.getSongs();
    }
    @GetMapping("PS/playRandom/{playlist}") // Playlist
    public String playSongs(@PathVariable String playlist){
        Random rnd = new Random();
        String pom="";
        ArrayList<Song> songs = getSongsByActualUserPlaylist(playlist);
        if(songs==null){
            return "No playlist found!";
        }
        for (Song sng : songs) {
            if (rnd.nextDouble() < 0.1 && !loginService.getActualUser().isPremium()) {
                sng.addFee(0.1);
                pom+="<p>" +  sng.toString() + " was played with ad </p>";
            } else {
                pom+="<p>" +  sng.toString() + " was played without ad </p>";
            }
        }
        return pom;
    }
}
