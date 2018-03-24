package utility;

import java.awt.Color;

import javax.swing.JColorChooser;

public class CommonHelpers {
	public static Color chooseColor(Color previousColor) {
		Color tmpColor = JColorChooser.showDialog(null, "Choose color", previousColor);
		return (tmpColor != null) ? tmpColor : previousColor;
	}
}
