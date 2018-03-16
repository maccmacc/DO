package mvc.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.point.CommandAddPoint;
import shapes.point.Point;
import shapes.square.CommandAddSquare;
import shapes.square.Square;
import utility.AddShapesDialogs;
import utility.ModifyShapesDialogs;


public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	
	public DrawingController (DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void checkShape(ActionEvent e) {
		String selectedShape = frame.getButtonView().getCmbShapes().getSelectedItem().toString();
		if (selectedShape.equals("point"))
			onPointAdded(e); //problem je sto u pvim metodama treba da se prosledi MouseEvent a ne ActionEvent
		else if (selectedShape.equals("square"))
			onSquareAdded(e);
	}
	public void onPointAdded(MouseEvent e) {
		CommandAddPoint add = new CommandAddPoint(model, new Point(e.getX(), e.getY(), Color.black));
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onSquareAdded(MouseEvent e) {
		Point upperLeftPoint = new Point(e.getX(), e.getY(), Color.black);
		int sideLength = AddShapesDialogs.addSquareDialog();
		Square square = new Square(upperLeftPoint, sideLength, Color.black);
		CommandAddSquare add = new CommandAddSquare(model, square);
		add.execute();
		model.getUndoStack().offerLast(add);
	}
}
