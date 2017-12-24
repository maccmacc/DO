package mainApp;
import drawingFrame.DrawingFrame;
import mvc.controller.DrawingController;
import mvc.model.DrawingModel;

public class DrawingApp {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);
		frame.getView().setModel(model);
	}

}
