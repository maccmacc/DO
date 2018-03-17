package shapes.rectangle;

import mvc.model.DrawingModel;
import shapes.Command;

public class CommandRemoveRectangle implements Command{
	
	private DrawingModel model;
	private Rectangle rectangle;
	public CommandRemoveRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.removeShape(rectangle);
	}

	@Override
	public void unexecute() {
		model.addShape(rectangle);
	}

}
