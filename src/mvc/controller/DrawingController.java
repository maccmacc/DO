package mvc.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import drawingFrame.DrawingFrame;
import hexagon.Hexagon;
import mvc.model.DrawingModel;
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
	private Line tmpLine;
	private int numberOfClicks;
	
	
	public DrawingController (DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void checkShape(MouseEvent e) {
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
		CommandAddPoint add = new CommandAddPoint(model, new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground()));
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onSquareAdded(MouseEvent e) {
		Point upperLeftPoint = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int sideLength = AddShapesDialogs.addSquareDialog();
		Square square = new Square(upperLeftPoint, sideLength, frame.getButtonView().getBtnOuterColor().getBackground());
		square.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddSquare add = new CommandAddSquare(model, square);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onCircleAdded(MouseEvent e) {
		Point center = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int r = AddShapesDialogs.addCircleDialog();
		Circle circle = new Circle(center, r, frame.getButtonView().getBtnOuterColor().getBackground());
		circle.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddCircle add = new CommandAddCircle(model, circle);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onRectangleAdded(MouseEvent e) {
		Point upperLeftPoint = new Point(e.getX(), e.getY(), frame.getButtonView().getBtnOuterColor().getBackground());
		int[] sides = AddShapesDialogs.addRectangleDialog();
		Rectangle rectangle = new Rectangle(upperLeftPoint, sides[0], sides[1], frame.getButtonView().getBtnOuterColor().getBackground());
		rectangle.setSurfaceColor(frame.getButtonView().getBtnInnerColor().getBackground());
		CommandAddRectangle add = new CommandAddRectangle(model, rectangle);
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
		CommandAddHexagonAdapter add = new CommandAddHexagonAdapter(model, hexagonAdapter);
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
			CommandAddLine add = new CommandAddLine(model, tmpLine);
			add.execute();
			model.getUndoStack().offerLast(add);
			numberOfClicks = 0;
		}
	}
}
