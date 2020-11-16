package shapes.hexagon;

import hexagon.Hexagon;
import mvc.model.DrawingModel;
import shapes.Command;

public class SelectHexagon implements Command{

	private DrawingModel model;
	private HexagonAdapter hexagon;

	
	public SelectHexagon(DrawingModel model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;

	}


	@Override
	public void execute()  {
		// TODO Auto-generated method stub
		hexagon.setSelected(true);
	}


	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		hexagon.setSelected(false);

	}
	
	@Override
	public String toString() {
		return "Select: " + hexagon.toString();
	}
}
