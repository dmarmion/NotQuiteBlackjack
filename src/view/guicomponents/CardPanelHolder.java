package view.guicomponents;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class CardPanelHolder extends JPanel {
	
	private MainFrame mf;
	private Map<String, CardPanel> playerPanels;
	private CardLayout layout = new CardLayout();

	public CardPanelHolder(MainFrame mf) {
		setLayout(layout);
		
		// ensure the card panel always takes up at least 2/3rds of the MainSplitPane
		setMinimumSize(new Dimension(0, ((int) twoThirds(mf.getInitialHeight() - 90))));
		setPreferredSize(new Dimension(mf.getInitialWidth(), ((int) twoThirds(mf.getInitialHeight() - 90))));
		
		this.mf = mf;
		playerPanels = new HashMap<String, CardPanel>();
		
		// Create the house CardPanel & add it to the CardPanelHolder and the Map of panels
		CardPanel housePanel = new CardPanel(mf);
		playerPanels.put("House", housePanel);
		add(housePanel, "House");
		
		// Add a panel per player already in the game
		for (Player player : mf.getAllPlayers()) {
			addPlayerPanel(player);
		}
	}
	
	/**
	 * Helper method to return 2/3rds of a number, a calculation used a lot when enforcing minimum panel sizes in this class.
	 * @param number the number we want to know 2/3rds of
	 * @return 2/3rds of number
	 */
	private double twoThirds(int number) {
		return ((double) number) * (2.0/3);
	}
	
	// add a player
	public void addPlayerPanel(Player player) {
		CardPanel newCP = new CardPanel(mf);
		playerPanels.put(player.getPlayerId(), newCP);
		add(newCP, player.getPlayerId());		
	}
	
	// remove a player
	public void removePlayerPanel(Player player) {
		remove(playerPanels.get(player.getPlayerId()));
		playerPanels.remove(player.getPlayerId());
	}
	
	// show a player
	public void switchPlayerPanel() {
		Player player = mf.getToolBar().getSelectedPlayer();
		
		if (player != null) {
			// Player exists in the GameEngine
			layout.show(this, player.getPlayerId());
			
		} else {
			// House selected, or player selected is not in the GameEngine
			layout.show(this, "House");
		}
		
	}
	
	public void drawCard(Player player, PlayingCard card) {
		CardPanel targetPanel = playerPanels.get(player.getPlayerId());
		targetPanel.drawCard(card);
	}
	
	// Overloaded version of drawCard(Player player, PlayingCard card), used for house calls to drawCard()
	public void drawCard(PlayingCard card) {
		CardPanel targetPanel = playerPanels.get("House");
		targetPanel.drawCard(card);
	}
	
	// Clear all card panels (for the start of a new round)
	public void clearAllCardPanels() {
		for (CardPanel panel : playerPanels.values()) {
			panel.resetPanel();
		}
	}

}
