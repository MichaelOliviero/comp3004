package com.comp3004.A1;

import junit.framework.TestCase;
import java.util.Random;

public class PlayerTest extends TestCase {
	
	// Test: Checks players default hand (empty) & card count
	public void testDefaultHand() {
		Player p1 = new Player();
		
		System.out.println("\n----- Checking empty hand -----");
		System.out.println(p1.getHand());
		assertTrue(p1.getHand().isEmpty());
		
		System.out.println(p1.getCardCount());
		assertEquals(0, p1.getCardCount());
	}
	
	// Test: Check hitting (adding cards) & card count
	public void testHit() {
		Player p1 = new Player();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		
		// Hand is empty by default
		assertTrue(p1.getHand().isEmpty());
		assertEquals(0, p1.getCardCount());
		
		System.out.println("\n----- Checking hand after hit with shuffled deck -----");
		p1.Hit(shuffledDeck.nextCard());
		System.out.println("Cards in the hand: " + p1.getCardCount());
		System.out.println("Cards left in the deck: " + shuffledDeck.getCardCount());
		System.out.println(p1.getHand().get(0).cardText());
		assertFalse(p1.getHand().isEmpty());
		assertEquals(1, p1.getCardCount());
	}
	
	// Test: Check multiple hits (adding cards) & card count
	public void testMultipleHits() {
		Player p1 = new Player();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		Random rand = new Random();
		int randomNum = rand.nextInt((8 - 2) + 1);
		
		// Hand is empty by default
		assertTrue(p1.getHand().isEmpty());
		assertEquals(0, p1.getCardCount());
		
		System.out.println("\n----- Checking hand after random amount of hits with shuffled deck -----");
		p1.Hit(shuffledDeck.nextCard());
		for (int x = 0; x < randomNum; x++) {
			p1.Hit(shuffledDeck.nextCard());
		}
		
		System.out.println("Cards in the hand: " + p1.getCardCount());
		System.out.println("Cards left in the deck: " + shuffledDeck.getCardCount());
		
		for (int y = 0; y < p1.getHand().size(); y++) {
			System.out.println(p1.getHand().get(y).cardText());
		}
		
		assertFalse(p1.getHand().isEmpty());
		assertEquals(randomNum + 1, p1.getCardCount());
	}
	
	// Test: Check Score of a random hand
	public void testGetScore() {
		Player p1 = new Player();
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffleDeck();
		Random rand = new Random();
		int randomNum = rand.nextInt((5 - 2) + 1);
		
		// Hand is empty by default
		assertTrue(p1.getHand().isEmpty());
		assertEquals(0, p1.getCardCount());
		
		System.out.println("\n----- Checking score after random amount of hits with shuffled deck -----");
		p1.Hit(shuffledDeck.nextCard());
		for (int x = 0; x < randomNum; x++) {
			p1.Hit(shuffledDeck.nextCard());
		}
		
		System.out.println("Cards in the hand: " + p1.getCardCount());
		System.out.println("Cards left in the deck: " + shuffledDeck.getCardCount());
		
		for (int y = 0; y < p1.getHand().size(); y++) {
			System.out.println(p1.getHand().get(y).cardText());
		}
		
		System.out.println("\nTotal score of cards: " + p1.getScore());
		
		assertFalse(p1.getHand().isEmpty());
		assertEquals(randomNum + 1, p1.getCardCount());
	}
}
