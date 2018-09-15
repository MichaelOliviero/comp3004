package com.comp3004.A1;

import java.util.ArrayList;

public class Dealer extends Player{
	// Variables
	private int cards = 0;
	private boolean ace = false;
	private ArrayList<Card> Hand = new ArrayList<Card>();
	
	// Gets the sum of the dealers card values
	@Override
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
				ace = true;
			}
		}
		
		// Automatic split if over 21
		while (aces > 0 && sum > 21) {
			sum = sum - 10;
			aces--;
			if (aces == 0) {
				ace = false;
			}
		}
		
		return sum;
	}
	
	public boolean dealersChoice() {
		if (this.getScore() <= 16) {
			return true;
		} else if (this.getScore() <= 17 && ace) {
			return true;
		} else {
			return false;
		}
	}

}
