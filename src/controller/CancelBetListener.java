package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.guicomponents.MainFrame;

public class CancelBetListener implements ActionListener {
	
	private MainFrame mf;

	public CancelBetListener(MainFrame mf) {
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Player currPlayer = mf.getToolBar().getSelectedPlayer();
		currPlayer.resetBet();
		
		// Update PlayerState
		mf.getExtraPlayerInfo().get(currPlayer).setHasBet(false);
		
		// Update GUI
		mf.updateStatusBar(String.format("Player \"%s\" reset their bet to 0", currPlayer.getPlayerName()));
		mf.getToolBar().playerUpdated();
		mf.getSummaryPanel().updateSummaryPanel();
	}

}
