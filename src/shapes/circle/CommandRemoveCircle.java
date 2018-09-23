package shapes.circle;

import mvc.model.DrawingModel;
import mvc.view.LogView;
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
		
		if (circle.isSelected() && !model.getSelectedShapeList().contains(circle)) {
			model.getSelectedShapeList().add(circle);
		}
	}
	
	@Override
	public String toString() {
		return circle.toString();
	}
}
