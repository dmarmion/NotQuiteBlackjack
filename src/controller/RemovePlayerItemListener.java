package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.guicomponents.MainFrame;

public class RemovePlayerItemListener implements ActionListener {

	private MainFrame mf;
	
	public RemovePlayerItemListener(MainFrame mf) {
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (mf.getToolBar().getSelectedPlayer() != null) {
			// Remove player from BlackEngine & ExtraPlayerInfo
			Player player = mf.getToolBar().getSelectedPlayer();
			mf.getModel().removePlayer(player);
			mf.getExtraPlayerInfo().remove(player);
			
			// Update GUI
			mf.updateStatusBar(String.format("Player \"%s\" removed.", player.getPlayerName()));
			mf.getToolBar().updateComboBox();
			mf.getCardPanelHolder().removePlayerPanel(player);
		} else {
			mf.updateStatusBar("Error: Currently selected player could not be removed.");
		}
	}

}
