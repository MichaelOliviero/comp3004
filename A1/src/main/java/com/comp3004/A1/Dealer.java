package com.comp3004.A1;

import java.util.ArrayList;

public class Dealer extends Player{
	// Variables
	@SuppressWarnings("unused")
	private int cards = 0;
	@SuppressWarnings("unused")
	private ArrayList<Card> Hand = new ArrayList<Card>();
	
	public boolean dealersChoice() {
		if (this.getScore() <= 16) {
			return true;
		} else if (this.getScore() <= 17) {
			return true;
		} else {
			return false;
		}
	}

}
