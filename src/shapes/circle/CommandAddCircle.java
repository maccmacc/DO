package shapes.circle;

import mvc.model.DrawingModel;
import shapes.Command;
import shapes.square.Square;

public class CommandAddCircle implements Command{
	private DrawingModel model;
	private Circle circle;
	public CommandAddCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.addShape(circle);
		
	}

	@Override
	public void unexecute() {
		model.removeShape(circle);
		
	}

}