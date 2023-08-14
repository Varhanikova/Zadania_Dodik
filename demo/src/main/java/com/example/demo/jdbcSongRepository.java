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
         float fee = rs.getFloat("fee");
         return new Song(id,autor,name,fee);
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
         return jdbcOperations.update(sql,song.getId(),song.getAutor(),song.getName(),0)>0;
     }
     public int getPocetSongs(){
         String sql = "select count(*) pocet from Song";
         return jdbcOperations.query(sql,this::mapInt).get(0);
     }
     public Song getSongById(int id){
         String sql = "select * from Song where id = ?";
         return jdbcOperations.query(sql,this::mapRow,id).get(0);
     }
     private float mapFee(ResultSet rs,int index) throws SQLException {
         return rs.getFloat("fee");
     }
     public boolean addFee(double pr, int id_song){
         String sql = "select fee from Song where id =?";
         float fee =  jdbcOperations.query(sql,this::mapFee,id_song).get(0);
         sql = "update Song set fee = ? where id = ?";
        return jdbcOperations.update(sql,fee+pr,id_song)>0;
     }
}
