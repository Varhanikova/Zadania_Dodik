package com.example.demo.Services;
import com.example.demo.Repositories.JdbcLoginRepository;
import com.example.demo.Classes.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;


@RestController
public class LoginService {
    private ArrayList<Login> logins =  new ArrayList<>();
    private Login actualUser=null;
    @Autowired
    JdbcLoginRepository jdbcUserRepository ;

    public LoginService(){
    }
    public Login getActualUser(){
        return actualUser;
    }
    public void setActualUser(Login user){
        actualUser=user;
    }

    @PostMapping("prihlasenie/{username}/{password}")//jop
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
    @PostMapping("prihlasenie/pridaj/{username}/{password}/{premium}")//jop
    public String addLogin(@PathVariable String username, @PathVariable String password, @PathVariable(required = false) String premium){
        Login l = new Login(username,password,premium);
       // logins.add(l);
        boolean  podarilo;
        podarilo = jdbcUserRepository.addLogin(l);
        if(Objects.equals(premium, "A")){
            //l.addFee(5);///!!
         podarilo= jdbcUserRepository.addFee(l.getUsername(),5);
        }
        return podarilo ? "Login with username " +username + " added!" : "Login was not added!";
    }
    @GetMapping("prihlasenia")//jop
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
