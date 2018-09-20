package com.comp3004.A1;

import junit.framework.TestCase;

public class CardTest extends TestCase {
	
	// Test: Check values of cards using the 2 int constructor
	public void testCardValueIntConstructor() {
		// Values: 2 to 10
		for (int i = 1; i < 10; i++) {
			Card card = new Card(0, i);
			assertTrue(card.cardValue() == (i+1));
		}
		
		// Values: Ace (1 & 11), Jack, Queen, King
		Card cardSoloAce = new Card(0, 0);
		assertEquals(11, cardSoloAce.cardValue());
		
		Hand player1 = new Hand();
		player1.Hit(new Card(0, 0)); // Hit first Ace
		player1.Hit(new Card(1, 0)); // Hit second Ace
		assertEquals(12, player1.getScore()); // Score -> 11 + 1 = 12
		
		Hand player2 = new Hand();
		player2.Hit(new Card(0, 0)); // Hit first Ace
		player2.Hit(new Card(1, 0)); // Hit second Ace
		player2.Hit(new Card(1, 13)); // Hit a King
		assertEquals(12, player2.getScore()); // Score -> 10 + 1 + 1 = 12
		
		Card cardJack = new Card(0, 11);
		assertEquals(10, cardJack.cardValue());
		
		Card cardQueen = new Card(0, 12);
		assertEquals(10, cardQueen.cardValue());
		
		Card cardKing = new Card(0, 13);
		assertEquals(10, cardKing.cardValue());
	}
	
	// Test: Check values of cards using the 2 String constructor
	public void testCardValueStringConstructor() {
		
		// Values: Ace (1 & 11), 2 to 10, Jack, Queen, King
		Card cardSoloAce = new Card("H", "A");
		assertEquals(11, cardSoloAce.cardValue());
		
		Hand player1 = new Hand();
		player1.Hit(new Card("H", "A")); // Hit first Ace
		player1.Hit(new Card("s", "A")); // Hit second Ace
		assertEquals(12, player1.getScore()); // Score -> 11 + 1 = 12
		
		Hand player2 = new Hand();
		player2.Hit(new Card("H", "A")); // Hit first Ace
		player2.Hit(new Card("s", "A")); // Hit second Ace
		player2.Hit(new Card("s", "k")); // Hit a King
		assertEquals(12, player2.getScore()); // Score -> 10 + 1 + 1 = 12
		
		Card cardTwo = new Card("H", "2");
		assertEquals(2, cardTwo.cardValue());
		
		Card cardThree = new Card("H", "3");
		assertEquals(3, cardThree.cardValue());
		
		Card cardFour = new Card("H", "4");
		assertEquals(4, cardFour.cardValue());
		
		Card cardFive = new Card("H", "5");
		assertEquals(5, cardFive.cardValue());
		
		Card cardSix = new Card("H", "6");
		assertEquals(6, cardSix.cardValue());
		
		Card cardSeven = new Card("H", "7");
		assertEquals(7, cardSeven.cardValue());
		
		Card cardEight = new Card("H", "8");
		assertEquals(8, cardEight.cardValue());
		
		Card cardNine = new Card("H", "9");
		assertEquals(9, cardNine.cardValue());
		
		Card cardTen = new Card("H", "10");
		assertEquals(10, cardTen.cardValue());
		
		Card cardJack = new Card("H", "J");
		assertEquals(10, cardJack.cardValue());
		
		Card cardQueen = new Card("H", "Q");
		assertEquals(10, cardQueen.cardValue());
		
		Card cardKing = new Card("H", "K");
		assertEquals(10, cardKing.cardValue());
	}
	
	// Test: Check values of cards using the 2 String constructor
	public void testCardValueSingleStringConstructor() {
		
		// Values: Ace (1 & 11), 2 to 10, Jack, Queen, King
		Card cardSoloAce = new Card("HA");
		assertEquals(11, cardSoloAce.cardValue());
		
		Hand player1 = new Hand();
		player1.Hit(new Card("HA")); // Hit first Ace
		player1.Hit(new Card("sA")); // Hit second Ace
		assertEquals(12, player1.getScore()); // Score -> 11 + 1 = 12
		
		Hand player2 = new Hand();
		player2.Hit(new Card("HA")); // Hit first Ace
		player2.Hit(new Card("sA")); // Hit second Ace
		player2.Hit(new Card("sk")); // Hit a King
		assertEquals(12, player2.getScore()); // Score -> 10 + 1 + 1 = 12
		
		Card cardTwo = new Card("H2");
		assertEquals(2, cardTwo.cardValue());
		
		Card cardTen = new Card("H10");
		assertEquals(10, cardTen.cardValue());
	}
}
