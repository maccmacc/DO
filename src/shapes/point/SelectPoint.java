package shapes.point;


import mvc.model.DrawingModel;



import shapes.Command;

public class SelectPoint implements Command{

	private DrawingModel model;
	private Point point;

	
	public SelectPoint(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;

	}
	@Override
	public void execute() {
		point.setSelected(true);

	}

	@Override
	public void unexecute() {
		point.setSelected(false);

	}
	
	@Override
	public String toString() {
		return "Select: " + point.toString();
	}
}
