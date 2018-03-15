package mvc.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.point.CommandAddPoint;
import shapes.point.Point;


public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	
	public DrawingController (DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void checkShape(MouseEvent e) {
		String selectedShape = frame.getButtonView().getCmbShapes().getSelectedItem().toString();
		if (selectedShape.equals("point"))
			onPointAdded(e);
		else if (selectedShape.equals("square"))
			System.out.println("hiii");
	}
	public void onPointAdded(MouseEvent e) {
		
		CommandAddPoint add = new CommandAddPoint(model, new Point(e.getX(), e.getY(), Color.black));
		add.execute();
		model.getUndoStack().offerLast(add);
	}
	
	public void onSquareAdded(MouseEvent e) {
		//CommandAddSquare add = new CommandAddSquare(model, )
	}
}
