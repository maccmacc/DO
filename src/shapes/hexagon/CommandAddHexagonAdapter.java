package shapes.hexagon;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandAddHexagonAdapter implements Command{
	private DrawingModel model;
	private HexagonAdapter hexagonAdapter;
	private LogView logView;
	public CommandAddHexagonAdapter(DrawingModel model, HexagonAdapter hexagonAdapter, LogView logView) {
		this.model = model;
		this.hexagonAdapter = hexagonAdapter;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.addShape(hexagonAdapter);
		logView.getDlm().addElement("Add:" + hexagonAdapter.toString());
	}

	@Override
	public void unexecute() {
		model.removeShape(hexagonAdapter);
		logView.getDlm().addElement("Undo:" + hexagonAdapter.toString());
	}

}
