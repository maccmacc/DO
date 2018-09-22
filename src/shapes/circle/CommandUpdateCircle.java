package shapes.circle;

import mvc.view.LogView;
import shapes.Command;
import shapes.point.Point;

public class CommandUpdateCircle implements Command{
	private Circle original;
	private Circle newState;
	private Circle tmp;
	
	public CommandUpdateCircle(Circle original, Circle newState) {
		this.original = original;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		tmp = new Circle(original.getCenter(), original.getR(), original.getColor(), original.getSurfaceColor());
		original.setCenter(newState.getCenter());
		original.setR(newState.getR());
		original.setColor(newState.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
	}

	@Override
	public void unexecute() {
		original.setCenter(tmp.getCenter());
		original.setR(tmp.getR());
		original.setColor(tmp.getColor());
		original.setSurfaceColor(tmp.getSurfaceColor());
	}

	@Override
	public String toString() {
		return newState.toString();
	}
}
