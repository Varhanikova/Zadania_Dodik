package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		SongService songService= new SongService();
		LoginService loginService = new LoginService();
		AdService adService = new AdService();
		PlaylistService playlistService= new PlaylistService();

		adService.setLoginService(loginService);
		adService.setSongService(songService);
		playlistService.setLoginService(loginService);
		playlistService.setAdService(adService);

		//create logins
		loginService.addLogin(new Login("user1","pass",true));
		loginService.addLogin(new Login("user2","pass",false));
		loginService.addLogin(new Login("user3","pass",true));

		//create ads
		Ad ad1 = new Ad("Coca Cola", 0.01, 0.4);
		Ad ad2 = new Ad("Lotus", 0.02, 0.6);
		adService.addAd(ad1);
		adService.addAd(ad2);

		//add songs to every user and play them
		for(Login lg :loginService.getLogins()) {
			loginService.setActualUser(lg);
			lg.addPlayList("moj");
			Playlist pl = playlistService.findPlaylist("moj");
			for (Song sng : songService.getSongs()) {
				pl.addSong(sng);
			}
			playlistService.playSongs("moj");
		}
		System.out.println(adService.sumOfMoney());
		assert (adService.sumOfMoney()>10);

	}

}
