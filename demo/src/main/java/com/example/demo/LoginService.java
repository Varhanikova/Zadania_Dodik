package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;


@RestController
public class LoginService {
    private ArrayList<Login> logins =  new ArrayList<>();
    private Login actualUser=null;
    @Autowired
    JdbcUserRepository jdbcUserRepository ;

    public LoginService(){
//       logins.add(new Login("user","pass",false));
    }
    public Login getActualUser(){
        return actualUser;
    }
    public void setActualUser(Login user){
        actualUser=user;
    }

    @PostMapping("prihlasenie/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password){
        for(Login login: jdbcUserRepository.getLoginsDB()){
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
        return jdbcUserRepository.addLogin(login)? "Login with username " +login.getUsername() + " added!" : "Login was not added!";
    }
    @PostMapping("prihlasenie/pridaj/{username}/{password}/{premium}")
    public String addLogin(@PathVariable String username, @PathVariable String password, @PathVariable(required = false) String premium){
        Login l = new Login(username,password,premium);
        logins.add(l);
        if(Objects.equals(premium, "A")){
            l.addFee(5);
            return "Login with username " +username + " added as premium user!";
        }
        return jdbcUserRepository.addLogin(l)? "Login with username " +username + " added!" : "Login was not added!";
    }
    @GetMapping("prihlasenia")
    public String vypisPrihlasenia(){
        String pom="";
        for(Login log: jdbcUserRepository.getLoginsDB()){
            pom+="<p> "+ log.getUsername() + " </p>";
        }
        return pom;
    }
    public ArrayList<Login> getLogins(){
        return logins;
    }



}
