package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    public List<Login> getLoginsDB(){
        String sql = "Select * from Login";
        return  jdbcOperations.query(sql,this::mapRow);
    }
    private Login mapRow(ResultSet rs,int rowNum) throws SQLException {
        String username = rs.getString("username");
        String password = rs.getString("password");
        String premium = rs.getString("premium");
        return new Login(username,password,premium);
    }
    public boolean addLogin(Login login){
        String sql = "insert into Login values(?,?,?,0)";
        return jdbcOperations.update(sql,login.getUsername(),login.getPassword(),login.isPremium())>0;
    }
}
