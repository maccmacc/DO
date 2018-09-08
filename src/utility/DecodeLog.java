package utility;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Shape;
import shapes.circle.Circle;
import shapes.circle.CommandAddCircle;
import shapes.circle.CommandUpdateCircle;
import shapes.point.CommandAddPoint;
import shapes.point.CommandUpdatePoint;
import shapes.point.Point;
import shapes.square.CommandAddSquare;
import shapes.square.CommandUpdateSquare;
import shapes.square.Square;

public class DecodeLog {

	public static void decodePoint(Point point, String parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts.contains("Add")) {
			CommandAddPoint add = new CommandAddPoint(model, point, logView);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts.contains("Update")) {
			CommandUpdatePoint updatePoint = new CommandUpdatePoint((Point)model.getSelectedShapeList().get(0), point, logView);
			updatePoint.execute();
			model.getUndoStack().offerLast(updatePoint);
		} else if (parts.contains("Select")) {
			frame.getButtonController().goThroughShapesList(point.getX(), point.getY());
		} else {
			
		}
	}
	
	public static void decodeCircle(Circle circle, String parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts.contains("Add")) {
			CommandAddCircle add = new CommandAddCircle(model, circle, logView);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts.contains("Update")) {
			CommandUpdateCircle updateCircle = new CommandUpdateCircle((Circle)model.getSelectedShapeList().get(0), circle, logView);
			updateCircle.execute();
			model.getUndoStack().offerLast(updateCircle);
		} else if (parts.contains("Select")) {
			frame.getButtonController().goThroughShapesList(circle.getCenter().getX(), circle.getCenter().getX());
		} else {
			
		}
	}

	public static void decodeSquare(Square square, String parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts.contains("Add")) {
			CommandAddSquare add = new CommandAddSquare(model, square, logView);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts.contains("Update")) {
			CommandUpdateSquare updateSquare = new CommandUpdateSquare((Square)model.getSelectedShapeList().get(0), square, logView);
			updateSquare.execute();
			model.getUndoStack().offerLast(updateSquare);
		} else if (parts.contains("Select")) {
			frame.getButtonController().goThroughShapesList(square.getUpperLeftPoint().getX(), square.getUpperLeftPoint().getY());
		} else {
			
		}
	}
	
	public void CheckOperation (String parts, DrawingFrame frame, DrawingModel model, LogView view, Shape shape) {
		if (parts.contains("Delete")) {
			frame.getButtonController().deleteButtonClickedHandler();
		}
	}
	
}
