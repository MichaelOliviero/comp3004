package com.comp3004.A1;

import junit.framework.TestCase;

public class BlackjackMainTest extends TestCase {
		
	public void testGameScenarios() {
		BlackjackMain game = new BlackjackMain();
		
		Hand player = new Hand();
		Hand dealer = new Hand();
		player.Hit(new Card(0, 0)); player.Hit(new Card(3, 10)); // Blackjack
		dealer.Hit(new Card(0, 10)); dealer.Hit(new Card(1, 10)); // 20
		game.continueGame(dealer, player); // Player Wins
		System.out.println("-----------------------------------------------------");
		player = new Hand();
		dealer = new Hand();
		player.Hit(new Card(0, 10)); player.Hit(new Card(1, 10)); // 20
		dealer.Hit(new Card(0, 0)); dealer.Hit(new Card(3, 10)); // Blackjack
		game.continueGame(dealer, player); // Dealer Wins
		System.out.println("-----------------------------------------------------");
		player = new Hand();
		dealer = new Hand();
		player.Hit(new Card(0, 0)); player.Hit(new Card(3, 10)); // Blackjack
		dealer.Hit(new Card(0, 0)); dealer.Hit(new Card(3, 10)); // Blackjack
		game.continueGame(dealer, player); // Dealer Wins
		System.out.println("-----------------------------------------------------");
		player = new Hand();
		dealer = new Hand();
		player.Hit(new Card(0, 10)); player.Hit(new Card(3, 8)); // 19
		dealer.Hit(new Card(0, 4)); dealer.Hit(new Card(3, 10)); // 15
		game.continueGame(dealer, player); // Player Wins
		System.out.println("-----------------------------------------------------");
		player = new Hand();
		dealer = new Hand();
		player.Hit(new Card(0, 4)); player.Hit(new Card(3, 10)); // 15
		dealer.Hit(new Card(0, 10)); dealer.Hit(new Card(3, 8)); // 19
		game.continueGame(dealer, player); // Dealer Wins
		System.out.println("-----------------------------------------------------");
	}

}
