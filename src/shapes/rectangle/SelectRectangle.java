package shapes.rectangle;

import mvc.model.DrawingModel;
import shapes.Command;
import shapes.point.Point;

public class SelectRectangle implements Command{

	private DrawingModel model;
	private Rectangle rectangle;

	
	public SelectRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;

	}
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		rectangle.setSelected(true);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		rectangle.setSelected(false);
	}

	@Override
	public String toString() {
		return "Select: " + rectangle.toString();
	}
}

