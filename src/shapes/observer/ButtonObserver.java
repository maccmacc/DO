package shapes.observer;

import mvc.view.ButtonView;
import mvc.view.ButtonViewRight;

public class ButtonObserver implements Observer {

	ButtonView view;
	ButtonViewRight buttonViewRight;
	
	public ButtonObserver(ButtonView view, ButtonViewRight buttonViewRight) {
		this.view = view;
		this.buttonViewRight = buttonViewRight;
	}
	
	@Override
	public void update(int numberOfSelectedShapes, int numberOfShapesInUndo, int numberOfShapesInRedo) {
		
		view.getBtnUndo().setEnabled(numberOfShapesInUndo != 0);
		view.getBtnRedo().setEnabled(numberOfShapesInRedo != 0);
		
		if (numberOfSelectedShapes == 1) {
			view.getBtnUpdate().setEnabled(true);
			view.getBtnDelete().setEnabled(true);
			buttonViewRight.getBtnBringToFront().setEnabled(true);
			buttonViewRight.getBtnBringToBack().setEnabled(true);
			buttonViewRight.getBtnToFront().setEnabled(true);
			buttonViewRight.getBtnToBack().setEnabled(true);
		}
		else if (numberOfSelectedShapes > 1) {
			view.getBtnUpdate().setEnabled(false);
			buttonViewRight.getBtnBringToFront().setEnabled(false);
			buttonViewRight.getBtnBringToBack().setEnabled(false);
			buttonViewRight.getBtnToFront().setEnabled(false);
			buttonViewRight.getBtnToBack().setEnabled(false);
		}
		else if (numberOfSelectedShapes == 0) {
			view.getBtnDelete().setEnabled(false);
			view.getBtnUpdate().setEnabled(false);
			buttonViewRight.getBtnBringToFront().setEnabled(false);
			buttonViewRight.getBtnBringToBack().setEnabled(false);
			buttonViewRight.getBtnToFront().setEnabled(false);
			buttonViewRight.getBtnToBack().setEnabled(false);
		}
	}
}
