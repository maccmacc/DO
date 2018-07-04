package shapes.circle;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandAddCircle implements Command {
	private DrawingModel model;
	private Circle circle;
	private LogView logView;
	public CommandAddCircle(DrawingModel model, Circle circle, LogView logView) {
		this.model = model;
		this.circle = circle;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.addShape(circle);
		logView.getDlm().addElement("Add " + circle.toString());
	}

	@Override
	public void unexecute() {
		model.removeShape(circle);
		logView.getDlm().addElement("Undo add " + circle.toString());
	}

}
