package com.comp3004.A1;

import junit.framework.TestCase;

public class DealerTest extends TestCase {
	
	// Test: Check dealersChoice function
	public void testDealersChoice() {
		Dealer dealer = new Dealer();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		
		// Hand is empty by default
		assertTrue(dealer.getHand().isEmpty());
		assertEquals(0, dealer.getCardCount());
		
		System.out.println("\n----- Checking score then using AI to decide whether to hit or stay -----");
		dealer.Hit(shuffledDeck.nextCard());
		dealer.Hit(shuffledDeck.nextCard());
		while(dealer.dealersChoice()) {
			dealer.Hit(shuffledDeck.nextCard());
		}
		
		System.out.println("Cards in the hand: " + dealer.getCardCount());
		System.out.println("Cards left in the deck: " + shuffledDeck.getCardCount());
		
		for (int y = 0; y < dealer.getHand().size(); y++) {
			System.out.println(dealer.getHand().get(y).cardText());
		}
		
		System.out.println("\nTotal score of cards: " + dealer.getScore());
		
		assertFalse(dealer.getHand().isEmpty());
	}

}
