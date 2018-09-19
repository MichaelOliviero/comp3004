package com.comp3004.A1;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;

public class Hand {
	// Variables
	private int cards = 0;
	private int aceCounter = 0;
	private boolean bj = false;
	private boolean bust = false;
	private ArrayList<Card> Hand = new ArrayList<Card>();
	private SimpleIntegerProperty value = new SimpleIntegerProperty(0);
	
	// Gets the players card count
	public int getCardCount() {
		return this.cards;
	}
	
	// Gets the players hand
	public ArrayList<Card> getHand() {
		return this.Hand;
	}
	
	// Adds a card to the players hand
	public void Hit(Card card) {
		this.cards++;
		this.Hand.add(card);
		if (card.cardValue() == 11) {
			this.aceCounter++;
		}
	}
	
	// Gets the players nth card
	public Card getCard(int n) {
		return this.Hand.get(n);
	}
	
	// Gets the sum of the players card values
	public int getScore() {
		int sum = 0;
		int value;
		int aces = 0;
		
		// Gets card values from hand
		for (int i = 0; i < cards; i++) {
			value = this.Hand.get(i).cardValue();
			sum = sum + value;
			if (value == 11) {
				aces++;
			}
			value = 0;
		}
		
		while (aces > 0 && sum > 21) {
			sum = sum - 10;
			aces--;
		}
		
		return sum;
	}
	
	public boolean hasBlackjack() {
		int score = this.Hand.get(0).cardValue() + this.Hand.get(1).cardValue();
		
		if (score == 21) {
			this.bj = true;
		} else {
			this.bj = false;
		}
		
		return this.bj;
	}
	
	public boolean isBusted() {
		if (this.getScore() > 21) {
			this.bust = true;
		} else {
			this.bust = false;
		}
		
		return this.bust;
	}
	
	public SimpleIntegerProperty scoreProperty() {
		value.set(this.getScore());
        return value;
    }
	
	public boolean dealersChoice() {
		if (this.getScore() <= 16) {
			return true;
		} else if (this.getScore() == 17 && this.getCardCount() == 2 && this.aceCounter == 1) {
			return true;
		} else {
			return false;
		}
	}

}
