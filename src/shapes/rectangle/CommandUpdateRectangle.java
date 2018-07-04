package shapes.rectangle;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdateRectangle implements Command{
	private Rectangle original;
	private Rectangle newState;
	private Rectangle tmp;
	private LogView logView;
	
	public CommandUpdateRectangle(Rectangle original, Rectangle newState, LogView logView) {
		this.original = original;
		this.newState = newState;
		this.logView = logView;
	}
	
	@Override
	public void execute() {
		tmp = new Rectangle(original.getUpperLeftPoint(), original.getSideLength(), original.getWidth(), original.getColor());
		original.setUpperLeftPoint(newState.getUpperLeftPoint());
		original.setSideLength(newState.getSideLength());
		original.setWidth(newState.getWidth());
		original.setColor(newState.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
		logView.getDlm().addElement("Update " + original.toString());
	}

	@Override
	public void unexecute() {
		original.setUpperLeftPoint(tmp.getUpperLeftPoint());
		original.setSideLength(tmp.getSideLength());
		original.setWidth(tmp.getWidth());
		original.setColor(tmp.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
		logView.getDlm().addElement("Undo update " + original.toString());
	}

}
