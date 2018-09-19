package com.comp3004.A1;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	
	// Test: Checks if a default deck exists and is populated
	public void testDefaultDeck() {
		Deck deck = new Deck();
		assertNotNull(deck);
		assertEquals(52, deck.getCardCount());
	}

	// Test: Checks if a shuffled deck exists and is populated
	public void testShuffledDeck() {
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		assertNotNull(shuffledDeck);
		assertEquals(52, shuffledDeck.getCardCount());
	}
}
