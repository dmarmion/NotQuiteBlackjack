package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.interfaces.BlackEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.BlackEngineCallback;

public class BlackEngineImpl implements BlackEngine {
	
	private Map<String, Player> players;
	private ArrayList<BlackEngineCallback> callbacks;
	private Deque<PlayingCard> cards;
	
	public BlackEngineImpl() {
		players = new HashMap<String, Player>();
		callbacks = new ArrayList<BlackEngineCallback>();
		cards = this.getShuffledHalfDeck();
	}
	
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		if (delay < 0 || delay > 1000) {
			throw new IllegalArgumentException("Delay parameter must be between 0 and 1000.");
		}
		
		int finalScore = dealLoop(player, delay, false);
		
		player.setResult(finalScore);
		
		for (BlackEngineCallback callback : callbacks) {
			callback.result(player, finalScore, this);
		}
		
	}

	public void dealHouse(int delay) throws IllegalArgumentException {
		if (delay < 0) {
			throw new IllegalArgumentException("Delay parameter must be 0 or greater.");
		}
		
		int finalScore = dealLoop(null, delay, true);
		
		for (Player player : players.values()) {
			applyWinLoss(player, finalScore);
		}
		
		for (BlackEngineCallback callback : callbacks) {
			callback.houseResult(finalScore, this);
		}
		
		for (Player player : players.values()) {
			player.resetBet();
		}
		
	}
	
	private int dealLoop(Player player, int delay, boolean houseVersion) {
		int playerScore = 0;
		
		while (playerScore < BUST_LEVEL) {
			// check if the deck is empty; if it is, get a new deck
			if (cards.size() == 0) {
				cards = this.getShuffledHalfDeck();
			}
			
		    // deal a card to the player/house
			PlayingCard dealtCard = cards.pop();
			int potentialScore = playerScore + dealtCard.getScore();
			
			// delay between dealing of cards
			try {
				Thread.sleep(delay);	
			} catch (InterruptedException e) {
				// ignore
			}
			
			// actions to take depend on whether or not the player/house will bust
			if (potentialScore < BUST_LEVEL) {
				playerScore += dealtCard.getScore();
				
				if (houseVersion) {
					for (BlackEngineCallback callback : callbacks) {
						callback.nextHouseCard(dealtCard, this);
					}
				} else {
					for (BlackEngineCallback callback : callbacks) {
						callback.nextCard(player, dealtCard, this);
					}
				}
				
			} else if (potentialScore == BUST_LEVEL) {
				playerScore += dealtCard.getScore();
				int finalScore = playerScore; // to save final score
				playerScore = BUST_LEVEL + 1; // to end the while loop
				
				if (houseVersion) {
					for (BlackEngineCallback callback : callbacks) {
						callback.nextHouseCard(dealtCard, this);
					}
				} else {
					for (BlackEngineCallback callback : callbacks) {
						callback.nextCard(player, dealtCard, this);
					}
				}
				
				return finalScore;

			} else {
				// player/house has busted
				int finalScore = playerScore; // to save final score
				playerScore = BUST_LEVEL + 1; // to end the while loop
				
				if (houseVersion) {
					for (BlackEngineCallback callback : callbacks) {
						callback.houseBustCard(dealtCard, this);
					}
				} else {
					for (BlackEngineCallback callback : callbacks) {
						callback.bustCard(player, dealtCard, this);
					}
				}
				
				return finalScore;
			}
		}
		
		return -1; // loop was somehow escaped
	}
	
	public void applyWinLoss(Player player, int houseResult) {
		// negative for a loss, zero for a draw, positive for a win
		int pointDifference = player.getResult() - houseResult;
		
		if (pointDifference < 0) {
			// loss
			player.setPoints(player.getPoints() - player.getBet());
		} else if (pointDifference > 0) {
			// win
			player.setPoints(player.getPoints() + player.getBet());
		}
		
		// in the case of a draw, nothing is changed
		
	}
	
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}
	
	public Player getPlayer(String id) {
		return players.get(id);
	}
	
	public boolean removePlayer(Player player) {
		if (players.remove(player.getPlayerId()) != null) {
			return true;
		}
		return false;
	}
	
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
	}
	
	public void addGameEngineCallback(BlackEngineCallback blackEngineCallback) {
		callbacks.add(blackEngineCallback);
	}
	
	public boolean removeGameEngineCallback(BlackEngineCallback blackEngineCallback) {
		return callbacks.remove(blackEngineCallback);
	}
	
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(players.values());
	}

	public Deque<PlayingCard> getShuffledHalfDeck() {
		LinkedList<PlayingCard> deck = new LinkedList<PlayingCard>();
		
		// add one of each card to the deck
		for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {			
			for (PlayingCard.Value value : PlayingCard.Value.values()) {
				deck.add(new PlayingCardImpl(suit, value));
			}
		}

		// shuffle the deck
		Collections.shuffle(deck);
		
		return deck;
	}
}
