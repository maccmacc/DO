package shapes.hexagon;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemoveHexagonAdapter implements Command{
	private DrawingModel model;
	private HexagonAdapter hexagonAdapter;
	
	public CommandRemoveHexagonAdapter(DrawingModel model, HexagonAdapter hexagonAdapter) {
		this.model = model;
		this.hexagonAdapter = hexagonAdapter;
	}

	@Override
	public void execute() {
		model.removeShape(hexagonAdapter);
	}

	@Override
	public void unexecute() {
		model.addShape(hexagonAdapter);
	}
	
	@Override
	public String toString() {
		return hexagonAdapter.toString();
	}

}
