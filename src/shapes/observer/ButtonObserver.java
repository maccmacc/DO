package shapes.observer;

import mvc.view.ButtonView;

public class ButtonObserver implements Observer{

	ButtonView view;
	
	public ButtonObserver(ButtonView view) {
		this.view = view;
	}
	
	@Override
	public void update(int numberOfSelectedShapes) {
		if (numberOfSelectedShapes == 1) {
			view.getBtnUpdate().setEnabled(true);
			view.getBtnDelete().setEnabled(true);
		}
		else if (numberOfSelectedShapes > 1) {
			view.getBtnUpdate().setEnabled(false);
		}
	}
}
