package shapes.point;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandAddPoint implements Command {

	private DrawingModel model;
	private Point point;
	private LogView logView;
	
	public CommandAddPoint(DrawingModel model, Point point, LogView logView) {
		this.model = model;
		this.point = point;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.addShape(point);
		logView.getDlm().addElement("Add:" + point.toString());
	}

	@Override
	public void unexecute() {
		model.removeShape(point);
		logView.getDlm().addElement("Undo:" + point.toString());
	}

}
