package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@RestController
public class MoneyService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SongService songService;
    private double sum=0;

    public double getSum()
    {
        return sum;
    }
    public void addtoSum(double price){
        sum+=price;
    }
   public void setSongService(SongService sg){songService= sg;}
    public void setLoginService(LoginService lg){loginService=lg;}

    @GetMapping("money")
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
    public double sumOfMoney(){
        double sum=0;
        for(Login lg: loginService.getLogins()){
            sum+=lg.getFee();
           }
        for(Song sng: songService.getSongs()){
            sum+=sng.getFee();
        }
        return sum;
    }
}

