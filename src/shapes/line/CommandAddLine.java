package shapes.line;

import mvc.model.DrawingModel;
import shapes.Command;

public class CommandAddLine implements Command{
	private DrawingModel model;
	private Line line;
	public CommandAddLine(DrawingModel model, Line line) {
		this.model = model;
		this.line = line;
	}

	@Override
	public void execute() {
		model.addShape(line);
		
	}

	@Override
	public void unexecute() {
		model.removeShape(line);
		
	}

}
