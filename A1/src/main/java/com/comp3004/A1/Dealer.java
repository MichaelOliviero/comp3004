package com.comp3004.A1;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;

public class Dealer extends Hand {
	/*
	// Variables
	private int cards = 0;	
	private int aceCounter = 0;
	@SuppressWarnings("unused")
	private boolean bj = false;
	@SuppressWarnings("unused")
	private boolean bust = false;
	private ArrayList<Card> Hand = new ArrayList<Card>();
	@SuppressWarnings("unused")
	private SimpleIntegerProperty value = new SimpleIntegerProperty(0);
	
	@Override
	public int getScore() {
		int sum = 0;
		int value = 0;
		
		// Gets card values from hand
		for (int i = 0; i < cards; i++) {
			value = this.Hand.get(i).cardValue();
			sum = sum + value;
			if (value == 11) {
				aceCounter++;
			}
		}
		
		System.out.println("Dealer Sum: " + sum);
		return sum;
	}
	
	public boolean dealersChoice() {
		if (this.getScore() <= 16) {
			return true;
		} else if (this.getScore() == 17 && this.getCardCount() == 2 && aceCounter == 1) {
			return true;
		} else {
			return false;
		}
	}*/

}
