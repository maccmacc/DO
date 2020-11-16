package shapes.point;

import mvc.model.DrawingModel;
import shapes.Command;

public class DeselectPoint implements Command{

	private DrawingModel model;
	private Point point;

	
	public DeselectPoint(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;

	}
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		point.setSelected(false);
	}
 
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		point.setSelected(true);
	}

	@Override
	public String toString() {
		return "Deselect: " + point.toString();
	}
}
