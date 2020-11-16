package shapes.point;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemovePoint implements Command {

	private DrawingModel model;
	private Point point;
	
	public CommandRemovePoint(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;
	}

	@Override
	public void execute() {
		model.removeShape(point);
	}

	@Override
	public void unexecute() {
		model.addShape(point);
		 
		if (point.isSelected() && !model.getSelectedShapeList().contains(point)) {
			model.getSelectedShapeList().add(point);
		}
	}
	
	@Override
	public String toString() {
		return point.toString();
	}

}
