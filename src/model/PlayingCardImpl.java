package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {
	
	private Suit suit;
	private Value value;
	
	public PlayingCardImpl(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit() {
		return this.suit;
	}

	public Value getValue() {
		return this.value;
	}

	public int getScore() {
		int score;
		if (this.value == Value.ACE) {
			score = 11;
		} else if (this.value == Value.EIGHT) {
			score = 8;
		} else if (this.value == Value.NINE) {
			score = 9;
		} else {
			// Card is 10 or a face card
			score = 10;
		}

		return score;
	}

	public String toString() {
		return String.format("Suit: %s, Value: %s, Score: %d",
				correctCase(this.suit.toString()), correctCase(this.value.toString()), this.getScore());
	}
	
	private String correctCase(String inpString) {	
		// First character uppercase, plus the rest of the string in lowercase
		return inpString.substring(0, 1).toUpperCase() + inpString.substring(1).toLowerCase();
	}
	
	public boolean equals(PlayingCard card) {
		if (card == null) {
			return false;
		}
		
		if (card.getSuit() != this.suit || card.getValue() != this.value) {
			return false;
		}
		
		return true;
	}
	
	public boolean equals(Object card) {
		if (card instanceof PlayingCard) {
			PlayingCard tempCard = (PlayingCard) card; // modified from A1 submission; now casts to PlayingCard, not PlayingCardImpl
			return this.equals(tempCard);
		}
		
		return false;
	}
	
	public int hashCode() {
		return suit.hashCode() + value.hashCode();
	}

}
