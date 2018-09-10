package mvc.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;

public class ButtonView extends JPanel{
	private GridBagLayout gridBagLayout;
	private JButton btnUndo;
	private JButton btnRedo;
	private JToggleButton tglbtnSelect;
	private JComboBox cmbShapes;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnOuterColor;
	private JButton btnInnerColor;
	private JButton btnSaveLog;
	private JButton btnSaveDrawing;
	private JButton btnOpenDrawing;
	private JButton btnOpenLog;
	private JButton btnDrawFromLog;


	private JButton btnToFront = new JButton("To front");
	private JButton btnToBack = new JButton("To back");
	private JButton btnBringToFront = new JButton("Bring to front");
	private JButton btnBringToBack = new JButton("Bring to back");
	
	public ButtonView() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 133, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnUndo = new JButton("Undo");
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 0;
		add(btnUndo, gbc_btnUndo);
		btnUndo.setEnabled(false);
		
		btnRedo = new JButton("Redo");
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.fill = GridBagConstraints.BOTH;
		gbc_btnRedo.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedo.gridx = 1;
		gbc_btnRedo.gridy = 0;
		add(btnRedo, gbc_btnRedo);
		btnRedo.setEnabled(false);
		
		tglbtnSelect = new JToggleButton("Select");
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnSelect.gridx = 2;
		gbc_tglbtnSelect.gridy = 0;
		add(tglbtnSelect, gbc_tglbtnSelect);
		
		cmbShapes = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 0;
		add(cmbShapes, gbc_comboBox);
		cmbShapes.setModel(new DefaultComboBoxModel(new String[] {"point", "line", "square", "circle", "rectangle", "hexagon"}));
		
		btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 1;
		add(btnDelete, gbc_btnDelete);
		btnDelete.setEnabled(false);
		
		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 1;
		add(btnUpdate, gbc_btnUpdate);
		btnUpdate.setEnabled(false);
		
		btnSaveDrawing = new JButton("Save drawing");
		GridBagConstraints gbc_btnSaveDrawing = new GridBagConstraints();
		gbc_btnSaveDrawing.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveDrawing.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveDrawing.gridx = 2;
		gbc_btnSaveDrawing.gridy = 1;
		add(btnSaveDrawing, gbc_btnSaveDrawing);
		
		btnSaveLog = new JButton("Save log");
		GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
		gbc_btnSaveLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveLog.gridx = 3;
		gbc_btnSaveLog.gridy = 1;
		add(btnSaveLog, gbc_btnSaveLog);
		
		btnOuterColor = new JButton("Outer color");
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOuterColor.gridx = 0;
		gbc_btnOuterColor.gridy = 2;
		btnOuterColor.setBackground(Color.black);
		btnOuterColor.setForeground(Color.white);
		add(btnOuterColor, gbc_btnOuterColor);
		
		btnInnerColor = new JButton("Inner color");
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 1;
		gbc_btnInnerColor.gridy = 2;
		btnInnerColor.setBackground(Color.white);
		btnInnerColor.setForeground(Color.black);
		add(btnInnerColor, gbc_btnInnerColor);
		
		btnOpenDrawing = new JButton("Open drawing");
		GridBagConstraints gbc_btnOpenDrawing = new GridBagConstraints();
		gbc_btnOpenDrawing.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenDrawing.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpenDrawing.gridx = 2;
		gbc_btnOpenDrawing.gridy = 2;
		add(btnOpenDrawing, gbc_btnOpenDrawing);
		
		btnOpenLog = new JButton("Open log");
		GridBagConstraints gbc_btnOpenLog = new GridBagConstraints();
		gbc_btnOpenLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenLog.gridx = 3;
		gbc_btnOpenLog.gridy = 2;
		add(btnOpenLog, gbc_btnOpenLog);
		
		btnDrawFromLog = new JButton("Draw from log");
		GridBagConstraints gbc_btnDrawFromLog = new GridBagConstraints();
		gbc_btnDrawFromLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDrawFromLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnDrawFromLog.gridx = 3;
		gbc_btnDrawFromLog.gridy = 3;
		add(btnDrawFromLog, gbc_btnDrawFromLog);
		
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.insets = new Insets(0, 0, 0, 5);
		gbc_btnToFront.gridx = 0;
		gbc_btnToFront.gridy = 4;
		add(btnToFront, gbc_btnToFront);
		
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnToBack.gridx = 1;
		gbc_btnToBack.gridy = 4;
		add(btnToBack, gbc_btnToBack);
		
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.insets = new Insets(0, 0, 0, 5);
		gbc_btnBringToFront.gridx = 2;
		gbc_btnBringToFront.gridy = 4;
		add(btnBringToFront, gbc_btnBringToFront);
		
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.gridx = 3;
		gbc_btnBringToBack.gridy = 4;
		add(btnBringToBack, gbc_btnBringToBack);
		
	}

	public GridBagLayout getGridBagLayout() {
		return gridBagLayout;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public JComboBox getCmbShapes() {
		return cmbShapes;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
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
	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

}
