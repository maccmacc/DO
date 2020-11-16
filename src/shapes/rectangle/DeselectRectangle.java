package shapes.rectangle;

import mvc.model.DrawingModel;
import shapes.Command;

public class DeselectRectangle implements Command{

	private DrawingModel model;
	private Rectangle rectangle;

	
	public DeselectRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;

	}
	
	
	@Override
	public void execute()  {
		// TODO Auto-generated method stub
		rectangle.setSelected(false);
	}
 
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		rectangle.setSelected(true);
	}

	@Override
	public String toString() {
		return "Select: " + rectangle.toString();
	}
}
