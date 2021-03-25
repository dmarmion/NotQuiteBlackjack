package view.guicomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.SummaryTableModel;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
	
	private JTable table;

	public SummaryPanel(MainFrame mf) {
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(0, 150));	
		
		table = new JTable(new SummaryTableModel(mf));
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setFillsViewportHeight(true);
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void updateSummaryPanel() {
		// Cast to SummaryTableModel to access the custom updateTable() method
		SummaryTableModel stm = (SummaryTableModel) table.getModel();
		stm.updateTable();
	}
	
}