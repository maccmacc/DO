package shapes.square;

import mvc.model.DrawingModel;
import shapes.Command;
import shapes.point.Point;

public class SelectSquare implements Command{

	private DrawingModel model;
	private Square square;
 
	
	public SelectSquare(DrawingModel model, Square square) {
		this.model = model;
		this.square = square;

	}
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		square.setSelected(true);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		square.setSelected(false);
	}

	@Override
	public String toString() {
		return "Select: " + square.toString();
	}
}

