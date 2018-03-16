package utility;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import shapes.point.Point;

public class AddShapesDialogs {

	public static int addSquareDialog() {
		JTextField sideLength = new JTextField();
		final JComponent[] components = new JComponent[] { new JLabel("Enter length of square side"), sideLength};
		if (JOptionPane.showConfirmDialog(null, components, "Add square",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!sideLength.getText().isEmpty()) {
				try {
					int tmpSide = Integer.parseInt(sideLength.getText());
					if (tmpSide > 0)
						return tmpSide;
					else
						DialogMethods.showErrorMessage("Length of square side must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Lenght of square side must be a number!");
				}
			} else {
				DialogMethods.showErrorMessage("Length of square side must not be empty!");
			}
		}
		return 0;
	}

}
