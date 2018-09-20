package mvc.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import drawingFrame.DrawingFrame;
import hexagon.Hexagon;
import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.circle.Circle;
import shapes.circle.CommandAddCircle;
import shapes.hexagon.CommandAddHexagonAdapter;
import shapes.hexagon.HexagonAdapter;
import shapes.line.CommandAddLine;
import shapes.line.Line;
import shapes.point.CommandAddPoint;
import shapes.point.Point;
import shapes.rectangle.CommandAddRectangle;
import shapes.rectangle.Rectangle;
import shapes.square.CommandAddSquare;
import shapes.square.Square;
import utility.DialogMethods;


public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private LogView logView;
	private Line tmpLine;
	private int numberOfClicks;
	
	public DrawingController (DrawingModel model, DrawingFrame frame, LogView logView) {
		this.model = model;
		this.frame = frame;
		this.logView = logView;
	}
	
	public void checkShape(MouseEvent e) {
		boolean done = false;
		String selectedShape = frame.getButtonView().getCmbShapes().getSelectedItem().toString();
		if (selectedShape.equals("point"))
			done = onPointAdded(e);  
		else if (selectedShape.equals("square"))
			done = onSquareAdded(e);
		else if (selectedShape.equals("circle"))
			done = onCircleAdded(e);
		else if (selectedShape.equals("rectangle"))
			done = onRectangleAdded(e);
		else if (selectedShape.equals("line"))
			done = onLineAdded(e);
		else if (selectedShape.equals("hexagon"))
			done = onHexagonAdded(e);
		
		if (done) frame.getButtonView().getBtnUndo().setEnabled(true);
	}
	public boolean onPointAdded(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY(), 
				frame.getButtonView().getBtnOuterColor().getBackground());
		CommandAddPoint add = new CommandAddPoint(model, point);
		add.execute();
		model.getUndoStack().offerLast(add);
		logView.getDlm().addElement("Add:" + point.toString());
		return true;
	}
	
	public boolean onSquareAdded(MouseEvent e) {
		Point upperLeftPoint = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int side = 0;
		JTextField sideLength = new JTextField();
		final JComponent[] components = new JComponent[] { new JLabel("Enter length of square side"), sideLength};
		if (JOptionPane.showConfirmDialog(null, components, "Add square",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!sideLength.getText().isEmpty()) {
				try {
					int tmpSide = Integer.parseInt(sideLength.getText());
					if (tmpSide > 0)
						side = tmpSide;
					else {
						DialogMethods.showErrorMessage("Length of square side must be greater than 0!");
					}
				} catch (NumberFormatException ex) {
					DialogMethods.showErrorMessage("Lenght of square side must be a number!");
				}
			} else {
				DialogMethods.showErrorMessage("Length of square side must not be empty!");
			}
		}
		if (side <= 0) return false;
		Square square = new Square(upperLeftPoint, side, frame.getButtonView().getBtnOuterColor().getBackground());
		square.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddSquare add = new CommandAddSquare(model, square);
		add.execute();
		model.getUndoStack().offerLast(add);
		logView.getDlm().addElement("Add:" + square.toString());
		return true;
	}
	
	public boolean onCircleAdded(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int perimeter = 0;
		JTextField r = new JTextField();
		final JComponent[] components = new JComponent[] { new JLabel("Enter length of circle perimeter"), r};
		if (JOptionPane.showConfirmDialog(null, components, "Add circle",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!r.getText().isEmpty()) {
				try {
					int tmpR = Integer.parseInt(r.getText());
					if (tmpR > 0)
						perimeter = tmpR;
					else {
						DialogMethods.showErrorMessage("Length of perimeter side must be greater than 0!");
					}
				} catch (NumberFormatException ex) {
					DialogMethods.showErrorMessage("Lenght of perimeter must be a number!");
				}
			} else {
				DialogMethods.showErrorMessage("Length of perimeter must not be empty!");
			}
		}
		if (perimeter <= 0) {
			return false;
		}
		Circle circle = new Circle(center, perimeter, frame.getButtonView().getBtnOuterColor().getBackground());
		circle.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddCircle add = new CommandAddCircle(model, circle);
		add.execute();
		model.getUndoStack().offerLast(add);
		logView.getDlm().addElement("Add:" + circle.toString());
		return true;
	}
	
	public boolean onRectangleAdded(MouseEvent e) {
		Point upperLeftPoint = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int widthR = 0;
		int heightR = 0;
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
					
					if (tmpWidth > 0 && tmpHeight > 0) {
						widthR = tmpWidth;
						heightR = tmpHeight;
					}
					else
						DialogMethods.showErrorMessage("Width and height must be greater than 0!");
				} catch (NumberFormatException ex) {
					DialogMethods.showErrorMessage("Width and height must be a number!");
				}
			} else {
				DialogMethods.showErrorMessage("Width and height must not be empty!");
			}
		}
		if (widthR <= 0 || heightR <= 0) {
			return false;
		}
		Rectangle rectangle = new Rectangle(upperLeftPoint, widthR, heightR, frame.getButtonView().getBtnOuterColor().getBackground());
		rectangle.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddRectangle add = new CommandAddRectangle(model, rectangle);
		add.execute();
		model.getUndoStack().offerLast(add);
		logView.getDlm().addElement("Add:" + rectangle.toString());
		return true;
	}
	
	public boolean onHexagonAdded(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int perimeter = 0;
		JTextField r = new JTextField();
		final JComponent[] components = new JComponent[] { new JLabel("Enter perimeter"), r};
		if (JOptionPane.showConfirmDialog(null, components, "Add hexagon",
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			if (!r.getText().isEmpty()) {
				try {
					int tmpR = Integer.parseInt(r.getText());
					if (tmpR > 0)
						perimeter = tmpR;
					else
						DialogMethods.showErrorMessage("Length of perimeter must be greater than 0!");
				} catch (NumberFormatException ex) {
					DialogMethods.showErrorMessage("Lenght of perimeter side must be a number!");
				}
			} else {
				DialogMethods.showErrorMessage("Length of perimeter side must not be empty!");
			}
		}
		if (perimeter <= 0) {
			return false;
		}
		Hexagon hexagon = new Hexagon(center.getX(), center.getY(), perimeter);
		hexagon.setBorderColor(frame.getButtonView().getBtnOuterColor().getBackground());
		hexagon.setAreaColor(frame.getButtonView().getBtnInnerColor().getBackground());
		HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon);
		hexagonAdapter.setColor(frame.getButtonView().getBtnOuterColor().getBackground());
		hexagonAdapter.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddHexagonAdapter add = new CommandAddHexagonAdapter(model, hexagonAdapter);
		add.execute();
		model.getUndoStack().offerLast(add);
		logView.getDlm().addElement("Add:" + hexagonAdapter.toString());
		return true;
	}
	
	public boolean onLineAdded(MouseEvent e) {
		if (numberOfClicks == 0) {
			tmpLine = new Line (new Point(e.getX(), e.getY()), new Point(0,0));
			numberOfClicks++;
		}
		else if (numberOfClicks == 1) {
			tmpLine.getEndPoint().setX(e.getX());
			tmpLine.getEndPoint().setY(e.getY());
			tmpLine.setColor(frame.getButtonView().getBtnOuterColor().getBackground());
			CommandAddLine add = new CommandAddLine(model, tmpLine);
			add.execute();
			model.getUndoStack().offerLast(add);
			logView.getDlm().addElement("Add:" + tmpLine.toString());
			numberOfClicks = 0;
		}
		
		return true;
	}

	public DrawingModel getModel() {
		return model;
	}
}
