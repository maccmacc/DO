package shapes.circle;


import mvc.model.DrawingModel;



import shapes.Command;

public class SelectCircle implements Command{

	private DrawingModel model;
	private Circle circle;

	
	public SelectCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;

	}
	@Override
	public void execute() {
		circle.setSelected(true);

	}

	@Override
	public void unexecute() {
		circle.setSelected(false);
 
	}
	
	@Override
	public String toString() {
		return "Select: " + circle.toString();
	}
}
