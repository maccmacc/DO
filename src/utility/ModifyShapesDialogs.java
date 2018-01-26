package utility;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import shapes.point.Point;

public class ModifyShapesDialogs {

	public static Point modifyPointDialog(Point point) {
		JTextField xCoordinate = new JTextField();
		JTextField yCoordinate = new JTextField();
		xCoordinate.setText(Integer.toString(point.getX()));
		yCoordinate.setText(Integer.toString(point.getY()));
		final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinate,
				new JLabel("Enter new y coordinate:"), yCoordinate };
		if (JOptionPane.showConfirmDialog(null, components, "Modify point",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!xCoordinate.getText().isEmpty() && !yCoordinate.getText().isEmpty()) {
				try {
					int tmpX = Integer.parseInt(xCoordinate.getText());
					int tmpY = Integer.parseInt(yCoordinate.getText());
					if (tmpX >= 0 && tmpY >= 0)
						return new Point(tmpX, tmpY, Color.BLACK);
					else
						DialogMethods.showErrorMessage("Coordinates must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Coordinates must be numbers!");
				}
			} else {
				DialogMethods.showErrorMessage("Coordinates must not be empty!");
			}
		}
		return null;
	}

}
