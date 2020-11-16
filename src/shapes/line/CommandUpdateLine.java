package shapes.line;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdateLine implements Command {
	private Line original;
	private Line newState;
	private Line tmp;
	
	public CommandUpdateLine(Line original, Line newState) {
		this.original = original;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		tmp = new Line(original.getStartPoint(), original.getEndPoint(), original.getColor());
		original.setStartPoint(newState.getStartPoint());
		original.setEndPoint(newState.getEndPoint());
		original.setColor(newState.getColor());
	}
 
	@Override
	public void unexecute() {
		original.setStartPoint(tmp.getStartPoint());
		original.setEndPoint(tmp.getEndPoint());
		original.setColor(tmp.getColor());
	}

	@Override
	public String toString() {
		return newState.toString();
	}
}
