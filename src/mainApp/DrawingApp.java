package mainApp;

import drawingFrame.DrawingFrame;
import mvc.controller.ButtonController;
import mvc.controller.DrawingController;
import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.observer.ButtonObserver;

public class DrawingApp {

  public static void main(String[] args) {
    DrawingModel model = new DrawingModel();
    DrawingFrame frame = new DrawingFrame();
    LogView logView = new LogView();
    frame.setLogView(logView);
    DrawingController drawingController = new DrawingController(model, frame, logView);
    frame.setDrawingController(drawingController);
    frame.getView().setModel(model);
    ButtonController buttonController = new ButtonController(model, frame, logView);
    frame.setButtonController(buttonController);
    ButtonObserver buttonObs = new ButtonObserver(frame.getButtonView(), frame.getButtonViewRight());
    model.addObserver(buttonObs);
  }
}
