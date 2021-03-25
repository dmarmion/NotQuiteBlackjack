package view;

import javax.swing.SwingUtilities;

import model.interfaces.BlackEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.guicomponents.MainFrame;
import view.interfaces.BlackEngineCallback;

public class BlackEngineCallbackGUI implements BlackEngineCallback {

	private MainFrame mf;

	public BlackEngineCallbackGUI(BlackEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf = new MainFrame(engine);
			}
		});
	}

	@Override
	public void nextCard(Player player, PlayingCard card, BlackEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf.getCardPanelHolder().drawCard(player, card);
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, BlackEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf.getCardPanelHolder().drawCard(player, card);
			}
		});

	}

	@Override
	public void result(Player player, int result, BlackEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf.resultUpdated(player, result);
			}
		});

	}

	@Override
	public void nextHouseCard(PlayingCard card, BlackEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf.getCardPanelHolder().drawCard(card);
			}
		});

	}

	@Override
	public void houseBustCard(PlayingCard card, BlackEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf.getCardPanelHolder().drawCard(card);
			}
		});

	}

	@Override
	public void houseResult(int result, BlackEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf.houseResultUpdated(result);
			}
		});

	}

}
