package utility;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import hexagon.Hexagon;
import mvc.controller.ButtonController;
import shapes.circle.Circle;
import shapes.hexagon.HexagonAdapter;
import shapes.line.Line;
import shapes.point.Point;
import shapes.rectangle.Rectangle;
import shapes.square.Square;

public class ModifyShapesDialogs {
	
	public static Point modifyPointDialog(Point point) {
		JTextField xCoordinate = new JTextField();
		JTextField yCoordinate = new JTextField();
		JButton btnNewColor = new JButton();
		xCoordinate.setText(Integer.toString(point.getX()));
		yCoordinate.setText(Integer.toString(point.getY()));
		btnNewColor.setBackground(point.getColor());
		btnNewColor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColor.setBackground(CommonHelpers.chooseColor(btnNewColor.getBackground()));
			}
		});
		final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinate,
				new JLabel("Enter new y coordinate:"), yCoordinate,
				new JLabel("Choose new color:"), btnNewColor};
		if (JOptionPane.showConfirmDialog(null, components, "Modify point",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!xCoordinate.getText().isEmpty() || !yCoordinate.getText().isEmpty()) {
				try {
					int tmpX = Integer.parseInt(xCoordinate.getText());
					int tmpY = Integer.parseInt(yCoordinate.getText());
					if (tmpX > 0 && tmpY > 0)
						return new Point(tmpX, tmpY, btnNewColor.getBackground());
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
	
	public static Circle modifyCircleDialog(Circle circle) {
		JTextField xCoordinateCenter = new JTextField();
		JTextField yCoordinateCenter = new JTextField();
		JTextField r = new JTextField();
		JButton btnNewColorOuter = new JButton();
		JButton btnNewColorInner = new JButton();
		xCoordinateCenter.setText(Integer.toString(circle.getCenter().getX()));
		yCoordinateCenter.setText(Integer.toString(circle.getCenter().getY()));
		r.setText(Integer.toString(circle.getR()));
		btnNewColorOuter.setBackground(circle.getColor());
		btnNewColorOuter.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorOuter.setBackground(CommonHelpers.chooseColor(btnNewColorOuter.getBackground()));
			}
		});
		btnNewColorInner.setBackground(circle.getSurfaceColor());
		btnNewColorInner.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorInner.setBackground(CommonHelpers.chooseColor(btnNewColorInner.getBackground()));
			}
		});
		final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateCenter,
				new JLabel("Enter new y coordinate:"), yCoordinateCenter, new JLabel("Enter new perimeter:"), r,
				new JLabel("Choose new outer color:"), btnNewColorOuter,
				new JLabel("Choose new inner color:"), btnNewColorInner};
		if (JOptionPane.showConfirmDialog(null, components, "Modify circle",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!xCoordinateCenter.getText().isEmpty() || !yCoordinateCenter.getText().isEmpty() || !r.getText().isEmpty()) {
				try {
					int tmpX = Integer.parseInt(xCoordinateCenter.getText());
					int tmpY = Integer.parseInt(yCoordinateCenter.getText());
					int tmpR = Integer.parseInt(r.getText());
					if (tmpX > 0 && tmpY > 0 && tmpR > 0)
						return new Circle(new Point(tmpX, tmpY), tmpR, btnNewColorOuter.getBackground(), btnNewColorInner.getBackground());
					else
						DialogMethods.showErrorMessage("Coordinates and perimeter must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Coordinates and perimeter must be numbers!");
				}
			} else {
				DialogMethods.showErrorMessage("Coordinates and perimeter must not be empty!");
			}
		}
		return null;
	}
	
	public static Square modifySquareDialog(Square square) {
		JTextField xCoordinateUpperLeft = new JTextField();
		JTextField yCoordinateUpperLeft = new JTextField();
		JTextField sideLength = new JTextField();
		JButton btnNewColorOuter = new JButton();
		JButton btnNewColorInner = new JButton();
		xCoordinateUpperLeft.setText(Integer.toString(square.getUpperLeftPoint().getX()));
		yCoordinateUpperLeft.setText(Integer.toString(square.getUpperLeftPoint().getY()));
		sideLength.setText(Integer.toString(square.getSideLength()));
		btnNewColorOuter.setBackground(square.getColor());
		btnNewColorOuter.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorOuter.setBackground(CommonHelpers.chooseColor(btnNewColorOuter.getBackground()));
			}
		});
		btnNewColorInner.setBackground(square.getSurfaceColor());
		btnNewColorInner.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorInner.setBackground(CommonHelpers.chooseColor(btnNewColorInner.getBackground()));
			}
		});
		final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateUpperLeft,
				new JLabel("Enter new y coordinate:"), yCoordinateUpperLeft, new JLabel("Enter new side length:"), sideLength,
				new JLabel("Choose new outer color:"), btnNewColorOuter, new JLabel("Choose new inner color:"), btnNewColorInner};
		if (JOptionPane.showConfirmDialog(null, components, "Modify square",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!xCoordinateUpperLeft.getText().isEmpty() || !yCoordinateUpperLeft.getText().isEmpty() || !sideLength.getText().isEmpty()) {
				try {
					int tmpX = Integer.parseInt(xCoordinateUpperLeft.getText());
					int tmpY = Integer.parseInt(yCoordinateUpperLeft.getText());
					int tmpSide = Integer.parseInt(sideLength.getText());
					if (tmpX > 0 && tmpY > 0 && tmpSide > 0)
						return new Square(new Point(tmpX, tmpY), tmpSide,
								btnNewColorOuter.getBackground(), btnNewColorInner.getBackground());
					else
						DialogMethods.showErrorMessage("Coordinates and side length must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Coordinates and side length must be numbers!");
				}
			} else {
				DialogMethods.showErrorMessage("Coordinates and side length must not be empty!");
			}
		}
		return null;
	}
	
	public static Rectangle modifyRectangleDialog(Rectangle rectangle) {
		JTextField xCoordinateUpperLeft = new JTextField();
		JTextField yCoordinateUpperLeft = new JTextField();
		JTextField width = new JTextField();
		JTextField height = new JTextField();
		JButton btnNewColorOuter = new JButton();
		JButton btnNewColorInner = new JButton();
		xCoordinateUpperLeft.setText(Integer.toString(rectangle.getUpperLeftPoint().getX()));
		yCoordinateUpperLeft.setText(Integer.toString(rectangle.getUpperLeftPoint().getY()));
		width.setText(Integer.toString(rectangle.getSideLength()));
		height.setText(Integer.toString(rectangle.getWidth()));
		btnNewColorOuter.setBackground(rectangle.getColor());
		btnNewColorOuter.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorOuter.setBackground(CommonHelpers.chooseColor(btnNewColorOuter.getBackground()));
			}
		});
		btnNewColorInner.setBackground(rectangle.getSurfaceColor());
		btnNewColorInner.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorInner.setBackground(CommonHelpers.chooseColor(btnNewColorInner.getBackground()));
			}
		});
		final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateUpperLeft,
				new JLabel("Enter new y coordinate:"), yCoordinateUpperLeft, new JLabel("Enter new width:"), width,
				new JLabel("Enter new height:"), height, new JLabel("Choose new outer color:"), btnNewColorOuter,
				new JLabel("Choose new inner color:"), btnNewColorInner};
		if (JOptionPane.showConfirmDialog(null, components, "Modify rectangle",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!xCoordinateUpperLeft.getText().isEmpty() || !yCoordinateUpperLeft.getText().isEmpty() || !width.getText().isEmpty()
					|| !height.getText().isEmpty()) {
				try {
					int tmpX = Integer.parseInt(xCoordinateUpperLeft.getText());
					int tmpY = Integer.parseInt(yCoordinateUpperLeft.getText());
					int tmpWidth = Integer.parseInt(width.getText());
					int tmpHeight = Integer.parseInt(height.getText());
					if (tmpX > 0 && tmpY > 0 && tmpWidth > 0 && tmpHeight > 0)
						return new Rectangle(new Point(tmpX, tmpY), tmpWidth, tmpHeight, btnNewColorOuter.getBackground(),
								btnNewColorInner.getBackground());
					else
						DialogMethods.showErrorMessage("Coordinates, width and height must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Coordinates, width and height must be numbers!");
				}
			} else {
				DialogMethods.showErrorMessage("Coordinates, width and height must not be empty!");
			}
		}
		return null;
	}

	public static Line modifyLineDialog(Line line) {
		JTextField xCoordinateStartPoint = new JTextField();
		JTextField yCoordinateStartPoint = new JTextField();
		JTextField xCoordinateEndPoint = new JTextField();
		JTextField yCoordinateEndPoint = new JTextField();
		xCoordinateStartPoint.setText(Integer.toString(line.getStartPoint().getX()));
		yCoordinateStartPoint.setText(Integer.toString(line.getStartPoint().getY()));
		xCoordinateEndPoint.setText(Integer.toString(line.getEndPoint().getX()));
		yCoordinateEndPoint.setText(Integer.toString(line.getEndPoint().getY()));
		final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate of start point:"), xCoordinateStartPoint,
				new JLabel("Enter new y coordinate of start point:"), yCoordinateStartPoint,
				new JLabel("Enter new x coordinate of end point:"), xCoordinateEndPoint, 
				new JLabel("Enter new y coordinate of end point:"), yCoordinateEndPoint};
		if (JOptionPane.showConfirmDialog(null, components, "Modify line",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!xCoordinateStartPoint.getText().isEmpty() || !yCoordinateStartPoint.getText().isEmpty() || 
					!xCoordinateEndPoint.getText().isEmpty() || !yCoordinateEndPoint.getText().isEmpty()) {
				try {
					int tmpStartX = Integer.parseInt(xCoordinateStartPoint.getText());
					int tmpStartY = Integer.parseInt(yCoordinateStartPoint.getText());
					int tmpEndX = Integer.parseInt(xCoordinateEndPoint.getText());
					int tmpEndY = Integer.parseInt(yCoordinateEndPoint.getText());
					if (tmpStartX > 0 && tmpStartY > 0 && tmpEndX > 0 && tmpEndY > 0)
						return new Line(new Point(tmpStartX, tmpStartY), new Point(tmpEndX, tmpEndY),  Color.BLACK);
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
	
	public static HexagonAdapter modifyHexagonAdapterDialog(HexagonAdapter hexagonAdapter) {
		JTextField xCoordinateCenter = new JTextField();
		JTextField yCoordinateCenter = new JTextField();
		JTextField r = new JTextField();
		JButton btnNewColorOuter = new JButton();
		JButton btnNewColorInner = new JButton();
		xCoordinateCenter.setText(Integer.toString(hexagonAdapter.getHexagon().getX()));
		yCoordinateCenter.setText(Integer.toString(hexagonAdapter.getHexagon().getY()));
		r.setText(Integer.toString(hexagonAdapter.getHexagon().getR()));
		btnNewColorOuter.setBackground(hexagonAdapter.getColor());
		btnNewColorOuter.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorOuter.setBackground(CommonHelpers.chooseColor(btnNewColorOuter.getBackground()));
			}
		});
		btnNewColorInner.setBackground(hexagonAdapter.getHexagon().getAreaColor());
		btnNewColorInner.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnNewColorInner.setBackground(CommonHelpers.chooseColor(btnNewColorInner.getBackground()));
			}
		});
		final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateCenter,
				new JLabel("Enter new y coordinate:"), yCoordinateCenter, new JLabel("Enter new perimeter:"), r,
				new JLabel("Choose new outer color:"), btnNewColorOuter,
				new JLabel("Choose new inner color:"), btnNewColorInner};
		if (JOptionPane.showConfirmDialog(null, components, "Modify hexagon",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!xCoordinateCenter.getText().isEmpty() || !yCoordinateCenter.getText().isEmpty() || !r.getText().isEmpty()) {
				try {
					int tmpX = Integer.parseInt(xCoordinateCenter.getText());
					int tmpY = Integer.parseInt(yCoordinateCenter.getText());
					int tmpR = Integer.parseInt(r.getText());
					if (tmpX > 0 && tmpY > 0 && tmpR > 0) {
						Hexagon hexagon = new Hexagon(tmpX, tmpY, tmpR);
						hexagon.setBorderColor(btnNewColorOuter.getBackground());
						hexagon.setAreaColor(btnNewColorInner.getBackground());
						return new HexagonAdapter(hexagon);
					} else
						DialogMethods.showErrorMessage("Coordinates and perimeter must be greater than 0!");
				} catch (NumberFormatException e) {
					DialogMethods.showErrorMessage("Coordinates and perimeter must be numbers!");
				}
			} else {
				DialogMethods.showErrorMessage("Coordinates and perimeter must not be empty!");
			}
		}
		return null;
	}
}
