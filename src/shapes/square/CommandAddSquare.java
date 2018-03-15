package shapes.square;

import mvc.model.DrawingModel;
import shapes.Command;
import shapes.point.Point;

public class CommandAddSquare implements Command{
	private DrawingModel model;
	private Square square;
	public CommandAddSquare(DrawingModel model, Square square) {
		this.model = model;
		this.square = square;
	}

	@Override
	public void execute() {
		model.addShape(square);
		
	}

	@Override
	public void unexecute() {
		model.removeShape(square);
		
	}

}
