package utility;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.circle.Circle;
import shapes.circle.CommandAddCircle;
import shapes.circle.CommandUpdateCircle;
import shapes.hexagon.CommandAddHexagonAdapter;
import shapes.hexagon.CommandUpdateHexagonAdapter;
import shapes.hexagon.HexagonAdapter;
import shapes.line.CommandAddLine;
import shapes.line.CommandUpdateLine;
import shapes.line.Line;
import shapes.point.CommandAddPoint;
import shapes.point.CommandUpdatePoint;
import shapes.point.Point;
import shapes.rectangle.CommandAddRectangle;
import shapes.rectangle.CommandUpdateRectangle;
import shapes.rectangle.Rectangle;
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
			checkOperation(parts, frame, model);
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
			checkOperation(parts, frame, model);
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
			checkOperation(parts, frame, model);
		}
	}
	
	public static void decodeLine(Line line, String parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts.contains("Add")) {
			CommandAddLine add = new CommandAddLine(model, line, logView);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts.contains("Update")) {
			CommandUpdateLine updateLine = new CommandUpdateLine((Line)model.getSelectedShapeList().get(0), line, logView);
			updateLine.execute();
			model.getUndoStack().offerLast(updateLine);
		} else if (parts.contains("Select")) {
			frame.getButtonController().goThroughShapesList(line.getStartPoint().getX(), line.getStartPoint().getY());
		} else {
			checkOperation(parts, frame, model);
		}
	}
	
	public static void decodeRectangle(Rectangle rectangle, String parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts.contains("Add")) {
			CommandAddRectangle add = new CommandAddRectangle(model, rectangle, logView);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts.contains("Update")) {
			CommandUpdateRectangle updateRectangle = new CommandUpdateRectangle((Rectangle)model.getSelectedShapeList().get(0), rectangle, logView);
			updateRectangle.execute();
			model.getUndoStack().offerLast(updateRectangle);
		} else if (parts.contains("Select")) {
			frame.getButtonController().goThroughShapesList(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY());
		} else {
			checkOperation(parts, frame, model);
		}
	}
	
	public static void decodeHexagon(HexagonAdapter hexagonAdapter, String parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts.contains("Add")) {
			CommandAddHexagonAdapter add = new CommandAddHexagonAdapter(model, hexagonAdapter, logView);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts.contains("Update")) {
			CommandUpdateHexagonAdapter updateHexagonAdapter = 
					new CommandUpdateHexagonAdapter((HexagonAdapter)model.getSelectedShapeList().get(0), hexagonAdapter, logView);
			updateHexagonAdapter.execute();
			model.getUndoStack().offerLast(updateHexagonAdapter);
		} else if (parts.contains("Select")) {
			frame.getButtonController().goThroughShapesList(hexagonAdapter.getHexagon().getX(), hexagonAdapter.getHexagon().getY());
		} else {
			checkOperation(parts, frame, model);
		}
	}
	
	public static void checkOperation (String parts, DrawingFrame frame, DrawingModel model) {
		if (parts.contains("Delete")) {
			frame.getButtonController().deleteButtonClickedHandler();
		} else if (parts.contains("Unselect")) {
			frame.getButtonController().unselectShapes();
		} else if (parts.contains("Undo")) {
			frame.getButtonController().onUndoButtonClicked();
		}
	}
	
}
