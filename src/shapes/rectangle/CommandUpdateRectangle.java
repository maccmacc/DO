package shapes.rectangle;

import shapes.Command;

public class CommandUpdateRectangle implements Command{
	private Rectangle original;
	private Rectangle newState;
	private Rectangle tmp;
	
	public CommandUpdateRectangle(Rectangle original, Rectangle newState) {
		this.original = original;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		tmp = new Rectangle(original.getUpperLeftPoint(), original.getSideLength(), original.getWidth(), original.getColor());
		original.setUpperLeftPoint(newState.getUpperLeftPoint());
		original.setSideLength(newState.getSideLength());
		original.setWidth(newState.getWidth());
		original.setColor(newState.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
	}

	@Override
	public void unexecute() {
		original.setUpperLeftPoint(tmp.getUpperLeftPoint());
		original.setSideLength(tmp.getSideLength());
		original.setWidth(tmp.getWidth());
		original.setColor(tmp.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
	}

}
