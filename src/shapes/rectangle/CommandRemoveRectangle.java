package shapes.rectangle;

import mvc.model.DrawingModel;
import mvc.view.LogView;
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
		 
		if (rectangle.isSelected() && !model.getSelectedShapeList().contains(rectangle)) {
			model.getSelectedShapeList().add(rectangle);
		}
	}
	
	@Override
	public String toString() {
		return rectangle.toString();
	}
}


