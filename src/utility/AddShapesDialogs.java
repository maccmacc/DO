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
	
	public static int addCircleDialog() {
		JTextField r = new JTextField();
		final JComponent[] components = new JComponent[] { new JLabel("Enter length of circle perimeter"), r};
		if (JOptionPane.showConfirmDialog(null, components, "Add circle",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!r.getText().isEmpty()) {
				try {
					int tmpR = Integer.parseInt(r.getText());
					if (tmpR > 0)
						return tmpR;
					else
						DialogMethods.showErrorMessage("Length of perimeter side must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Lenght of perimeter must be a number!");
				}
			} else {
				DialogMethods.showErrorMessage("Length of perimeter must not be empty!");
			}
		}
		return 0;
	}
	
	public static int[] addRectangleDialog() {
		JTextField width = new JTextField();
		JTextField height = new JTextField();
		final JComponent[] components = new JComponent[] { new JLabel("Enter rectangle width"), width, 
				new JLabel("Enter rectangle height"), height};
		if (JOptionPane.showConfirmDialog(null, components, "Add rectangle",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!width.getText().isEmpty() || !height.getText().isEmpty()) {
				try {
					int tmpWidth = Integer.parseInt(width.getText());
					int tmpHeight = Integer.parseInt(height.getText());
					
					if (tmpWidth > 0 && tmpHeight > 0)
						return new int[] {tmpWidth, tmpHeight};
					else
						DialogMethods.showErrorMessage("Width and height must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Width and height must be a number!");
				}
			} else {
				DialogMethods.showErrorMessage("Width and height must not be empty!");
			}
		}
		return new int[] {0, 0};
	}

}
