package view.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
	
	private final int NUM_CARDS = 6;
	private CardSlot[] cardSlots = new CardSlot[NUM_CARDS];
	private int currPosition = 0;

	public CardPanel(MainFrame mf) {
		setBackground(new Color(8, 138, 75)); // This colour is a nice green similar to that typically found in a casino
		setLayout(new GridLayout(1, NUM_CARDS, 15, 15));
		
		for (int i = 0; i < NUM_CARDS; i++) {
			cardSlots[i] = new CardSlot(null);
			add(cardSlots[i]);
		}
	}
	
	public void drawCard(PlayingCard card) {
		cardSlots[currPosition].setCard(card);
		cardSlots[currPosition].repaint();
		currPosition++;
		
		revalidate();
	}
	
	public void resetPanel() {
		currPosition = 0;
		
		for (CardSlot slot : cardSlots) {
			slot.setCard(null);
			slot.repaint();
		}
	}
	
}
