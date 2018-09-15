// Source: https://gist.github.com/meagonqz/7739768

package com.comp3004.A1;

import java.util.Random;

public class Deck {
	// Variables
	private int index = 0;
	private Card[] deckArray = new Card[52];
	
	// Constructor
	public Deck() {
		int pos = 0;
		
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 13; y++) {
				Card newCard = new Card(x, y);
				deckArray[pos] = newCard;
				pos++;
			}
		}
	}
	
	// Gets the card count
	public int getCardCount() {
		return (52 - index);
	}
	
	// Randomly shuffles the deck
	public void shuffleDeck() {
		int count = 0;
		index = 0;
		
		while (count < 1000) {
			Random rand = new Random();
			int x = rand.nextInt(52);
			int y = rand.nextInt(52);
			Card stored = deckArray[x];
			deckArray[x] = deckArray[y];
			deckArray[y] = stored;
			count++;
		}
	}
	
	// Checks if there's another card
	public boolean hasNextCard() {
		return (index < 52);
	}
	
	// Goes to the next card in the deck if it exists
	public Card nextCard() {
		if (index < 52) {
			index++;
			return deckArray[index - 1];
		} else {
			return null;
		}
	}
}
