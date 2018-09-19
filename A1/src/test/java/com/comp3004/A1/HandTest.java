package com.comp3004.A1;

import junit.framework.TestCase;
import java.util.Random;

public class HandTest extends TestCase {
	
	// Test: Checks players default hand (empty) & card count
	public void testEmptyHand() {
		Hand p1 = new Hand();
		assertTrue(p1.getHand().isEmpty());
		assertEquals(0, p1.getCardCount());
	}
	
	// Test: Check hitting (adding cards) & card count
	public void testHit() {
		Hand p1 = new Hand();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		
		// Hand is empty by default
		assertTrue(p1.getHand().isEmpty());
		assertEquals(0, p1.getCardCount());
		
		// Hand is no longer empty - has 1 random card in it
		p1.Hit(shuffledDeck.nextCard());
		assertFalse(p1.getHand().isEmpty());
		assertEquals(1, p1.getCardCount());
	}
	
	// Test: Check multiple hits (adding cards) & card count
	public void testMultipleHits() {
		Hand p1 = new Hand();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		Random rand = new Random();
		int randomNum = rand.nextInt((8 - 2) + 1);
		
		// Hand is empty by default
		assertTrue(p1.getHand().isEmpty());
		assertEquals(0, p1.getCardCount());
		
		// Hand is no longer empty - has random amount of cards in it
		p1.Hit(shuffledDeck.nextCard());
		for (int x = 0; x < randomNum; x++) {
			p1.Hit(shuffledDeck.nextCard());
		}
		
		assertFalse(p1.getHand().isEmpty());
		assertEquals(randomNum + 1, p1.getCardCount());
	}
	
	// Test: Check Score of a random hand
	public void testGetScore() {
		Hand p1 = new Hand();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		Random rand = new Random();
		int randomNum = rand.nextInt((5 - 2) + 1);
		
		// Hand is empty by default
		assertTrue(p1.getHand().isEmpty());
		assertEquals(0, p1.getCardCount());
		
		// Hand is no longer empty - has random amount of cards in it
		p1.Hit(shuffledDeck.nextCard());
		for (int x = 0; x < randomNum; x++) {
			p1.Hit(shuffledDeck.nextCard());
		}
		
		assertFalse(p1.getHand().isEmpty());
		assertEquals(randomNum + 1, p1.getCardCount());
	}
	
	// Test: Check dealersChoice function when <= 16
	public void testDealersUnder16() {
		// Give dealer a 16
		Hand dealer1 = new Hand();
		dealer1.Hit(new Card(0, 10)); // 10
		dealer1.Hit(new Card(0, 5)); // 6
		assertTrue(dealer1.dealersChoice()); // Dealer will hit
		
		// Give dealer a 14
		Hand dealer2 = new Hand();
		dealer2.Hit(new Card(0, 10)); // 10
		dealer2.Hit(new Card(0, 3)); // 4
		assertTrue(dealer2.dealersChoice()); // Dealer will hit
		
		// Give dealer a 12
		Hand dealer3 = new Hand();
		dealer3.Hit(new Card(0, 10)); // 10
		dealer3.Hit(new Card(0, 1)); // 2
		assertTrue(dealer3.dealersChoice()); // Dealer will hit
		
		// Give dealer a 6
		Hand dealer4 = new Hand();
		dealer4.Hit(new Card(0, 2));
		dealer4.Hit(new Card(0, 2));
		assertTrue(dealer4.dealersChoice()); // Dealer will hit
	}

	// Test: Check dealersChoice function when soft 17
	public void testDealersSoft17() {
		// Give dealer a soft 17
		Hand dealer1 = new Hand();
		dealer1.Hit(new Card(0, 5)); // 6
		dealer1.Hit(new Card(0, 0)); // Ace
		assertTrue(dealer1.dealersChoice()); // Dealer will hit
	}
}
