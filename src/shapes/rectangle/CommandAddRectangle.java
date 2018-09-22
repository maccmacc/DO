package shapes.rectangle;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;
import shapes.square.Square;

public class CommandAddRectangle implements Command{
	private DrawingModel model;
	private Rectangle rectangle;
	
	public CommandAddRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.addShape(rectangle);
	}

	@Override
	public void unexecute() {
		model.removeShape(rectangle);
	}

	@Override
	public String toString() {
		return rectangle.toString();
	}
}
