package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class jdbcPlaylistRepository {
    @Autowired
    private JdbcOperations jdbcOperations;
    @Autowired
    private JdbcLoginRepository jdbcLoginRepository;

    private Playlist mapRow(ResultSet rs, int index) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String username = rs.getString("username");
        for(Login log: jdbcLoginRepository.getLoginsDB()){
            if(log.getUsername().equals(username)){
                return new Playlist(id,name,log);
            }
        }
        return null;
    }
    private int mapPocet(ResultSet rs, int index) throws SQLException {
        return rs.getInt("pocet");
    }
    public List<Playlist> getPlaylists(){
        String sql = "select * from Playlist";
        return jdbcOperations.query(sql,this::mapRow);
    }
    public int getPocetPlaylistov(String username){
        String sql = "select count(*) pocet from Playlist where username=?";
        return jdbcOperations.query(sql,this::mapPocet,username).get(0);
    }
    public boolean addPlaylist(Playlist pl){
        String sql = "insert into Playlist values(?,?,?)";
        return jdbcOperations.update(sql,pl.getId(),pl.getNazov(),pl.getLogin().getUsername())>0;
    }
    public List<Playlist> getPlaylistPodlaUsera(String username){
        String sql = "select * from Playlist where username=?";
        return jdbcOperations.query(sql,this::mapRow,username);
    }
    public Playlist getPlaylistPodlaId(int id){
        String sql = "select * from Playlist where id=?";
        return jdbcOperations.query(sql,this::mapRow,id).get(0);
    }

}
