package shapes.hexagon;

import hexagon.Hexagon;
import mvc.model.DrawingModel;
import shapes.Command;

public class DeselectHexagon implements Command{

	private DrawingModel model;
	private HexagonAdapter hexagon;

	
	public DeselectHexagon(DrawingModel model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;

	}
	
	@Override
	public void execute()  {
		// TODO Auto-generated method stub
		hexagon.setSelected(false);

	}
 
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		hexagon.setSelected(true);

	}
	
	@Override
	public String toString() {
		return "Deselect: " + hexagon.toString();
	}

}
