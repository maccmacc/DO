package shapes.hexagon;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandAddHexagonAdapter implements Command{
	private DrawingModel model;
	private HexagonAdapter hexagonAdapter;
	public CommandAddHexagonAdapter(DrawingModel model, HexagonAdapter hexagonAdapter) {
		this.model = model;
		this.hexagonAdapter = hexagonAdapter;
	}

	@Override
	public void execute() {
		model.addShape(hexagonAdapter);
	}

	@Override
	public void unexecute() {
		model.removeShape(hexagonAdapter);
	}

}
