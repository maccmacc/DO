package shapes.square;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;
import shapes.point.Point;

public class CommandAddSquare implements Command{
	private DrawingModel model;
	private Square square;
	private LogView logView;
	
	public CommandAddSquare(DrawingModel model, Square square, LogView logView) {
		this.model = model;
		this.square = square;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.addShape(square);
		logView.getDlm().addElement(square.toString());
	}

	@Override
	public void unexecute() {
		model.removeShape(square);
		
	}

}
