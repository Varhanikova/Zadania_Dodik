package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		SongService songService= new SongService();
		LoginService loginService = new LoginService();
		MoneyService moneyService = new MoneyService();
		moneyService.setLoginService(loginService);
		moneyService.setSongService(songService);

		//create logins
		loginService.addLogin(new Login("user1","pass",true));
		loginService.addLogin(new Login("user2","pass",false));
		loginService.addLogin(new Login("user3","pass",true));

		//add songs to every user and play them
		for(Login lg :loginService.getLogins()) {
			loginService.setActualUser(lg);
			lg.addPlayList("moj");
			Playlist pl = loginService.findPlaylist("moj");
			for (Song sng : songService.getSongs()) {
				pl.addSong(sng);
			}
			moneyService.playSongs("moj");
		}
		assert (moneyService.sumOfMoney()>10);

	}

}
