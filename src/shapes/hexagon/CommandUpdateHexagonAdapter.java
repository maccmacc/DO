package shapes.hexagon;

import mvc.view.LogView;
import shapes.Command;

public class CommandUpdateHexagonAdapter implements Command {
	private HexagonAdapter original;
	private HexagonAdapter newState;
	private HexagonAdapter tmp;
	private LogView logView;
	
	public CommandUpdateHexagonAdapter(HexagonAdapter original, HexagonAdapter newState, LogView logView) {
		this.original = original;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		tmp = new HexagonAdapter(original.getHexagon(), original.getHexagon().getBorderColor(), original.getHexagon().getAreaColor());
		original.getHexagon().setX(newState.getHexagon().getX());
		original.getHexagon().setY(newState.getHexagon().getY());
		original.getHexagon().setR(newState.getHexagon().getR());
		original.getHexagon().setBorderColor(newState.getHexagon().getBorderColor());
		original.getHexagon().setAreaColor(newState.getHexagon().getAreaColor());
		logView.getDlm().addElement("Update " + original.toString());
	}

	@Override
	public void unexecute() {
		original.getHexagon().setX(tmp.getHexagon().getX());
		original.getHexagon().setY(tmp.getHexagon().getY());
		original.getHexagon().setR(tmp.getHexagon().getR());
		original.getHexagon().setBorderColor(tmp.getHexagon().getBorderColor());
		original.getHexagon().setAreaColor(tmp.getHexagon().getAreaColor());
		logView.getDlm().addElement("Undo update " + original.toString());
	}


}
