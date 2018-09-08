package shapes.point;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemovePoint implements Command {

	private DrawingModel model;
	private Point point;
	private LogView logView;
	
	public CommandRemovePoint(DrawingModel model, Point point,LogView logView) {
		this.model = model;
		this.point = point;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.removeShape(point);
		logView.getDlm().addElement("Delete:" + point.toString());
	}

	@Override
	public void unexecute() {
		model.addShape(point);
		logView.getDlm().addElement("Undo delete:" + point.toString());
	}

}
