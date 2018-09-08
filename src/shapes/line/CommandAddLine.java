package shapes.line;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandAddLine implements Command{
	private DrawingModel model;
	private Line line;
	private LogView logView;
	
	public CommandAddLine(DrawingModel model, Line line, LogView logView) {
		this.model = model;
		this.line = line;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.addShape(line);
		logView.getDlm().addElement("Add:" + line.toString());
	}

	@Override
	public void unexecute() {
		model.removeShape(line);
		logView.getDlm().addElement("Undo:" + line.toString());
	}

}
