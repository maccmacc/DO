package shapes.point;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdatePoint implements Command {
	private Point original;
	private Point newState;
	private Point tmp;
	private LogView logView;
	
	public CommandUpdatePoint(Point original, Point newState, LogView logView) {
		this.original = original;
		this.newState = newState;
		this.logView = logView;
	}
	
	@Override
	public void execute() {
		tmp = new Point(original.getX(), original.getY(), original.getColor());
		original.setX(newState.getX());
		original.setY(newState.getY());
		original.setColor(newState.getColor());
		logView.getDlm().addElement("Update " + original.toString());
	}

	@Override
	public void unexecute() {
		original.setX(tmp.getX());
		original.setY(tmp.getY());
		original.setColor(tmp.getColor());
		logView.getDlm().addElement("Undo update " + original.toString());
	}

}
