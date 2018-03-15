package mvc.controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import shapes.Command;
import shapes.Shape;
import shapes.point.CommandRemovePoint;
import shapes.point.CommandUpdatePoint;
import shapes.point.Point;
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
				deleteShape();
			} else if (selectedShapeList.size() > 1) {
				deleteMultipleShapes();
			}
			else if (selectedShapeList.size() == 0)
				JOptionPane.showMessageDialog(null, "You have to select shape", "Error!", JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Shape list is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void deleteShape() {
		if (DialogMethods.askUserToConfirm("Are you sure that you want to delete this shape?")) {
			Command remove = new CommandRemovePoint(model, (Point) selectedShapeList.get(0));
			remove.execute();
			model.getUndoStack().offerLast(remove);
		}
	}

	public void deleteMultipleShapes() {
		if (DialogMethods.askUserToConfirm("Are you sure that you want to delete multiple shapes?")) {
			for (int i = 0; i <= selectedShapeList.size() - 1; i++) {
				Command remove = new CommandRemovePoint(model, (Point) selectedShapeList.get(i));
				remove.execute();
				model.getUndoStack().offerLast(remove);
			}
		}
	}

	public void modifyShape() {
		countSelectedShapes();
		if (selectedShapeList.size() == 1) {
			Point newPoint = ModifyShapesDialogs.modifyPointDialog((Point)selectedShapeList.get(0));
			if (newPoint != null) {
				CommandUpdatePoint updatePoint = new CommandUpdatePoint((Point)selectedShapeList.get(0), newPoint);
				updatePoint.execute();
				model.getUndoStack().offerLast(updatePoint);
			}
		} else {
			DialogMethods.showErrorMessage("You can modify only 1 shape!");
		}
	}
	
	

}
