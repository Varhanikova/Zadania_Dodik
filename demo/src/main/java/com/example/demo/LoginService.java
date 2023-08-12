package com.example.demo;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
public class LoginService {
    private ArrayList<Login> logins =  new ArrayList<>();
    private Login actualUser=null;

    public LoginService(){
       logins.add(new Login("user","pass",false));
    }
    public Login getActualUser(){
        return actualUser;
    }
    public void setActualUser(Login user){
        actualUser=user;
    }

    @PostMapping("prihlasenie/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password){
        for(Login login: logins){
            if(login.getUsername().equals(username) && login.getPassword().equals(password)){
                actualUser=login;
                return "Logged as " + username;
            }
        }
        return "Wrong credentials!";
    }
    @PostMapping("prihlasenie/pridaj")
    public String addLogin(@RequestBody Login login){
        logins.add(login);
        return "Login with username " +login.getUsername() + " added!";
    }
    @PostMapping("prihlasenie/pridaj/{username}/{password}/{premium}")
    public String addLogin(@PathVariable String username, @PathVariable String password, @PathVariable(required = false) boolean premium){
        Login l = new Login(username,password,premium);
        logins.add(l);
        if(premium){
            l.addFee(5);
            return "Login with username " +username + " added as premium user!";
        }
        return "Login with username " +username + " added!";
    }
    @GetMapping("prihlasenia")
    public String vypisPrihlasenia(){
        String pom="";
        for(Login log: logins){
            pom+="<p> "+ log.getUsername() + " </p>";
        }
        return pom;
    }
    public ArrayList<Login> getLogins(){
        return logins;
    }



}
