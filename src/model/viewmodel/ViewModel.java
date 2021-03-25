package model.viewmodel;

import java.util.HashMap;
import java.util.Map;

import model.interfaces.Player;

public class ViewModel {
	
	private Map<Player, PlayerState> extraPlayerInfo;

	public ViewModel() {
		extraPlayerInfo = new HashMap<Player, PlayerState>();
	}
	
	/**
	 *  NOTE: I previously had the ViewModel implemented as a HashMap stored as an attribute of the MainFrame class.
	 *  Several methods in this class mirror the HashMap methods I used previously to minimise code changes when I switched the
	 *  implementation to this ViewModel class.
	 */
	
	public PlayerState get(Player p) {
		return extraPlayerInfo.get(p);
	}
	
	public void put(Player p, PlayerState ps) {
		extraPlayerInfo.put(p, ps);
	}
	
	public void remove(Player p) {
		extraPlayerInfo.remove(p);
	}
	
	public Map<Player, PlayerState> getExtraPlayerInfo() {
		return this.extraPlayerInfo;
	}
	
}
