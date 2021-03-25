package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import view.guicomponents.MainSplitPane;

public class MainSplitPaneSizeListener extends ComponentAdapter {
	
	private MainSplitPane mainSplitPane;

	public MainSplitPaneSizeListener(MainSplitPane msp) {
		this.mainSplitPane = msp;
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		mainSplitPane.checkDividerLocation();
	}
	
}
