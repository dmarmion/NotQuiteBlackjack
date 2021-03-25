package model.viewmodel;

import model.interfaces.Player;

public class PlayerState {

	private boolean hasBet;
	private boolean hasDealt;
	private String winLoss;
	
	public PlayerState(Player player) {
		
		if (player.getBet() == 0) {
			this.hasBet = false;
		} else {
			this.hasBet = true;
		}
		
		this.hasDealt = false;
		this.winLoss = "Yet to Play";
	}
	
	public boolean getHasBet() {
		return this.hasBet;
	}
	
	public void setHasBet(boolean hasBet) {
		this.hasBet = hasBet;
	}
	
	public boolean getHasDealt() {
		return this.hasDealt;
	}

	public void setHasDealt(boolean hasDealt) {
		this.hasDealt = hasDealt;
	}

	public String getWinLoss() {
		return winLoss;
	}

	public void setWinLoss(String winLoss) {
		this.winLoss = winLoss;
	}
	
}
