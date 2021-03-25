package view.guicomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import model.interfaces.PlayingCard;
import view.ImageUtils;

@SuppressWarnings("serial")
public class CardSlot extends JPanel {

	private PlayingCard card;

	public CardSlot(PlayingCard card) {
		this.card = card;

		setBackground(new Color(8, 138, 75));
	}

	public void setCard(PlayingCard card) {
		this.card = card;
	}

	// Draws the playing card in the slot, if the card is not null
	@Override
	public void paintComponent(Graphics g) {
		if (card != null) {
			super.paintComponent(g);
			
			final int PADDING_LR = 5; // left-right padding
			final int PADDING_UPPER = 20; // gap above the card
			
			// Draw card shape
			final int CARD_WIDTH = this.getWidth() - (2 * PADDING_LR);
			final int CARD_HEIGHT = (int) (1.5 * CARD_WIDTH);
			
			g.setColor(Color.WHITE);
			g.fillRoundRect(PADDING_LR, PADDING_UPPER, CARD_WIDTH, CARD_HEIGHT, 25, 25);
			
			// Set font, with size as 1/4 of the card's width
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, this.getWidth() / 4));
			
			// Set text colour based on Suit
			if (card.getSuit() == PlayingCard.Suit.DIAMONDS || card.getSuit() == PlayingCard.Suit.HEARTS) {
				// Red card
				g.setColor(Color.RED);
				
			} else if (card.getSuit() == PlayingCard.Suit.CLUBS || card.getSuit() == PlayingCard.Suit.SPADES) {
				// Black card
				g.setColor(Color.BLACK);
			}
			
			// Determine text to draw on card
			String cardText = getCardText(card.getValue());

			// Draw upper left text
			g.drawString(cardText, 3 * PADDING_LR, PADDING_UPPER + g.getFontMetrics().getHeight());
			
			// Draw bottom right text
			int brTextXPos = this.getWidth() - g.getFontMetrics().charWidth(cardText.charAt(0)) - (3 * PADDING_LR);
			int brTextYPos = (int) (1.5 * this.getWidth()) - PADDING_UPPER;
			g.drawString(cardText, brTextXPos, brTextYPos);
			
			// Drawing suit - suit is drawn to a square with sides equal to 1/3rd of the width of a card
			Image suitImage = ImageUtils.getSuitImage(card.getSuit().toString());
			
			final int THIRD_OF_WIDTH = CARD_WIDTH / 3;
			int CARD_HEIGHT_MIDDLE = PADDING_UPPER + (CARD_HEIGHT / 2);
			
			if (suitImage != null) {
				g.drawImage(suitImage, (PADDING_LR + THIRD_OF_WIDTH), (CARD_HEIGHT_MIDDLE - (THIRD_OF_WIDTH / 2)),
						(this.getWidth() - PADDING_LR - THIRD_OF_WIDTH), (CARD_HEIGHT_MIDDLE + (THIRD_OF_WIDTH / 2)),
						0, 0, suitImage.getWidth(null), suitImage.getHeight(null), Color.WHITE, null);
			}
		} else {
			super.paintComponent(g);
		}
	}
	
	/**
	 * Helper method to take a PlayingCard value and return the correspondng String that should be written on a card
	 * @param value PlayingCard Value
	 * @return String corresponding to the value
	 */
	private String getCardText(PlayingCard.Value value) {
		String cardText = "";
		
		switch (value) {
		case EIGHT:
			cardText = "8";
			break;
		case NINE:
			cardText = "9";
			break;
		case TEN:
			cardText = "T";
			break;
		case JACK:
			cardText = "J";
			break;
		case QUEEN:
			cardText = "Q";
			break;
		case KING:
			cardText = "K";
			break;
		case ACE:
			cardText = "A";
			break;
		}
		
		return cardText;
	}
}
