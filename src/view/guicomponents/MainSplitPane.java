package view.guicomponents;

import java.awt.Dimension;

import javax.swing.JSplitPane;

import controller.MainSplitPaneSizeListener;

@SuppressWarnings("serial")
public class MainSplitPane extends JSplitPane {
	
	private CardPanelHolder cardPanelHolder;
	private SummaryPanel summPanel;

	public MainSplitPane(MainFrame mf) {
		super(JSplitPane.VERTICAL_SPLIT, new CardPanelHolder(mf), new SummaryPanel(mf));
		
		cardPanelHolder = (CardPanelHolder) this.getLeftComponent();
		summPanel = (SummaryPanel) this.getRightComponent();
		
		setResizeWeight(0.67);
		addComponentListener(new MainSplitPaneSizeListener(this));
	}
	
	public void checkDividerLocation() {
		cardPanelHolder.setMinimumSize(new Dimension(0, ((int) (this.getHeight() * (2.0/3.0)))));
	}
	
	public CardPanelHolder getCardPanelHolder() {
		return cardPanelHolder;
	}
	
	public SummaryPanel getSummaryPanel() {
		return summPanel;
	}
	
}
