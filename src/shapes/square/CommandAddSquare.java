package shapes.square;

import mvc.model.DrawingModel;
import mvc.view.LogView;
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
		
		if (square.isSelected() && !model.getSelectedShapeList().contains(square)) {
			model.getSelectedShapeList().add(square);
		}
	}

	@Override
	public void unexecute() {
		model.removeShape(square);
		model.getSelectedShapeList().remove(square);
	}
	
	@Override
	public String toString() {
		return square.toString();
	}

}
