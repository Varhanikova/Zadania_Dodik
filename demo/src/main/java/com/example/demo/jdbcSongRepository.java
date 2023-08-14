package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class jdbcSongRepository {
     @Autowired
     private JdbcOperations jdbcOperations;

     private Song mapRow(ResultSet rs, int row) throws SQLException {
         int id = rs.getInt("id");
         String name = rs.getString("name");
         String autor = rs.getString("autor");
         return new Song(id,autor,name);
     }
     private int mapInt(ResultSet rs, int row) throws SQLException {
         return rs.getInt("pocet");
     }
     public List<Song> getSongs() {
         String sql = "select * from Song";
         return jdbcOperations.query(sql,this::mapRow);
     }
     public boolean addSong(Song song){
         String sql = "insert into Song values(?,?,?,?)";
         return jdbcOperations.update(sql,song.getId(),song.getAutor(),song.getName())>0;
     }
     public int getPocetSongs(){
         String sql = "select count(*) pocet from Song";
         return jdbcOperations.query(sql,this::mapInt).get(0);
     }
}
