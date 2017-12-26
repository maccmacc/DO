package mvc.controller;

import java.awt.event.MouseEvent;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.Command;
import shapes.point.CommandRemovePoint;

public class ButtonController {
	private DrawingModel model;
	private DrawingFrame frame;
	
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

}
