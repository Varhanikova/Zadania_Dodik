package com.example.demo.Repositories;

import com.example.demo.Classes.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcLoginRepository {
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
        double fee = rs.getDouble("fee");
        return new Login(username,password,premium,fee);
    }
    public boolean addLogin(Login login){
        String sql = "insert into Login values(?,?,?,0)";
        return jdbcOperations.update(sql,login.getUsername(),login.getPassword(),login.isPremium())>0;
    }
    private float mapFee(ResultSet rs, int index) throws SQLException {
        return rs.getFloat("fee");
    }

    public boolean addFee(String user, double fee){
        String sql = "select fee from Login where username=?";
        float fe = jdbcOperations.query(sql, this::mapFee,user).get(0);
        sql = "update Login set fee =? where username =?";
       return jdbcOperations.update(sql, fe+fee,user)>0;
    }

}
