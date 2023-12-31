package com.example.demo.Repositories;

import com.example.demo.Classes.Playlist;
import com.example.demo.Classes.PlaylistSong;
import com.example.demo.Classes.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class jdbcPlaylistSongRepository {
    @Autowired
    private JdbcOperations jdbcOperations;
    @Autowired private com.example.demo.Repositories.jdbcSongRepository jdbcSongRepository;
    @Autowired private com.example.demo.Repositories.jdbcPlaylistRepository jdbcPlaylistRepository;

    private PlaylistSong mapRow(ResultSet rs, int index) throws SQLException {
        int id = rs.getInt("id");
        int id_playlist = rs.getInt("id_playlist");
        int id_song = rs.getInt("id_song");
        return new PlaylistSong(id,jdbcPlaylistRepository.getPlaylistPodlaId(id_playlist),jdbcSongRepository.getSongById(id_song));
    }
    public List<PlaylistSong> getPlaylistSongs(){
        String sql = "select * from PlaylistSong";
        return jdbcOperations.query(sql,this::mapRow);
    }
    public List<PlaylistSong> getSongsByPlaylist(int pl){
        String sql = "select * from PlaylistSong where id_playlist = ?";
        return jdbcOperations.query(sql,this::mapRow,pl);
    }
    public boolean addSong(int id, Song song, Playlist pl){
        String sql = "insert into PlaylistSong values(?,?,?)";
        return jdbcOperations.update(sql,id,pl.getId(),song.getId())>0;
    }
}
