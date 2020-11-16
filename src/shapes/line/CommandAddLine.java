package shapes.line;

import mvc.model.DrawingModel;
import mvc.view.LogView;
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
		
		if (line.isSelected() && !model.getSelectedShapeList().contains(line)) {
			model.getSelectedShapeList().add(line);
		}
	}
 
	@Override
	public void unexecute() {
		model.removeShape(line);
		model.getSelectedShapeList().remove(line);
	}
	
	@Override
	public String toString() {
		return line.toString();
	}

}
