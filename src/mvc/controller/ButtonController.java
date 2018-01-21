package mvc.controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.Command;
import shapes.Shape;
import shapes.point.CommandRemovePoint;

public class ButtonController {
	private DrawingModel model;
	private DrawingFrame frame;
	
	private ArrayList<Integer> selectedShapesIndexList = new ArrayList<>();
	
	public ButtonController (DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void onUndoButtonClicked(MouseEvent e) {
		 if (!model.getUndoStack().isEmpty()) {
		      Command previous = model.getUndoStack().pollLast();
		      model.getRedoStack().offerLast(previous);
		      previous.unexecute();
		}
	}
	
	public void onRedoButtonClicked(MouseEvent e) {
		if (!model.getRedoStack().isEmpty()) {
		      Command previous = model.getRedoStack().pollLast();
		      model.getUndoStack().offerLast(previous);
		      previous.execute();
		}
	}
	
	public void onSelectButtonClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		goThroughShapesList (x, y);
	}
	
	public void unselectShapes() {
		for(Shape shape : model.getShapeList()) {
			shape.setSelected(false);
		}
	}
	
	public boolean goThroughShapesList (int x, int y) {
		for (int i = model.getShapeList().size() - 1; i >= 0; i--) {
			if(model.getShapeList().get(i).contains(x, y)) {
				model.getShapeList().get(i).setSelected(true);
				return true;
			}
		}
		return false;
	}
	
	

}
