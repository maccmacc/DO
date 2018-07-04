package shapes.circle;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemoveCircle implements Command{

	private DrawingModel model;
	private Circle circle;
	private LogView logView;
	
	public CommandRemoveCircle(DrawingModel model, Circle circle,LogView logView) {
		this.model = model;
		this.circle = circle;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.removeShape(circle);
		logView.getDlm().addElement("Delete " + circle.toString());
	}

	@Override
	public void unexecute() {
		model.addShape(circle);
		logView.getDlm().addElement("Undo delete " + circle.toString());
	}
}
