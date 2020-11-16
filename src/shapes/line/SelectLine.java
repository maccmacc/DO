package shapes.line;

import mvc.model.DrawingModel;
import shapes.Command;

public class SelectLine implements Command{

	private DrawingModel model;
	private Line line;

	
	public SelectLine(DrawingModel model, Line line) {
		this.model = model;
		this.line = line;

	}
	
	
	
	@Override
	public void execute()  {
		// TODO Auto-generated method stub
		line.setSelected(true);
	}
 
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		line.setSelected(false);

	}

	@Override
	public String toString() {
		return "Select: " + line.toString();
	}
}
