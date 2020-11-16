package shapes.square;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdateSquare implements Command{
	private Square original;
	private Square newState;
	private Square tmp;
	
	public CommandUpdateSquare(Square original, Square newState) {
		this.original = original;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		tmp = new Square(original.getUpperLeftPoint(), original.getSideLength(), original.getColor(), original.getSurfaceColor());
		original.setUpperLeftPoint(newState.getUpperLeftPoint());
		original.setSideLength(newState.getSideLength());
		original.setColor(newState.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
	}
 
	@Override
	public void unexecute() {
		original.setUpperLeftPoint(tmp.getUpperLeftPoint());
		original.setSideLength(tmp.getSideLength());
		original.setColor(tmp.getColor());
		original.setSurfaceColor(tmp.getSurfaceColor());
	}

	@Override
	public String toString() {
		return newState.toString();
	}
}
