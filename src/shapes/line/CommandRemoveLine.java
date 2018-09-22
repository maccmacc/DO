package shapes.line;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemoveLine implements Command{

	private DrawingModel model;
	private Line line;
	
	public CommandRemoveLine(DrawingModel model, Line line) {
		this.model = model;
		this.line = line;
	}

	@Override
	public void execute() {
		model.removeShape(line);
	}

	@Override
	public void unexecute() {
		model.addShape(line);
	}
	
	@Override
	public String toString() {
		return line.toString();
	}
}
