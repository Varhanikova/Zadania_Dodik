package com.example.demo;
import jakarta.annotation.PostConstruct;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SongService {
    private ArrayList<Song> songs =  new ArrayList<>();

    public ArrayList<Song> getSongs(){return songs;}
    public SongService(){
        fillListWithRandomSongs();
        fillListWithMetalicaSongs();
    }
    public void fillListWithRandomSongs(){
        songs.add(new Song("Falling in Reverse","Carry On"));
        songs.add(new Song("Polaris","Masochist"));
        songs.add(new Song("Caskets","Glass Heart"));
        songs.add(new Song("Polaris", "Crooked Path"));
        songs.add(new Song("Ice Nine Kills","The Shower Scene"));
        songs.add(new Song("Architects","Gone With The Wind"));
        songs.add(new Song("Holding Absence","Like a Shadow"));
        songs.add(new Song("Thousands Below", "Hell finds you everywhere"));
        songs.add(new Song("Nine Lashes ","Adrenaline"));
        songs.add(new Song("Bad Omens","Just Pretend"));
    }

    public void fillListWithMetalicaSongs(){
            songs.add(new Song("Metallica", "Nothing else matters"));
            songs.add(new Song("Metallica", "The Unforgiven I"));
            songs.add(new Song("Metallica", "The Unforgiven II"));
            songs.add(new Song("Metallica", "Master of Puppets"));
            songs.add(new Song("Metallica", "Enter Sandman"));
            songs.add(new Song("Metallica", "Whiskey in the Jar"));
            songs.add(new Song("Metallica", "For Whom The Bell Tolls"));
            songs.add(new Song("Metallica", "The Day That Never Comes"));
            songs.add(new Song("Metallica", "Sad But True"));
            songs.add(new Song("Metallica", "The Memory Remains"));
    }

    @GetMapping("pesnicky/autor/{autor}")
    public String getSongByAutor(@PathVariable String autor){
        String pom="";
        for(Song sng: songs) {
            if(sng.getAutor().equalsIgnoreCase(autor)){
                pom+="<p>" +  sng + "</p>";
            }
        }
        return pom;
    }
    @PostMapping("pesnicky/pridaj/{autor}/{nazov}")
    public String addNewSong(@PathVariable String autor,@PathVariable String nazov){
            songs.add(new Song(autor, nazov));
            return "Song " +autor + ": " + nazov + " pridany!";
    }
    @PostMapping("pesnicky/pridaj")
    public String addNewSong(@RequestBody Song song) {
        if(!existuje(song.getName(),song.getAutor())){
            songs.add(song);
            return "Song " + song.getAutor() + ": " + song.getName() + " pridany!";
        }
           return "Song already exists!";
    }
    public boolean existuje(String nazov, String autor){
        for(Song sng: songs){
            if(sng.getName().equalsIgnoreCase(nazov) && sng.getAutor().equalsIgnoreCase(autor)){
                return true;
            }
        }
        return false;
    }

    @GetMapping("pesnicky/random")
    public String randomSong(){
        Random rand = new Random();
        int rnd = rand.nextInt(songs.size());
        return songs.get(rnd).toString();
    }
    @GetMapping("pesnicky/all")
    public String getAll(){
        String pom ="";
        for(Song sng: songs){
            pom+="<p>" +  sng.toString() + "</p>";
        }
        return pom;
    }

}
