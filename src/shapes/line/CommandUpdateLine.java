package shapes.line;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdateLine implements Command {
	private Line original;
	private Line newState;
	private Line tmp;
	private LogView logView;
	
	public CommandUpdateLine(Line original, Line newState, LogView logView) {
		this.original = original;
		this.newState = newState;
		this.logView = logView;
	}
	
	@Override
	public void execute() {
		tmp = new Line(original.getStartPoint(), original.getEndPoint(), original.getColor());
		original.setStartPoint(newState.getStartPoint());
		original.setEndPoint(newState.getEndPoint());
		original.setColor(newState.getColor());
		logView.getDlm().addElement("Update " + original.toString());
	}

	@Override
	public void unexecute() {
		original.setStartPoint(tmp.getStartPoint());
		original.setEndPoint(tmp.getEndPoint());
		original.setColor(tmp.getColor());
		logView.getDlm().addElement("Undo update " + original.toString());
	}

}
