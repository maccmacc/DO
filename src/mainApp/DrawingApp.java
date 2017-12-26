package mainApp;
import drawingFrame.DrawingFrame;
import mvc.controller.ButtonController;
import mvc.controller.DrawingController;
import mvc.model.DrawingModel;

public class DrawingApp {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		DrawingController drawingController = new DrawingController(model, frame);
		frame.setDrawingController(drawingController);
		frame.getView().setModel(model);
		ButtonController buttonController = new ButtonController(model, frame);
		frame.setButtonController(buttonController);
	}

}
