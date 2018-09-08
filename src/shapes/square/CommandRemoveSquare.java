package shapes.square;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemoveSquare implements Command{
	private DrawingModel model;
	private Square square;
	private LogView logView;
	
	public CommandRemoveSquare(DrawingModel model, Square square, LogView logView) {
		this.model = model;
		this.square = square;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.removeShape(square);
		logView.getDlm().addElement("Delete:" + square.toString());
	}

	@Override
	public void unexecute() {
		model.addShape(square);
		logView.getDlm().addElement("Undo delete:" + square.toString());
	}

}
