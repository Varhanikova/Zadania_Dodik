package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
public class AdService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SongService songService;
    private ArrayList<Ad> ads = new ArrayList<>();

    public AdService(){
        ads.add(new Ad("Coca Cola",0.1,0.35));
        ads.add(new Ad("Lotus",0.2,0.55));
    }
    public ArrayList<Ad> getAds() {
        return ads;
    }

   public void setSongService(SongService sg){songService= sg;}
    public void setLoginService(LoginService lg){loginService=lg;}
    public void addAd(Ad p_ad){
        ads.add(p_ad);
    }
    public boolean checkProbability(){
        double sum=0;
        for(Ad ad: ads){
            sum+=ad.getProbability();
        }
        return sum == 1;
    }
    @GetMapping("Ad/songsAd")
    public String listSongsWithAd(){
        String pom="";
        ArrayList<Song> songs = loginService.getActualUser().getAllSavedSongs();
        for(Song sng :songs){
            if(sng.getFee()>0.0){
               pom+= "<p> " + sng.songAd() + " </p>";
            }
        }
        return pom;
    }
    public double sumOfProfit(){
        double sum=0;
        for(Login lg: loginService.getLogins()){
            sum+=lg.getFee();
           }
        for(Song sng: songService.getSongs()){
            sum+=sng.getFee();
        }
        return sum;
    }
    @GetMapping("Ad/profit")
    public String listAdsWithProfit(){
        String pom="";
        for(Ad ad: ads){
            pom+="<p> Ad " + ad.getSponzor() + " has profit: " + ad.getProfit()*ad.getUsed() + " </p>";
        }
        return pom;
    }
}

