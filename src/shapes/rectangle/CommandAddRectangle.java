package shapes.rectangle;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;
import shapes.square.Square;

public class CommandAddRectangle implements Command{
	private DrawingModel model;
	private Rectangle rectangle;
	private LogView logView;
	
	public CommandAddRectangle(DrawingModel model, Rectangle rectangle, LogView logView) {
		this.model = model;
		this.rectangle = rectangle;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.addShape(rectangle);
		logView.getDlm().addElement(rectangle.toString());
	}

	@Override
	public void unexecute() {
		model.removeShape(rectangle);
		
	}

}
