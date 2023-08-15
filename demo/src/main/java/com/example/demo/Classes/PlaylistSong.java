package com.example.demo.Classes;

public class PlaylistSong {
    private int id;
    private Playlist playlist;
    private Song song;

    public int getId() {
        return id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public Song getSong() {
        return song;
    }
    public PlaylistSong(int pid, Playlist pl, Song s){
        id = pid;
        playlist=pl;
        song = s;
    }
}
