package com.example.demo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SongService {
    private ArrayList<Song> songs =  new ArrayList<>();
    @Autowired
    private  jdbcSongRepository jdbcSongRepository;
    public ArrayList<Song> getSongs(){return songs;}
    public SongService(){

    }
    public void fillListWithRandomSongs(){
        songs.add(new Song(1,"Falling in Reverse","Carry On"));
        songs.add(new Song(2,"Polaris","Masochist"));
        songs.add(new Song(3,"Caskets","Glass Heart"));
        songs.add(new Song(4,"Polaris", "Crooked Path"));
        songs.add(new Song(5,"Ice Nine Kills","The Shower Scene"));
        songs.add(new Song(6,"Architects","Gone With The Wind"));
        songs.add(new Song(7,"Holding Absence","Like a Shadow"));
        songs.add(new Song(8,"Thousands Below", "Hell finds you everywhere"));
        songs.add(new Song(9,"Nine Lashes ","Adrenaline"));
        songs.add(new Song(10,"Bad Omens","Just Pretend"));
    }

    public void fillListWithMetalicaSongs(){
            songs.add(new Song(11,"Metallica", "Nothing else matters"));
            songs.add(new Song(12,"Metallica", "The Unforgiven I"));
            songs.add(new Song(13,"Metallica", "The Unforgiven II"));
            songs.add(new Song(14,"Metallica", "Master of Puppets"));
            songs.add(new Song(15,"Metallica", "Enter Sandman"));
            songs.add(new Song(16,"Metallica", "Whiskey in the Jar"));
            songs.add(new Song(17,"Metallica", "For Whom The Bell Tolls"));
            songs.add(new Song(18,"Metallica", "The Day That Never Comes"));
            songs.add(new Song(19,"Metallica", "Sad But True"));
            songs.add(new Song(20,"Metallica", "The Memory Remains"));
    }

    @GetMapping("pesnicky/autor/{autor}")
    public String getSongByAutor(@PathVariable String autor){
        String pom="";
        for(Song sng: jdbcSongRepository.getSongs()) {
            if(sng.getAutor().equalsIgnoreCase(autor)){
                pom+="<p>" +  sng + "</p>";
            }
        }
        return pom;
    }
    @PostMapping("pesnicky/pridaj/{id}/{autor}/{nazov}")
    public String addNewSong(@PathVariable int id,@PathVariable String autor,@PathVariable String nazov){
            //songs.add(new Song(songs.size()+1,autor, nazov));
            return jdbcSongRepository.addSong(new Song(id,autor,nazov))? "Song " +autor + ": " + nazov + " pridany!" : "Song sa nepodarilo pridať!";
    }
    @PostMapping("pesnicky/pridaj")
    public String addNewSong(@RequestBody Song song) {
//        if(!existuje(song.getName(),song.getAutor())){
//            songs.add(song);
//            return "Song " + song.getAutor() + ": " + song.getName() + " pridany!";
//        }
           return jdbcSongRepository.addSong(song) ? "Song " + song.toString() + " pridany!" :"Song already exists!";
    }
    public boolean existuje(String nazov, String autor){
        for(Song sng: jdbcSongRepository.getSongs()){
            if(sng.getName().equalsIgnoreCase(nazov) && sng.getAutor().equalsIgnoreCase(autor)){
                return true;
            }
        }
        return false;
    }

    @GetMapping("pesnicky/random")
    public String randomSong(){
        Random rand = new Random();
        int rnd = rand.nextInt(jdbcSongRepository.getPocetSongs());
        return jdbcSongRepository.getSongs().get(rnd).toString();
    }
    @GetMapping("pesnicky/all")
    public String getAll(){
        String pom ="";
        for(Song sng: jdbcSongRepository.getSongs()){
            pom+="<p>" +  sng.toString() + "</p>";
        }
        return pom;
    }

}
