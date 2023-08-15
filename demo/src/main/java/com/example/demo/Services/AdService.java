package com.example.demo.Services;

import com.example.demo.Classes.Ad;
import com.example.demo.Classes.Login;
import com.example.demo.Classes.Song;
import com.example.demo.Repositories.JdbcLoginRepository;
import com.example.demo.Repositories.jdbcAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SongService songService;
    @Autowired
    private jdbcAdRepository adRepository;
    @Autowired
    com.example.demo.Repositories.jdbcSongRepository jdbcSongRepository;
    @Autowired
    JdbcLoginRepository jdbcLoginRepository;
    @Autowired
    com.example.demo.Repositories.jdbcPlaylistSongRepository jdbcPlaylistSongRepository;
    @Autowired
    com.example.demo.Repositories.jdbcPlaylistRepository jdbcPlaylistRepository;
    private ArrayList<Ad> ads = new ArrayList<>();

    public AdService(){

    }
    public List<Ad> getAds() {
        return adRepository.getAds();
    }

   public void setSongService(SongService sg){songService= sg;}
    public void setLoginService(LoginService lg){loginService=lg;}
    public String addAd(Ad p_ad){
       return adRepository.addAd(p_ad)? "Ad " + p_ad.getSponzor() + " pridaná!" : "Nepodarilo sa pridať!";
    }
    public boolean checkProbability(){
        double sum=0;
        for(Ad ad: adRepository.getAds()){
            sum+=ad.getProbability();
        }
        return sum == 1;
    }
    @GetMapping("Ad/songsAd") //jop
    public String listSongsWithAd(){
        String pom="";
        for(Song sng: jdbcSongRepository.getSongs()){
            if(sng.getFee()>0.0){
                pom+= "<p> " + sng.songAd() + " </p>";
            }
        }
        return pom;
    }
    public double sumOfProfit(){
        double sum=0;
        for(Login lg: jdbcLoginRepository.getLoginsDB()){
            sum+=lg.getFee();
           }
        for(Song sng: jdbcSongRepository.getSongs()){
            sum+=sng.getFee();
        }
        return sum;
    }
    @GetMapping("Ad/profit")//jop
    public String listAdsWithProfit(){
        String pom="";
        for(Ad ad: adRepository.getAds()){
            pom+="<p> Ad " + ad.getSponzor() + " has profit: " + ad.getProfit()*ad.getUsed() + " </p>";
        }
        return pom;
    }
}

