package view;

import javax.swing.table.AbstractTableModel;

import model.interfaces.Player;
import view.guicomponents.MainFrame;

@SuppressWarnings("serial")
public class SummaryTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Player ID", "Name", "Points", "Bet", "Last Result", "Last Win/Loss"};
	private MainFrame mf;
	
	public SummaryTableModel(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return mf.getAllPlayers().size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		int i = 0;
		for (Player player : mf.getAllPlayers()) {
			if (i == row) {
				// this is the player we are looking for
				if (column == 0)
					return player.getPlayerId();
				else if (column == 1)
					return player.getPlayerName();
				else if (column == 2)
					return player.getPoints();
				else if (column == 3)
					return player.getBet();
				else if (column == 4)
					return player.getResult();
				else if (column == 5)
					return mf.getExtraPlayerInfo().get(player).getWinLoss();
			}
			
			i++;
		}
		
		return "Error";
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}

}
