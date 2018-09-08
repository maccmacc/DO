package shapes.square;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdateSquare implements Command{
	private Square original;
	private Square newState;
	private Square tmp;
	private LogView logView;
	
	public CommandUpdateSquare(Square original, Square newState, LogView logView) {
		this.original = original;
		this.newState = newState;
		this.logView = logView;
	}
	
	@Override
	public void execute() {
		tmp = new Square(original.getUpperLeftPoint(), original.getSideLength(), original.getColor());
		original.setUpperLeftPoint(newState.getUpperLeftPoint());
		original.setSideLength(newState.getSideLength());
		original.setColor(newState.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
		logView.getDlm().addElement("Update:" + original.toString());
	}

	@Override
	public void unexecute() {
		original.setUpperLeftPoint(tmp.getUpperLeftPoint());
		original.setSideLength(tmp.getSideLength());
		original.setColor(tmp.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
		logView.getDlm().addElement("Update:" + original.toString());
	}

}
