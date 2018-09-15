package com.comp3004.A1;

import junit.framework.TestCase;

public class CardTest extends TestCase {
	
	// Test: Check all values of a card
	public void testCardValue() {
		System.out.println("\n----- Checking if all values exist -----");
		for (int i = 0; i < 13; i++) {
			Card card = new Card(0, i);
			assertNotNull(card);
			System.out.println(card.cardText());
		}
	}
	
	// Test: Check all suits of a card
	public void testCardSuit() {
		System.out.println("\n----- Checking if all suits exist -----");
		
		// Using String (Upper Case) & int constructor
		Card card1 = new Card("H", 0);
		assertNotNull(card1);
		System.out.println(card1.cardText());
		
		// Using String (Lower Case) & int constructor
		Card card2 = new Card("s", 0);
		assertNotNull(card2);
		System.out.println(card2.cardText());
		
		// Using int & int constructor
		for (int i = 2; i < 4; i++) {
			Card card3 = new Card(i, 0);
			assertNotNull(card3);
			System.out.println(card3.cardText());
		}
	}

}
