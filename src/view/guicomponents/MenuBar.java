package view.guicomponents;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerItemListener;
import controller.RemovePlayerItemListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	public MenuBar(MainFrame mf) {		
		JMenu playerMenu = new JMenu("Players");
		
		JMenuItem addPlayer = new JMenuItem("Add Player");
		addPlayer.addActionListener(new AddPlayerItemListener(mf));
		
		JMenuItem removePlayer = new JMenuItem("Remove Player");
		removePlayer.addActionListener(new RemovePlayerItemListener(mf));
		
		playerMenu.add(addPlayer);
		playerMenu.add(removePlayer);
		
		add(playerMenu);
		
	}
	
}
