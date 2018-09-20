package strategy;

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

	public static void decodePoint(Point point, String[] parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts[0].contains("Add")) {
			CommandAddPoint add = new CommandAddPoint(model, point);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts[0].contains("Update")) {
			CommandUpdatePoint updatePoint = new CommandUpdatePoint((Point)model.getSelectedShapeList().get(0), point);
			updatePoint.execute();
			model.getUndoStack().offerLast(updatePoint);
		} else if (parts[0].contains("Select")) {
			String[] selectedCoordinates = parts[2].split("=");
			int sX = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf("(")+1, selectedCoordinates[1].indexOf(",")));
			int sY = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf(",")+1, selectedCoordinates[1].indexOf(")")));
			frame.getButtonController().goThroughShapesList(sX, sY);
		} else {
			checkOperation(parts[0], frame, model);
		}
	}
	
	public static void decodeCircle(Circle circle, String[] parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts[0].contains("Add")) {
			CommandAddCircle add = new CommandAddCircle(model, circle);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts[0].contains("Update")) {
			CommandUpdateCircle updateCircle = new CommandUpdateCircle((Circle)model.getSelectedShapeList().get(0), circle);
			updateCircle.execute();
			model.getUndoStack().offerLast(updateCircle);
		} else if (parts[0].contains("Select")) {
			String[] selectedCoordinates = parts[4].split("=");
			int sX = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf("(")+1, selectedCoordinates[1].indexOf(",")));
			int sY = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf(",")+1, selectedCoordinates[1].indexOf(")")));
			frame.getButtonController().goThroughShapesList(sX, sY);
		} else {
			checkOperation(parts[0], frame, model);
		}
	}

	public static void decodeSquare(Square square, String[] parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts[0].contains("Add")) {
			CommandAddSquare add = new CommandAddSquare(model, square);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts[0].contains("Update")) {
			CommandUpdateSquare updateSquare = new CommandUpdateSquare((Square)model.getSelectedShapeList().get(0), square);
			updateSquare.execute();
			model.getUndoStack().offerLast(updateSquare);
		} else if (parts[0].contains("Select")) {
			String[] selectedCoordinates = parts[4].split("=");
			int sX = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf("(")+1, selectedCoordinates[1].indexOf(",")));
			int sY = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf(",")+1, selectedCoordinates[1].indexOf(")")));
			frame.getButtonController().goThroughShapesList(sX, sY);
		} else {
			checkOperation(parts[0], frame, model);
		}
	}
	
	public static void decodeLine(Line line, String[] parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts[0].contains("Add")) {
			CommandAddLine add = new CommandAddLine(model, line);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts[0].contains("Update")) {
			CommandUpdateLine updateLine = new CommandUpdateLine((Line)model.getSelectedShapeList().get(0), line);
			updateLine.execute();
			model.getUndoStack().offerLast(updateLine);
		} else if (parts[0].contains("Select")) {
			String[] selectedCoordinates = parts[3].split("=");
			int sX = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf("(")+1, selectedCoordinates[1].indexOf(",")));
			int sY = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf(",")+1, selectedCoordinates[1].indexOf(")")));
			frame.getButtonController().goThroughShapesList(sX, sY);
		} else {
			checkOperation(parts[0], frame, model);
		}
	}
	
	public static void decodeRectangle(Rectangle rectangle, String[] parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts[0].contains("Add")) {
			CommandAddRectangle add = new CommandAddRectangle(model, rectangle);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts[0].contains("Update")) {
			CommandUpdateRectangle updateRectangle = new CommandUpdateRectangle((Rectangle)model.getSelectedShapeList().get(0), rectangle);
			updateRectangle.execute();
			model.getUndoStack().offerLast(updateRectangle);
		} else if (parts[0].contains("Select")) {
			String[] selectedCoordinates = parts[5].split("=");
			int sX = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf("(")+1, selectedCoordinates[1].indexOf(",")));
			int sY = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf(",")+1, selectedCoordinates[1].indexOf(")")));
			frame.getButtonController().goThroughShapesList(sX, sY);
		} else {
			checkOperation(parts[0], frame, model);
		}
	}
	
	public static void decodeHexagon(HexagonAdapter hexagonAdapter, String[] parts, DrawingFrame frame, DrawingModel model, LogView logView) {
		if (parts[0].contains("Add")) {
			CommandAddHexagonAdapter add = new CommandAddHexagonAdapter(model, hexagonAdapter);
			add.execute();
			model.getUndoStack().offerLast(add);
		} else if (parts[0].contains("Update")) {
			CommandUpdateHexagonAdapter updateHexagonAdapter = 
					new CommandUpdateHexagonAdapter((HexagonAdapter)model.getSelectedShapeList().get(0), hexagonAdapter);
			updateHexagonAdapter.execute();
			model.getUndoStack().offerLast(updateHexagonAdapter);
		} else if (parts[0].contains("Select")) {
			String[] selectedCoordinates = parts[4].split("=");
			int sX = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf("(")+1, selectedCoordinates[1].indexOf(",")));
			int sY = Integer.parseInt(selectedCoordinates[1].substring(selectedCoordinates[1].indexOf(",")+1, selectedCoordinates[1].indexOf(")")));
			frame.getButtonController().goThroughShapesList(sX, sY);
		} else {
			checkOperation(parts[0], frame, model);
		}
	}
	
	public static void checkOperation (String parts, DrawingFrame frame, DrawingModel model) {
		if (parts.contains("Delete")) {
			frame.getButtonController().deleteShapes();
		} else if (parts.contains("Unselect")) {
			frame.getButtonController().unselectShapes();
		} else if (parts.contains("Undo")) {
			frame.getButtonController().onUndoButtonClicked();
		} 
	}
}
