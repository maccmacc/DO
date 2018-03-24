package mvc.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import com.sun.org.apache.xpath.internal.operations.Mod;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.Command;
import shapes.Shape;
import shapes.circle.Circle;
import shapes.circle.CommandRemoveCircle;
import shapes.circle.CommandUpdateCircle;
import shapes.line.CommandRemoveLine;
import shapes.line.CommandUpdateLine;
import shapes.line.Line;
import shapes.point.CommandRemovePoint;
import shapes.point.CommandUpdatePoint;
import shapes.point.Point;
import shapes.rectangle.CommandRemoveRectangle;
import shapes.rectangle.CommandUpdateRectangle;
import shapes.rectangle.Rectangle;
import shapes.square.CommandRemoveSquare;
import shapes.square.CommandUpdateSquare;
import shapes.square.Square;
import utility.CommonHelpers;
import utility.DialogMethods;
import utility.ModifyShapesDialogs;

public class ButtonController {
	private DrawingModel model;
	private DrawingFrame frame;
	private ArrayList<Shape> selectedShapeList;

	public ButtonController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void onUndoButtonClicked(MouseEvent e) {
		if (!model.getUndoStack().isEmpty()) {
			Command previous = model.getUndoStack().pollLast();
			model.getRedoStack().offerLast(previous);
			previous.unexecute();
		}
	}

	public void onRedoButtonClicked(MouseEvent e) {
		if (!model.getRedoStack().isEmpty()) {
			Command previous = model.getRedoStack().pollLast();
			model.getUndoStack().offerLast(previous);
			previous.execute();
		}
	}

	public void onSelectButtonClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		goThroughShapesList(x, y);
	}

	public void unselectShapes() {
		for (Shape shape : model.getShapeList()) {
			shape.setSelected(false);
		}
	}

	public boolean goThroughShapesList(int x, int y) {
		for (int i = model.getShapeList().size() - 1; i >= 0; i--) {
			if (model.getShapeList().get(i).contains(x, y)) {
				model.getShapeList().get(i).setSelected(true);
				return true;
			}
		}
		unselectShapes();
		return false;
	}

	public int countSelectedShapes() {
		selectedShapeList = new ArrayList<>();
		for (Shape shape : model.getShapeList()) {
			if (shape.isSelected())
				selectedShapeList.add(shape);
		}
		return selectedShapeList.size();
	}

	public void deleteButtonClickedHandler() {
		countSelectedShapes();
		if (!model.getShapeList().isEmpty()) {
			if (selectedShapeList.size() == 1) {
				deleteShape(selectedShapeList);
			} else if (selectedShapeList.size() > 1) {
				deleteMultipleShapes();
			}
			else if (selectedShapeList.size() == 0)
				JOptionPane.showMessageDialog(null, "You have to select shape", "Error!", JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Shape list is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void deleteShape(ArrayList selectedShapeList) {
		if (DialogMethods.askUserToConfirm("Are you sure that you want to delete this shape?")) {
			if (selectedShapeList.get(0) instanceof Point) {
				Command remove = new CommandRemovePoint(model, (Point) selectedShapeList.get(0));
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Line) {
				Command remove = new CommandRemoveLine(model, (Line) selectedShapeList.get(0));
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Circle) {
				Command remove = new CommandRemoveCircle(model, (Circle) selectedShapeList.get(0));
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Rectangle) {
				Command remove = new CommandRemoveRectangle(model, (Rectangle) selectedShapeList.get(0));
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Square) {
				Command remove = new CommandRemoveSquare(model, (Square) selectedShapeList.get(0));
				remove.execute();
				model.getUndoStack().offerLast(remove);
			}
		}
	}

	public void deleteMultipleShapes() {
		if (DialogMethods.askUserToConfirm("Are you sure that you want to delete multiple shapes?")) {
			for (int i = 0; i <= selectedShapeList.size() - 1; i++) {
				if (selectedShapeList.get(i) instanceof Point) {
					CommandRemovePoint remove = new CommandRemovePoint(model, (Point) selectedShapeList.get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (selectedShapeList.get(i) instanceof Line) {
					CommandRemoveLine remove = new CommandRemoveLine(model, (Line) selectedShapeList.get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (selectedShapeList.get(i) instanceof Circle) {
					CommandRemoveCircle remove = new CommandRemoveCircle(model, (Circle) selectedShapeList.get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (selectedShapeList.get(i) instanceof Rectangle) {
					CommandRemoveRectangle remove = new CommandRemoveRectangle(model, (Rectangle) selectedShapeList.get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (selectedShapeList.get(i) instanceof Square) {
					CommandRemoveSquare remove = new CommandRemoveSquare(model, (Square) selectedShapeList.get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
				}
			}
		}
	}

	public void modifyShape() {
		countSelectedShapes();
		if (selectedShapeList.size() == 1) {
			if (selectedShapeList.get(0) instanceof Point) {
				Point newPoint = ModifyShapesDialogs.modifyPointDialog((Point)selectedShapeList.get(0));
					if (newPoint != null) {
						CommandUpdatePoint updatePoint = new CommandUpdatePoint((Point)selectedShapeList.get(0), newPoint);
						updatePoint.execute();
						model.getUndoStack().offerLast(updatePoint);
					}
			} else if (selectedShapeList.get(0) instanceof Circle) {
				Circle newCircle = ModifyShapesDialogs.modifyCircleDialog((Circle) selectedShapeList.get(0));
					if (newCircle != null) {
						CommandUpdateCircle updateCircle = new CommandUpdateCircle((Circle)selectedShapeList.get(0), newCircle);
						updateCircle.execute();
						model.getUndoStack().offerLast(updateCircle);
					}
			} else if (selectedShapeList.get(0) instanceof Rectangle) {
				Rectangle newRectangle = ModifyShapesDialogs.modifyRectangleDialog((Rectangle) selectedShapeList.get(0));
				if (newRectangle != null) {
					CommandUpdateRectangle updateRectangle = new CommandUpdateRectangle((Rectangle)selectedShapeList.get(0), newRectangle);
					updateRectangle.execute();
					model.getUndoStack().offerLast(updateRectangle);
				}
			} else if (selectedShapeList.get(0) instanceof Square) {
				Square newSquare = ModifyShapesDialogs.modifySquareDialog((Square) selectedShapeList.get(0));
				if (newSquare != null) {
					CommandUpdateSquare updateSquare = new CommandUpdateSquare((Square)selectedShapeList.get(0), newSquare);
					updateSquare.execute();
					model.getUndoStack().offerLast(updateSquare);
				}
			} else if (selectedShapeList.get(0) instanceof Line) {
				Line newLine = ModifyShapesDialogs.modifyLineDialog((Line) selectedShapeList.get(0));
				if (newLine != null) {
					CommandUpdateLine updateLine = new CommandUpdateLine((Line)selectedShapeList.get(0), newLine);
					updateLine.execute();
					model.getUndoStack().offerLast(updateLine);
				}
			} 
		} else {
			DialogMethods.showErrorMessage("You can modify only 1 shape!");
		}
	}
	
	public void chooseOuterColor(Color previousColor) {
		frame.getButtonView()
		.getBtnOuterColor()
		.setBackground(CommonHelpers.chooseColor(previousColor));
	}
	
	public void chooseInnerColor(Color previousColor) {
		frame.getButtonView()
		.getBtnInnerColor()
		.setBackground(CommonHelpers.chooseColor(previousColor));
	}
	
	

}
