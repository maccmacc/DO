package mvc.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;

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
import utility.AddShapesDialogs;
import utility.ModifyShapesDialogs;


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
		frame.getButtonView().getBtnUndo().setEnabled(true);
		String selectedShape = frame.getButtonView().getCmbShapes().getSelectedItem().toString();
		if (selectedShape.equals("point"))
			onPointAdded(e); 
		else if (selectedShape.equals("square"))
			onSquareAdded(e);
		else if (selectedShape.equals("circle"))
			onCircleAdded(e);
		else if (selectedShape.equals("rectangle"))
			onRectangleAdded(e);
		else if (selectedShape.equals("line"))
			onLineAdded(e);
		else if (selectedShape.equals("hexagon"))
			onHexagonAdded(e);
	}
	public void onPointAdded(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY(), 
				frame.getButtonView().getBtnOuterColor().getBackground());
		CommandAddPoint add = new CommandAddPoint(model, point, logView);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onSquareAdded(MouseEvent e) {
		Point upperLeftPoint = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int sideLength = AddShapesDialogs.addSquareDialog();
		Square square = new Square(upperLeftPoint, sideLength, frame.getButtonView().getBtnOuterColor().getBackground());
		square.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddSquare add = new CommandAddSquare(model, square, logView);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onCircleAdded(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int r = AddShapesDialogs.addCircleDialog();
		Circle circle = new Circle(center, r, frame.getButtonView().getBtnOuterColor().getBackground());
		circle.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddCircle add = new CommandAddCircle(model, circle, logView);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onRectangleAdded(MouseEvent e) {
		Point upperLeftPoint = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int[] sides = AddShapesDialogs.addRectangleDialog();
		Rectangle rectangle = new Rectangle(upperLeftPoint, sides[0], sides[1], frame.getButtonView().getBtnOuterColor().getBackground());
		rectangle.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddRectangle add = new CommandAddRectangle(model, rectangle, logView);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onHexagonAdded(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int r = AddShapesDialogs.addHexagonDialog();
		Hexagon hexagon = new Hexagon(center.getX(), center.getY(), r);
		hexagon.setBorderColor(frame.getButtonView().getBtnOuterColor().getBackground());
		hexagon.setAreaColor(frame.getButtonView().getBtnInnerColor().getBackground());
		HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon);
		hexagonAdapter.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddHexagonAdapter add = new CommandAddHexagonAdapter(model, hexagonAdapter, logView);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onLineAdded(MouseEvent e) {
		if (numberOfClicks == 0) {
			tmpLine = new Line (new Point(e.getX(), e.getY()), new Point(0,0));
			numberOfClicks++;
		}
		else if (numberOfClicks == 1) {
			tmpLine.getEndPoint().setX(e.getX());
			tmpLine.getEndPoint().setY(e.getY());
			tmpLine.setColor(frame.getButtonView().getBtnOuterColor().getBackground());
			CommandAddLine add = new CommandAddLine(model, tmpLine, logView);
			add.execute();
			model.getUndoStack().offerLast(add);
			numberOfClicks = 0;
		}
	}

	public DrawingModel getModel() {
		return model;
	}
}
