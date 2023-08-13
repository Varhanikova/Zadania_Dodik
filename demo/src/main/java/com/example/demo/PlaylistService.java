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
    @Autowired
    private AdService adService;

    public PlaylistService(){

    }

    public void setAdService(AdService adService) {
        this.adService = adService;
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
        for(Song sn: pl.getSongs()){
            if(sn.getAutor().equalsIgnoreCase(sng.getAutor() )&& sn.getName().equalsIgnoreCase(sng.getName())){
                return "Song is alredy in playlist!";
            }
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
    @GetMapping("PS/fillPlaylist/{playlist}")
    public void fillPlaylistWithAll(@PathVariable String playlist){
        Playlist pl = findPlaylist(playlist);
        for(Song sng: songService.getSongs()){
            pl.addSong(sng);
        }
    }

    @GetMapping("prihlasenie/allSongs")
    public String allSongs(){
        if(loginService.getActualUser()!=null){
            return loginService.getActualUser().vypisHudby();
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
    @GetMapping("PS/playRandom/{playlist}")
    public String playSongs(@PathVariable String playlist){
        Random rnd = new Random();
        String pom="";
        ArrayList<Song> songs = getSongsByActualUserPlaylist(playlist);
        if(songs==null){
            return "No playlist found!";
        }
        ArrayList<Ad> ads = adService.getAds();
        double prob=0; double prev=0;
        for (Song sng : songs) {
            if (rnd.nextDouble() < 0.1 && !loginService.getActualUser().isPremium()) {
                prob = rnd.nextDouble();
                for (int i =0;i<ads.size();i++) {
                    if(i==0) {
                        prev=0;
                    } else{
                        prev = ads.get(i-1).getProbability();
                    }
                    if (prev<=prob && (prev+ ads.get(i).getProbability()) >= prob) {
                        sng.addFee(ads.get(i).getProfit());
                        ads.get(i).setUsed();
                        pom += "<p>" + sng.toString() + " was played with ad " + ads.get(i).getSponzor() + " </p>";
                        break;
                    }
                }
            } else {
                pom+="<p>" +  sng.toString() + " was played without ad </p>";
            }
        }
        return pom;
    }
    public void playSongsTest(String playlist){
        ArrayList<Song> songs = getSongsByActualUserPlaylist(playlist);
        if(songs==null){
            return;
        }
        ArrayList<Ad> ads = adService.getAds();
        if(!loginService.getActualUser().isPremium()) {
            int i = 0;
            for (Song sng : songs) {
                sng.addFee(ads.get(i % 4).getProfit());
                ads.get(i % 4).setUsed();
                i++;
            }
        }
    }

    public void setSongsService(SongService pSS) {
        songService = pSS;
    }
}
