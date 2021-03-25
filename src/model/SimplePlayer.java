package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String id;
	private String playerName;
	private int points;
	private int bet;
	private int result;

	public SimplePlayer(String id, String playerName, int initialPoints) {
		this.id = id;
		this.playerName = playerName;
		this.points = initialPoints;
		
		this.bet = 0;
		this.result = 0;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public String getPlayerId() {
		return this.id;
	}
	
	public boolean setBet(int bet) {
		if (bet > 0 && bet <= this.points) {
			this.bet = bet;
			return true;
		}
		
		return false;
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public void resetBet() {
		this.bet = 0;
	}
	
	public int getResult() {
		return this.result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
	public boolean equals(Player player) {
		if (player == null) {
			return false;
		}
		
		if (player.getPlayerId().equals(this.getPlayerId())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean equals(Object player) {
		if (player instanceof SimplePlayer) {
			Player tempPlayer = (SimplePlayer) player;
			return this.equals(tempPlayer);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	public int compareTo(Player player) {
		String otherId = player.getPlayerId();
		return id.compareTo(otherId);
	}
	
	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d",
				this.id, this.playerName, this.bet, this.points, this.result);
	}
}
