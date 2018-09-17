package com.comp3004.A1;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;

public class Player {
	// Variables
	private int cards = 0;
	private ArrayList<Card> Hand = new ArrayList<Card>();
	private SimpleIntegerProperty value = new SimpleIntegerProperty(0);
	
	// Gets the players card count
	public int getCardCount() {
		return cards;
	}
	
	// Gets the players hand
	public ArrayList<Card> getHand() {
		return Hand;
	}
	
	// Adds a card to the players hand
	public void Hit(Card card) {
		cards++;
		Hand.add(card);
	}
	
	// Gets the players top first card
	public Card getFirstCard() {
		return Hand.get(0);
	}
	
	// Gets the players nth card
	public Card getCard(int n) {
		return Hand.get(n);
	}
	
	// Gets the sum of the players card values
	public int getScore() {
		int sum = 0;
		int value = 0;
		int count = cards;
		int aces = 0;
		
		// Gets card values from hand
		for (int i = 0; i < count; i++) {
			value = Hand.get(i).cardValue();
			sum = sum + value;
			if (value == 11) {
				aces++;
			}
		}
		
		// Automatic split if over 21
		while (aces > 0 && sum > 21) {
			sum = sum - 10;
			aces--;
		}
		
		return sum;
	}
	
	public boolean hasBlackjack() {
		boolean bj = false;
		int score = Hand.get(0).cardValue() + Hand.get(1).cardValue();
		
		if (score == 21) {
			bj = true;
		} else {
			bj = false;
		}
		
		return bj;
	}
	
	public boolean isBusted() {
		boolean bust = false;
		
		if (this.getScore() > 21) {
			bust = true;
		} else {
			bust = false;
		}
		
		return bust;
	}
	
	public SimpleIntegerProperty scoreProperty() {
		value.set(this.getScore());
        return value;
    }

}
