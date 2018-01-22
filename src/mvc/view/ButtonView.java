package mvc.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
		cmbShapes.setModel(new DefaultComboBoxModel(new String[] {"point", "line"}));
		
		btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 1;
		add(btnDelete, gbc_btnDelete);
		
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

}
