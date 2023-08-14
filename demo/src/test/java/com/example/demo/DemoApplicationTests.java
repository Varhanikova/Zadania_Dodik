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
		playlistService.setSongsService(songService);

		//create logins
		loginService.addLogin(new Login("user1","pass","A"));
		loginService.addLogin(new Login("user2","pass","N"));
		loginService.addLogin(new Login("user3","pass","A"));

		//create ads
		Ad ad1 = new Ad("Microsoft", 0.01, 0.04);
		Ad ad2 = new Ad("Freia", 0.02, 0.06);
		adService.addAd(ad1);
		adService.addAd(ad2);

		//add songs to every user and play them
		int a =0;
		for(Login lg :loginService.getLogins()) {
			loginService.setActualUser(lg);
			playlistService.addPlayList(a,"moj");
			Playlist pl = playlistService.findPlaylist("moj");
		//	playlistService.fillPlaylistWithAll("moj");
			playlistService.playSongsTest("moj");
			a++;
		}
		System.out.println(adService.sumOfProfit());
		assert (adService.sumOfProfit()>13.29 && adService.sumOfProfit() < 13.31);
		//<p> Ad " + ad.getSponzor() + " has profit: " + ad.getProfit()*ad.getUsed() + " </p>

		String ads = adService.listAdsWithProfit();
		assert(ads.equals("<p> Ad Coca Cola has profit: 1.0 </p><p> Ad Lotus has profit: 2.0 </p><p> Ad Microsoft has profit: 0.1 </p><p> Ad Freia has profit: 0.2 </p>"));
	}

}
