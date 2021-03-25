package view.guicomponents;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.CancelBetListener;
import controller.PlaceBetListener;
import controller.PlayerDealButtonListener;
import controller.PlayerSelectedListener;
import model.interfaces.Player;
import model.viewmodel.PlayerState;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	
	private MainFrame mf;
	JButton placeBet;
	JButton cancelBet;
	JButton dealButton;
	private JComboBox<String> playerSelect;

	public ToolBar(MainFrame mf) {
		this.mf = mf;
		
		setFloatable(false);
		setLayout(new GridLayout(1, 4, 15, 0));

		// Place Bet Button
		placeBet = new JButton("Place Bet");
		placeBet.setEnabled(false);
		placeBet.addActionListener(new PlaceBetListener(mf));
		
		// Cancel Bet Button
		cancelBet = new JButton("Cancel Bet");
		cancelBet.setEnabled(false);
		cancelBet.addActionListener(new CancelBetListener(mf));
		
		// Deal Button
		dealButton = new JButton("Deal");
		dealButton.setEnabled(false);
		dealButton.addActionListener(new PlayerDealButtonListener(mf));
		
		// ComnoBox for Player Selection
		playerSelect = new JComboBox<String>();
		updateComboBox();
		playerSelect.addActionListener(new PlayerSelectedListener(mf));

		add(placeBet);
		add(cancelBet);
		add(dealButton);
		add(playerSelect);
	}
	
	/**
	 * Returns the Player corresponding to the current selection in the JComboBox
	 * @return the currently selected player, or null if the player is not found (e.g. the House is selected)
	 */
	public Player getSelectedPlayer() {
		String currSelection = playerSelect.getSelectedItem().toString();
		Player currPlayer = null;
		
		for (Player player : mf.getAllPlayers()) {
			if (player.getPlayerName().equals(currSelection)) {
				String currPlayerID = player.getPlayerId();
				currPlayer = mf.getModel().getPlayer(currPlayerID);
			}
		}
		
		return currPlayer;
	}
	
	public void playerUpdated() {
		Player player = getSelectedPlayer();
		
		if (player != null) {
			PlayerState playerInfo = mf.getExtraPlayerInfo().get(player);
			
			if (playerInfo.getHasBet()) {
				// The player has placed a bet
				placeBet.setEnabled(false);
				
				if (playerInfo.getHasDealt()) {
					// The player has bet and dealt
					cancelBet.setEnabled(false);
					dealButton.setEnabled(false);
				} else {
					// The player has bet, but not been dealt their cards yet
					cancelBet.setEnabled(true);
					dealButton.setEnabled(true);
				}
				
			} else {
				// The player hasn't placed a bet yet
				placeBet.setEnabled(true);
				cancelBet.setEnabled(false);
				dealButton.setEnabled(false); // As per game rules: "A player cannot deal if they have not placed a bet"
			}

		} else {
			// The house is currently selected
			placeBet.setEnabled(false);
			cancelBet.setEnabled(false);
			dealButton.setEnabled(false);
		}
	}
	
	public void setSelectedPlayer(String playerName) {
		playerSelect.setSelectedItem(playerName);
	}

	public void updateComboBox() {
		playerSelect.removeAllItems();
		playerSelect.addItem("House");

		for (Player player : mf.getAllPlayers()) {
			playerSelect.addItem(player.getPlayerName());
		}
	}
}
