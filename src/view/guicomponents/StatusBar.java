package view.guicomponents;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	
	JLabel statusMessage;

	public StatusBar() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		statusMessage = new JLabel("Welcome.");

		add(statusMessage);
	}
	
	public void updateStatus(String newText) {
		statusMessage.setText(newText);
	}
	
}
