package shapes.point;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandAddPoint implements Command {

	private DrawingModel model;
	private Point point;
	
	public CommandAddPoint(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;
	}

	@Override
	public void execute() {
		model.addShape(point);
	}

	@Override
	public void unexecute() {
		model.removeShape(point);
	}

}
