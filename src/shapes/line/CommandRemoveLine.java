package shapes.line;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemoveLine implements Command{

	private DrawingModel model;
	private Line line;
	private LogView logView;
	
	public CommandRemoveLine(DrawingModel model, Line line, LogView logView) {
		this.model = model;
		this.line = line;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.removeShape(line);
		logView.getDlm().addElement("Delete:" + line.toString());
	}

	@Override
	public void unexecute() {
		model.addShape(line);
		logView.getDlm().addElement("Undo delete:" + line.toString());
	}
}
