package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class jdbcAdRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    private Ad mapRow(ResultSet rs, int index) throws SQLException {
        String sponzor = rs.getString("sponzor");
        float profit = rs.getFloat("profit");
        double probability = rs.getDouble("probability");
        int used = rs.getInt("used");
        return new Ad(sponzor,profit,probability,used);
    }
    public List<Ad> getAds(){
        String sql = "select * from Ad";
        return jdbcOperations.query(sql,this::mapRow);
    }
    public boolean addAd(Ad ad){
        String sql = "insert into Ad values(?,?,?)";
        return jdbcOperations.update(sql,ad.getSponzor(),ad.getProfit(),ad.getProbability())>0;
    }
    private int mapUsed(ResultSet rs, int index) throws SQLException {
        return rs.getInt("used");
    }
    public boolean addUsed(String id){
        String sql = "select used from Ad where sponzor =?";
        int used =jdbcOperations.query(sql,this::mapUsed,id).get(0);
        sql = "update Ad set used = ? where sponzor=?";
        return jdbcOperations.update(sql,used+1,id)>0;
    }

}
