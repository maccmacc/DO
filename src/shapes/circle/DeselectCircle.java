package shapes.circle;

import mvc.model.DrawingModel;
import shapes.Command;

public class DeselectCircle implements Command{

	private DrawingModel model;
	private Circle circle;

	
	public DeselectCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;

	}
	@Override
	public void execute() {
		circle.setSelected(false);
	}

	@Override
	public void unexecute() {
		circle.setSelected(true);
 
	}
	
	@Override
	public String toString() {
		return "Deselect: " + circle.toString();
	}
}
