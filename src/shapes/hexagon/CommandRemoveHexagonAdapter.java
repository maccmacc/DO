package shapes.hexagon;

import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;

public class CommandRemoveHexagonAdapter implements Command{
	private DrawingModel model;
	private HexagonAdapter hexagonAdapter;
	private LogView logView;
	
	public CommandRemoveHexagonAdapter(DrawingModel model, HexagonAdapter hexagonAdapter, LogView logView) {
		this.model = model;
		this.hexagonAdapter = hexagonAdapter;
		this.logView = logView;
	}

	@Override
	public void execute() {
		model.removeShape(hexagonAdapter);
		logView.getDlm().addElement("Delete:" + hexagonAdapter.toString());
	}

	@Override
	public void unexecute() {
		model.addShape(hexagonAdapter);
		logView.getDlm().addElement("Undo delete:" + hexagonAdapter.toString());
	}

}
