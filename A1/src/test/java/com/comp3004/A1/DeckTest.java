package com.comp3004.A1;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	
	// Test: Checks if a default deck exists and is populated
	public void testDefaultDeck() {
		System.out.println("\n----- Checking default deck -----");
		Deck deck = new Deck();
		assertNotNull(deck);
		System.out.println("Deck containing " + deck.getCardCount() + " cards: ");
		assertEquals(52, deck.getCardCount());
		
		// Go through each card in the deck
		while (deck.hasNextCard()) {
			System.out.println(deck.nextCard().cardText());
		}
	}

	// Test: Checks if a default deck exists and is populated
	public void testShuffledDeck() {
		System.out.println("\n----- Checking shuffled deck -----");
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		assertNotNull(shuffledDeck);
		System.out.println("Deck containing " + shuffledDeck.getCardCount() + " cards: ");
		assertEquals(52, shuffledDeck.getCardCount());
		
		// Go through each card in the deck
		while (shuffledDeck.hasNextCard()) {
			System.out.println(shuffledDeck.nextCard().cardText());
		}
	}
}
