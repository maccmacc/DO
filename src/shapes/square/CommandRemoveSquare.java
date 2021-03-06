package shapes.square;

import mvc.model.DrawingModel;
import mvc.view.LogView;
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
		
		if (square.isSelected() && !model.getSelectedShapeList().contains(square)) {
			model.getSelectedShapeList().add(square);
		}
	} 
	
	@Override
	public String toString() {
		return square.toString();
	}

}
