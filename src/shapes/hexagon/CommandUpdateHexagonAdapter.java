package shapes.hexagon;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdateHexagonAdapter implements Command {
	private HexagonAdapter original;
	private HexagonAdapter newState;
	private HexagonAdapter tmp;
	
	public CommandUpdateHexagonAdapter(HexagonAdapter original, HexagonAdapter newState) {
		this.original = original;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		tmp = new HexagonAdapter(original.getHexagon(), original.getColor(), original.getSurfaceColor());
		original.getHexagon().setX(newState.getHexagon().getX());
		original.getHexagon().setY(newState.getHexagon().getY());
		original.getHexagon().setR(newState.getHexagon().getR());
		original.setColor(newState.getColor());
		original.setSurfaceColor(newState.getSurfaceColor());
	}

	@Override
	public void unexecute() {
		original.getHexagon().setX(tmp.getHexagon().getX());
		original.getHexagon().setY(tmp.getHexagon().getY());
		original.getHexagon().setR(tmp.getHexagon().getR());
		original.setColor(tmp.getColor());
		original.setSurfaceColor(tmp.getSurfaceColor());
	}


}
