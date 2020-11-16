package mvc.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ButtonViewLeft extends JPanel{
	
	private JButton btnSaveLog;
	private JButton btnSaveDrawing;
	private JButton btnOpenDrawing;
	private JButton btnOpenLog;
	private JButton btnDrawFromLog;
 
	public ButtonViewLeft() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{344};
		gridBagLayout.rowHeights = new int[]{47, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		btnSaveDrawing = new JButton("Save drawing");
		GridBagConstraints gbc_btnSaveDrawing = new GridBagConstraints();
		gbc_btnSaveDrawing.fill = GridBagConstraints.BOTH;
		gbc_btnSaveDrawing.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveDrawing.gridx = 0;
		gbc_btnSaveDrawing.gridy = 0;
		add(btnSaveDrawing, gbc_btnSaveDrawing);
		
		btnSaveLog = new JButton("Save log");
		GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
		gbc_btnSaveLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveLog.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveLog.gridx = 0;
		gbc_btnSaveLog.gridy = 1;
		add(btnSaveLog, gbc_btnSaveLog);
		
		btnOpenDrawing = new JButton("Open drawing");
		GridBagConstraints gbc_btnOpenDrawing = new GridBagConstraints();
		gbc_btnOpenDrawing.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenDrawing.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpenDrawing.gridx = 0;
		gbc_btnOpenDrawing.gridy = 2;
		add(btnOpenDrawing, gbc_btnOpenDrawing);
		
		btnOpenLog = new JButton("Open log");
		GridBagConstraints gbc_btnOpenLog = new GridBagConstraints();
		gbc_btnOpenLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenLog.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpenLog.gridx = 0;
		gbc_btnOpenLog.gridy = 3;
		add(btnOpenLog, gbc_btnOpenLog);
		
		btnDrawFromLog = new JButton("Draw from log");
		GridBagConstraints gbc_btnDrawFromLog = new GridBagConstraints();
		gbc_btnDrawFromLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDrawFromLog.insets = new Insets(0, 0, 0, 5);
		gbc_btnDrawFromLog.gridx = 0;
		gbc_btnDrawFromLog.gridy = 4;
		add(btnDrawFromLog, gbc_btnDrawFromLog);
	}
	
	public JButton getBtnSaveLog() {
		return btnSaveLog;
	}

	public JButton getBtnSaveDrawing() {
		return btnSaveDrawing;
	}

	public JButton getBtnOpenDrawing() {
		return btnOpenDrawing;
	}

	public JButton getBtnOpenLog() {
		return btnOpenLog;
	}

	public JButton getBtnDrawFromLog() {
		return btnDrawFromLog;
	}

}
