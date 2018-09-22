package zAxis;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.Command;
import shapes.Shape;

public class ToBack implements Command{

	private DrawingModel model;
	private Shape shape;
	private DrawingFrame frame;
	private int currentIndex;
	private int newIndex;

	public ToBack(DrawingModel model, DrawingFrame frame, Shape shape) {
		this.model = model;
		this.shape = shape;
		this.frame = frame;
		currentIndex = model.getShapeList().indexOf(shape);
	}
	
	@Override
	public void execute() {
		model.getShapeList().remove(shape);
		model.getShapeList().add(currentIndex-1, shape);
		frame.repaint();
		newIndex = model.getShapeList().indexOf(shape);
	}

	@Override
	public void unexecute() {
		model.getShapeList().remove(shape);
		model.getShapeList().add(currentIndex, shape);
		frame.repaint();
	}
	
	@Override
	public String toString() {
		return ("ToBack:" + shape.toString() + ";currentIndex:" + currentIndex + ";newIndex:" + newIndex);
	}

}
