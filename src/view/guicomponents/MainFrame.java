package view.guicomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.interfaces.BlackEngine;
import model.interfaces.Player;
import model.viewmodel.PlayerState;
import model.viewmodel.ViewModel;
import view.ImageUtils;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private final int INITIAL_HEIGHT = 768;
	private final int INTIAL_WIDTH = 1024;
	private BlackEngine model;
	private ViewModel extraPlayerInfo;
	private String currSelectedPlayer;

	private Header header;
	MainSplitPane mainPane;
	private StatusBar statusBar;

	// Constructor creates the window and its components
	public MainFrame(BlackEngine engine) {
		super("Not Quite Blackjack");
		setBounds(100, 100, this.INTIAL_WIDTH, this.INITIAL_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(ImageUtils.getSuitImage("clubs"));
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(this.INTIAL_WIDTH / 2, this.INITIAL_HEIGHT / 3));

		// Add model and initialise extraPlayerInfo
		model = engine;

		extraPlayerInfo = new ViewModel();
		for (Player player : engine.getAllPlayers()) {
			extraPlayerInfo.put(player, new PlayerState(player));
		}

		currSelectedPlayer = "House";

		// define contained components
		header = new Header(this);
		mainPane = new MainSplitPane(this);
		statusBar = new StatusBar();

		// add contained components
		add(header, BorderLayout.NORTH);
		add(mainPane, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);

		setVisible(true);
	}

	public int getInitialHeight() {
		return this.INITIAL_HEIGHT;
	}

	public int getInitialWidth() {
		return this.INTIAL_WIDTH;
	}

	public BlackEngine getModel() {
		return model;
	}

	public Map<Player, PlayerState> getExtraPlayerInfo() {
		return extraPlayerInfo.getExtraPlayerInfo();
	}

	public String getCurrSelectedPlayer() {
		return this.currSelectedPlayer;
	}

	public CardPanelHolder getCardPanelHolder() {
		return mainPane.getCardPanelHolder();
	}

	public SummaryPanel getSummaryPanel() {
		return mainPane.getSummaryPanel();
	}

	public ToolBar getToolBar() {
		return header.getToolBar();
	}

	public Collection<Player> getAllPlayers() {
		return model.getAllPlayers();
	}

	public void newPlayerSelected(String name) {
		this.currSelectedPlayer = name;

		if (!currSelectedPlayer.equals("")) {
			getToolBar().playerUpdated();
			getCardPanelHolder().switchPlayerPanel();
		}
	}

	public void updateStatusBar(String message) {
		statusBar.updateStatus(message);
	}

	public void resultUpdated(Player player, int result) {
		// Update GUI
		getSummaryPanel().updateSummaryPanel();
		updateStatusBar(String.format("Player \"%s\" has been dealt their cards. Their result was: %d",
				player.getPlayerName(), result));

		// Check if all players have dealt
		boolean houseReady = true;

		for (Player p : getAllPlayers()) {
			if (!extraPlayerInfo.get(p).getHasDealt()) {
				// A player has not been dealt their cards
				houseReady = false;
			}
		}

		if (houseReady) {
			// Show Dialog
			JOptionPane.showMessageDialog(this, "The House is now ready to deal.", "House Ready", JOptionPane.INFORMATION_MESSAGE);

			// Switch to House cardPanel
			getToolBar().setSelectedPlayer("House");
			updateStatusBar("The House is being dealt cards");

			// Call methods on model
			new Thread() {
				@Override
				public void run() {
					model.dealHouse(100);
				}
			}.start();
		}
	}
	
	public void houseResultUpdated(int houseResult) {
		updateStatusBar(String.format("The House has been dealt their cards. Their result was: %d", houseResult));
		
		for (Player player: getAllPlayers()) {
			if (player.getResult() < houseResult)
				extraPlayerInfo.get(player).setWinLoss("Loss");
			else if (player.getResult() == houseResult)
				extraPlayerInfo.get(player).setWinLoss("Draw");
			else
				extraPlayerInfo.get(player).setWinLoss("Win");			
		}
		
		getSummaryPanel().updateSummaryPanel();
		
		JOptionPane.showMessageDialog(this, "Click OK to begin the next game.", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
		prepareForNewGame();
	}
	
	public void prepareForNewGame() {
		// Remove players with 0 points from the game
		List<Player> playersToRemove = new ArrayList<Player>();
		
		for (Player player : getAllPlayers()) {
			if (player.getPoints() <= 0) {
				playersToRemove.add(player);
			}
		}
		
		for (Player player : playersToRemove) {
			// update model & viewModel
			model.removePlayer(player);
			extraPlayerInfo.remove(player);
			
			// Update GUI
			getToolBar().updateComboBox();
			getCardPanelHolder().removePlayerPanel(player);
			getSummaryPanel().updateSummaryPanel();
		}
		
		// Reset card panels
		getCardPanelHolder().clearAllCardPanels();
		
		// Reset player state for remaining players
		for (Player player : getAllPlayers()) {		
			extraPlayerInfo.get(player).setHasBet(false);
			extraPlayerInfo.get(player).setHasDealt(false);
			
			// Update ToolBar
			getToolBar().setSelectedPlayer(player.getPlayerName());
			getToolBar().playerUpdated();
		}
		
		getToolBar().updateComboBox();	
	}

}