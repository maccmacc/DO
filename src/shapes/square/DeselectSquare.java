package shapes.square;

import mvc.model.DrawingModel;
import shapes.Command;

public class DeselectSquare implements Command{

	private DrawingModel model;
	private Square square;

	
	public DeselectSquare(DrawingModel model, Square square) {
		this.model = model;
		this.square = square;

	}
	
	 
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		square.setSelected(false);
	}
  
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		square.setSelected(true);
	}

	@Override
	public String toString() {
		return "Select: " + square.toString();
	}
}
