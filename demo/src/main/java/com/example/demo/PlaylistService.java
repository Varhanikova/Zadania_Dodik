package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class PlaylistService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SongService songService;
    @Autowired
    private AdService adService;
    @Autowired private jdbcPlaylistRepository jdbcPl;
    @Autowired private jdbcSongRepository jdbcSongRepository;
    @Autowired private jdbcPlaylistSongRepository jdbcPlaylistSongRepository;
    @Autowired private jdbcAdRepository jdbcAdRepository;

    public PlaylistService(){

    }

    public void setAdService(AdService adService) {
        this.adService = adService;
    }

    public void setLoginService(LoginService ls){
        loginService = ls;
    }
    @PostMapping("prihlasenie/pridajPlaylist/{id}/{name}")
    public String addPlayList(@PathVariable int id, @PathVariable String name){
        if (loginService.getActualUser()==null) {
            return "No user!";
        }
        if(!loginService.getActualUser().isPremium().equals("A")){
            if(jdbcPl.getPocetPlaylistov(loginService.getActualUser().getUsername())>=2){
                return "No other playlist is allowed! " + loginService.getActualUser().getUsername()+" switch to premium!";
            }
        }
        return jdbcPl.addPlaylist(new Playlist(id,name, loginService.getActualUser())) ? "Playlist " + name + " added to user " + loginService.getActualUser().getUsername():
                "Nepodarilo sa vložiť!";
    }
    @PostMapping("prihlasenie/pridajSong/{id}/{playlist}/{name}")
    public String addSongToPlaylist(@PathVariable int id, @PathVariable String playlist, @PathVariable String name){
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

        return jdbcPlaylistSongRepository.addSong(id,sng,pl)? "Song " + name + " added to playlist " + pl.getNazov() :
                "Nepodarilo sa vložiť!";
    }
    public Playlist findPlaylist(String name){
        for(Playlist pl: jdbcPl.getPlaylistPodlaUsera(loginService.getActualUser().getUsername())){
            if(pl.getNazov().equalsIgnoreCase(name)){
                return pl;
            }
        }
        return null;
    }
    public Song findSong(String name){
        for(Song sng: jdbcSongRepository.getSongs()){
            if(sng.getName().equalsIgnoreCase(name)){
                return sng;
            }
        }
        return null;
    }
    @GetMapping("prihlasenie/allSongs") //jo
    public String allSongs(){
        String pom="";
        if(loginService.getActualUser()!=null){
            for(Playlist pl: jdbcPl.getPlaylistPodlaUsera(loginService.getActualUser().getUsername())){
                pom+="<p> " + pl.getNazov() + ": </p>";
                for(PlaylistSong sng: jdbcPlaylistSongRepository.getSongsByPlaylist(pl.getId())){
                   pom+="<p> " + sng.getSong().toString() + " </p>";
                }
            }
        } else{
            pom="no user!";
        }
        return pom;
    }
    public List<PlaylistSong> getSongsByActualUserPlaylist(String name){
        Playlist pl = findPlaylist(name);
        if(pl==null){
            return null;
        }
        List<PlaylistSong> songs = jdbcPlaylistSongRepository.getSongsByPlaylist(pl.getId());
        Collections.shuffle(songs);
        return songs;
    }
    @GetMapping("PS/playRandom/{playlist}") //jo
    public String playSongs(@PathVariable String playlist){
        Random rnd = new Random();
        String pom="";
        List<PlaylistSong>songs = getSongsByActualUserPlaylist(playlist);
        if(songs==null){
            return "No playlist found!";
        }
       List<Ad> ads = jdbcAdRepository.getAds();
        double prob=0; double prev=0;
        for (PlaylistSong sng : songs) {
            if (rnd.nextDouble() < 0.1 && !loginService.getActualUser().isPremium().equals("A")) {
                prob = rnd.nextDouble();
                for (int i =0;i<ads.size();i++) {
                    if(i==0) {
                        prev=0;
                    } else{
                        prev = ads.get(i-1).getProbability();
                    }
                    if (prev<=prob && (prev+ ads.get(i).getProbability()) >= prob) {
                       pom+= (jdbcSongRepository.addFee(ads.get(i).getProfit(),sng.getSong().getId()) &&  jdbcAdRepository.addUsed(ads.get(i).getSponzor())) ?
                               "<p>" + sng.getSong().toString() + " was played with ad " + ads.get(i).getSponzor() + " </p>" :
                               " <p> Nepodarilo sa prehrať!!";
                        break;
                    }
                }
            } else {
                pom+="<p>" +  sng.getSong().toString() + " was played without ad </p>";
            }
        }
        return pom;
    }
    public void playSongsTest(String playlist){
        List<PlaylistSong> songs = getSongsByActualUserPlaylist(playlist);
        if(songs==null){
            return;
        }
        List<Ad> ads = adService.getAds();
        if(!loginService.getActualUser().isPremium().equals("A")) {
            int i = 0;
            for (PlaylistSong sng : songs) {
                sng.getSong().addFee(ads.get(i % 4).getProfit());
                ads.get(i % 4).setUsed();
                i++;
            }
        }
    }

    public void setSongsService(SongService pSS) {
        songService = pSS;
    }
}
