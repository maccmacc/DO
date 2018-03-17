package shapes.circle;

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
		tmp = new Circle(original.getCenter(), original.getR(), original.getColor());
		original.setCenter(newState.getCenter());
		original.setR(newState.getR());
		original.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		original.setCenter(tmp.getCenter());
		original.setR(tmp.getR());
		original.setColor(tmp.getColor());
		
	}

}
