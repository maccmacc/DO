package mvc.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.Point;


public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	
	public DrawingController (DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void onPointAdded(MouseEvent e) {
		model.addShape(new Point(e.getX(), e.getY(), Color.BLACK));
	}
}
