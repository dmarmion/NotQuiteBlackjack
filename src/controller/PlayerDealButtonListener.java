package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.guicomponents.MainFrame;

public class PlayerDealButtonListener implements ActionListener {

	private MainFrame mf;

	public PlayerDealButtonListener(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player player = mf.getToolBar().getSelectedPlayer();

		// Update PlayerState
		mf.getExtraPlayerInfo().get(player).setHasDealt(true);

		// Update GUI
		mf.updateStatusBar(String.format("Player \"%s\" is being dealt cards", player.getPlayerName()));
		mf.getToolBar().playerUpdated(); // To disable toolbar buttons for this player

		// Deal cards to the player
		new Thread() {
			@Override
			public void run() {
				mf.getModel().dealPlayer(player, 100);
			}
		}.start();
	}

}
