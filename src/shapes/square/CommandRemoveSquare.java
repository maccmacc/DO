package shapes.square;

import mvc.model.DrawingModel;
import shapes.Command;

public class CommandRemoveSquare implements Command{
	private DrawingModel model;
	private Square square;
	public CommandRemoveSquare(DrawingModel model, Square square) {
		this.model = model;
		this.square = square;
	}

	@Override
	public void execute() {
		model.removeShape(square);
	}

	@Override
	public void unexecute() {
		model.addShape(square);
	}

}
