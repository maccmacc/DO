package shapes.rectangle;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemoveRectangle implements Command{
	
	private DrawingModel model;
	private Rectangle rectangle;
	private LogView logView;
	
	public CommandRemoveRectangle(DrawingModel model, Rectangle rectangle, LogView logView) {
		this.model = model;
		this.rectangle = rectangle;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.removeShape(rectangle);
		logView.getDlm().addElement("Delete:" + rectangle.toString());
	}

	@Override
	public void unexecute() {
		model.addShape(rectangle);
		logView.getDlm().addElement("Undo delete:" + rectangle.toString());
	}
	
}


