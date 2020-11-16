package mvc.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ButtonViewRight extends JPanel{
	
	private JButton btnToFront = new JButton("To front");
	private JButton btnToBack = new JButton("To back");
	private JButton btnBringToFront = new JButton("Bring to front");
	private JButton btnBringToBack = new JButton("Bring to back");
 
	public ButtonViewRight() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 0;
		add(btnBringToFront, gbc_btnBringToFront);
		btnBringToFront.setEnabled(false);
		
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBringToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToBack.gridx = 0;
		gbc_btnBringToBack.gridy = 1;
		add(btnBringToBack, gbc_btnBringToBack);
		btnBringToBack.setEnabled(false);
		
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.fill = GridBagConstraints.BOTH;
		gbc_btnToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnToFront.gridx = 0;
		gbc_btnToFront.gridy = 2;
		add(btnToFront, gbc_btnToFront);
		btnToFront.setEnabled(false);
		
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnToBack.gridx = 0;
		gbc_btnToBack.gridy = 3;
		add(btnToBack, gbc_btnToBack);
		btnToBack.setEnabled(false);
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
