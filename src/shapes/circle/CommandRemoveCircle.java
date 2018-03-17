package shapes.circle;

import mvc.model.DrawingModel;
import shapes.Command;

public class CommandRemoveCircle implements Command{

	private DrawingModel model;
	private Circle circle;
	public CommandRemoveCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.removeShape(circle);
	}

	@Override
	public void unexecute() {
		model.addShape(circle);
	}
}
