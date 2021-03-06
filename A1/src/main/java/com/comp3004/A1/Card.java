// Source: https://gist.github.com/meagonqz/7739768

package com.comp3004.A1;

public class Card {
	// Variables
	private int suit;
	private int value;
	private static final String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
	private static final String[] values = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	// Constructor (if inputting 1 String)
	public Card (String card) {
		this(("" + card.charAt(0)), card.substring(1));
	}
	
	// Constructor (if inputting 2 ints)
	public Card (int suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	// Constructor (if inputting 2 Strings)
	public Card (String suit, String value) {
		suit = suit.toUpperCase();
		if (suit.equals("H")) {
			this.suit = 0;
		} else if (suit.equals("S")) {
			this.suit = 1;
		} else if (suit.equals("C")) {
			this.suit = 2;
		} else if (suit.equals("D")) {
			this.suit = 3;
		}
		
		value = value.toUpperCase();
		if (value.equals("A")) {
			this.value = 0;
		} else if (value.equals("J")) {
			this.value = 11;
		} else if (value.equals("Q")) {
			this.value = 12;
		} else if (value.equals("K")) {
			this.value = 13;
		} else {
			this.value = Integer.parseInt(value)-1;
		}
	}
	
	// Gets the value of the card
	public int cardValue() {
		if (this.value == 0) {
			return 11;
		} else if (this.value < 10) {
			return (value + 1);
		} else if (this.value >= 10) {
			return 10;
		} else {
			return 0;
		}
	}
	
	// Gets the suit and value of the card
	public String cardText() {
		return "\t Card: " + values[this.value] + " of " + suits[this.suit];
	}
}
