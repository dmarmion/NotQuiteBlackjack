package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.Player;
import model.viewmodel.PlayerState;
import view.guicomponents.MainFrame;

public class AddPlayerItemListener implements ActionListener {

	private MainFrame mf;

	public AddPlayerItemListener(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Get player details from user via dialog boxes
		String playerID = JOptionPane.showInputDialog(mf, "What is the player ID?", "Add New Player",
				JOptionPane.QUESTION_MESSAGE);
		String playerName = JOptionPane.showInputDialog(mf, "What is the player's name?", "Add New Player",
				JOptionPane.QUESTION_MESSAGE);

		// Parse player points from inputted String
		String rawPoints = JOptionPane.showInputDialog(mf, "What is the player's intial number of points?",
				"Add New Player", JOptionPane.QUESTION_MESSAGE);
		int playerPoints;

		try {
			playerPoints = Integer.parseInt(rawPoints);
		} catch (NumberFormatException nfe) {
			playerPoints = -1;
		}

		// Validate input
		if (playerID != null && playerName != null && !playerName.equals("") && playerPoints > 0) {
			// Player is valid, so add them to the game
			Player newPlayer = new SimplePlayer(playerID, playerName, playerPoints);

			// Update model & extraPlayerInfo
			mf.getExtraPlayerInfo().put(newPlayer, new PlayerState(newPlayer));
			mf.getModel().addPlayer(newPlayer);

			// Update status bar to inform of added player
			mf.updateStatusBar(String.format("Player Added - ID: %s, Name: %s, Points: %d", newPlayer.getPlayerId(),
					newPlayer.getPlayerName(), newPlayer.getPoints()));

			// Add player to combo box in toolbar
			mf.getToolBar().updateComboBox();

			// Add player to CardPanelHolder
			mf.getCardPanelHolder().addPlayerPanel(newPlayer);

			// Add player to summary panel
			mf.getSummaryPanel().updateSummaryPanel();

		} else {
			// Player details entered were not valid, do not create a new player
			JOptionPane.showMessageDialog(mf, "Error: Details entered were invalid - no player was created.",
					"Add New Player", JOptionPane.WARNING_MESSAGE);
		}
	}

}
