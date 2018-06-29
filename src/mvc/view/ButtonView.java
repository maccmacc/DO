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
	
	public ButtonView() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnUndo = new JButton("Undo");
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 0;
		add(btnUndo, gbc_btnUndo);
		
		btnRedo = new JButton("Redo");
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedo.gridx = 1;
		gbc_btnRedo.gridy = 0;
		add(btnRedo, gbc_btnRedo);
		
		tglbtnSelect = new JToggleButton("Select");
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
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
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 1;
		add(btnDelete, gbc_btnDelete);
		btnDelete.setEnabled(false);
		
		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 1;
		add(btnUpdate, gbc_btnUpdate);
		btnUpdate.setEnabled(false);
		
		btnOuterColor = new JButton("Outer color");
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnOuterColor.gridx = 2;
		gbc_btnOuterColor.gridy = 1;
		btnOuterColor.setBackground(Color.black);
		btnOuterColor.setForeground(Color.white);
		add(btnOuterColor, gbc_btnOuterColor);
		
		btnInnerColor = new JButton("Inner color");
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.gridx = 3;
		gbc_btnInnerColor.gridy = 1;
		btnInnerColor.setBackground(Color.white);
		btnInnerColor.setForeground(Color.black);
		add(btnInnerColor, gbc_btnInnerColor);
		
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

}
