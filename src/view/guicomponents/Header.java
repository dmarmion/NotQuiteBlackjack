package view.guicomponents;

import java.awt.GridLayout;

import javax.swing.JPanel;

/*
 * The purpose of this class is to group the menu bar and toolbar into one component so that they can both be added to the
 * MainFrame in the NORTH position of the BorderLayout
 */

@SuppressWarnings("serial")
public class Header extends JPanel {
	
	private ToolBar toolbar;

	public Header(MainFrame mf) {
		setLayout(new GridLayout(2, 1));
		
		MenuBar menubar = new MenuBar(mf);
		toolbar = new ToolBar(mf);
		
		add(menubar);
		add(toolbar);
	}
	
	public ToolBar getToolBar() {
		return this.toolbar;
	}
	
}
