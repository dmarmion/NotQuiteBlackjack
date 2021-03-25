package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.Player;
import view.guicomponents.MainFrame;

public class PlaceBetListener implements ActionListener {
	
	private MainFrame mf;

	public PlaceBetListener(MainFrame mf) {
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Parse bet from inputted String
		String rawBet = JOptionPane.showInputDialog(mf, "What should the player's bet be set to?",
				"Place Bet", JOptionPane.QUESTION_MESSAGE);
		int bet;
		
		try {
			bet = Integer.parseInt(rawBet);
		} catch (NumberFormatException nfe) {
			bet = -1;
		}
		
		Player currPlayer = mf.getToolBar().getSelectedPlayer();
		
		if (mf.getModel().placeBet(currPlayer, bet)) {
			// Bet was valid and successfully placed
			
			// Update PlayerState
			mf.getExtraPlayerInfo().get(currPlayer).setHasBet(true);
			
			// Update GUI
			mf.updateStatusBar(String.format("Player \"%s\" set their bet to %d", currPlayer.getPlayerName(), bet));
			mf.getToolBar().playerUpdated();
			mf.getSummaryPanel().updateSummaryPanel();
			
		} else {
			// Bet was invalid
			JOptionPane.showMessageDialog(mf, "Error: Invalid bet",
					"Place Bet", JOptionPane.WARNING_MESSAGE);
		}

	}

}
