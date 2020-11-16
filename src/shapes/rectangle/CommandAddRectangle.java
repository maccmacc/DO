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
		
		if (rectangle.isSelected() && !model.getSelectedShapeList().contains(rectangle)) {
			model.getSelectedShapeList().add(rectangle);
		}
	} 

	@Override
	public void unexecute() {
		model.removeShape(rectangle);
		model.getSelectedShapeList().remove(rectangle);
	}

	@Override
	public String toString() {
		return rectangle.toString();
	}
}
