package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import view.guicomponents.MainFrame;

public class PlayerSelectedListener implements ActionListener {
	
	private MainFrame mf;
	
	public PlayerSelectedListener(MainFrame mf) {
		this.mf = mf;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox playerSelect = (JComboBox) e.getSource();
		String currPlayerName;
		
		try {
			currPlayerName = playerSelect.getSelectedItem().toString();
		} catch (Exception exc) {
			// This listener also gets called when a player is added, so this is necessary to avoid a NullPointerException.
			currPlayerName = "";
		}
		
		mf.newPlayerSelected(currPlayerName);
	}

}
