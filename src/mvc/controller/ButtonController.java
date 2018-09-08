package mvc.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.sun.org.apache.xpath.internal.operations.Mod;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;
import mvc.view.LogView;
import shapes.Command;
import shapes.Shape;
import shapes.circle.Circle;
import shapes.circle.CommandRemoveCircle;
import shapes.circle.CommandUpdateCircle;
import shapes.hexagon.CommandRemoveHexagonAdapter;
import shapes.hexagon.CommandUpdateHexagonAdapter;
import shapes.hexagon.HexagonAdapter;
import shapes.line.CommandRemoveLine;
import shapes.line.CommandUpdateLine;
import shapes.line.Line;
import shapes.observer.Observer;
import shapes.observer.Subject;
import shapes.point.CommandRemovePoint;
import shapes.point.CommandUpdatePoint;
import shapes.point.Point;
import shapes.rectangle.CommandRemoveRectangle;
import shapes.rectangle.CommandUpdateRectangle;
import shapes.rectangle.Rectangle;
import shapes.square.CommandRemoveSquare;
import shapes.square.CommandUpdateSquare;
import shapes.square.Square;
import strategy.SaveDrawing;
import strategy.SaveLog;
import strategy.SaveManager;
import utility.CommonHelpers;
import utility.DialogMethods;
import utility.ModifyShapesDialogs;

public class ButtonController {
	private DrawingModel model;
	private DrawingFrame frame;
	private LogView logView;

	public ButtonController(DrawingModel model, DrawingFrame frame, LogView logView) {
		this.model = model;
		this.frame = frame;
		this.logView = logView;
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
		countSelectedShapes();
		for (Shape shape : model.getShapeList()) {
			shape.setSelected(false);
		}
		model.notifyAllObservers();
	}

	public boolean goThroughShapesList(int x, int y) {
		for (int i = model.getShapeList().size() - 1; i >= 0; i--) {
			if (model.getShapeList().get(i).contains(x, y)) {
				model.getShapeList().get(i).setSelected(true);
				countSelectedShapes();
				model.notifyAllObservers();
				return true;
			}
		}
		unselectShapes();
		return false;
	}

	public int countSelectedShapes() {
		model.getSelectedShapeList().clear();
		for (Shape shape : model.getShapeList()) {
			if (shape.isSelected())
				model.getSelectedShapeList().add(shape);
		}
		System.out.println("number of selected shapes: " + model.getSelectedShapeList().size());
		System.out.println("number of  shapes: " + model.getShapeList().size());
		return model.getSelectedShapeList().size();
	}

	public void deleteButtonClickedHandler() {
		countSelectedShapes();
		if (!model.getShapeList().isEmpty()) {
			if (model.getSelectedShapeList().size() == 1) {
				deleteShape(model.getSelectedShapeList());
			} else if (model.getSelectedShapeList().size() > 1) {
				deleteMultipleShapes();
			}
			else if (model.getSelectedShapeList().size() == 0)
				JOptionPane.showMessageDialog(null, "You have to select shape", "Error!", JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Shape list is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void deleteShape(ArrayList selectedShapeList) {
		if (DialogMethods.askUserToConfirm("Are you sure that you want to delete this shape?")) {
			if (selectedShapeList.get(0) instanceof Point) {
				Command remove = new CommandRemovePoint(model, (Point) selectedShapeList.get(0), logView);
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Line) {
				Command remove = new CommandRemoveLine(model, (Line) selectedShapeList.get(0), logView);
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Circle) {
				Command remove = new CommandRemoveCircle(model, (Circle) selectedShapeList.get(0), logView);
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Rectangle) {
				Command remove = new CommandRemoveRectangle(model, (Rectangle) selectedShapeList.get(0), logView);
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof Square) {
				Command remove = new CommandRemoveSquare(model, (Square) selectedShapeList.get(0), logView);
				remove.execute();
				model.getUndoStack().offerLast(remove);
			} else if (selectedShapeList.get(0) instanceof HexagonAdapter) {
				Command remove = new CommandRemoveHexagonAdapter(model, (HexagonAdapter) selectedShapeList.get(0), logView);
				remove.execute();
				model.getUndoStack().offerLast(remove);
			}
			unselectShapes();
		}
	}

	public void deleteMultipleShapes() {
		if (DialogMethods.askUserToConfirm("Are you sure that you want to delete multiple shapes?")) {
			for (int i = 0; i <= model.getSelectedShapeList().size() - 1; i++) {
				if (model.getSelectedShapeList().get(i) instanceof Point) {
					CommandRemovePoint remove = new CommandRemovePoint(model, (Point) model.getSelectedShapeList().get(i), logView);
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (model.getSelectedShapeList().get(i) instanceof Line) {
					CommandRemoveLine remove = new CommandRemoveLine(model, (Line) model.getSelectedShapeList().get(i), logView);
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (model.getSelectedShapeList().get(i) instanceof Circle) {
					CommandRemoveCircle remove = new CommandRemoveCircle(model, (Circle) model.getSelectedShapeList().get(i), logView);
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (model.getSelectedShapeList().get(i) instanceof Rectangle) {
					CommandRemoveRectangle remove = new CommandRemoveRectangle(model, (Rectangle) model.getSelectedShapeList().get(i), logView);
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (model.getSelectedShapeList().get(i) instanceof Square) {
					CommandRemoveSquare remove = new CommandRemoveSquare(model, (Square) model.getSelectedShapeList().get(i), logView);
					remove.execute();
					model.getUndoStack().offerLast(remove);
				} else if (model.getSelectedShapeList().get(0) instanceof HexagonAdapter) {
					Command remove = new CommandRemoveHexagonAdapter(model, (HexagonAdapter) model.getSelectedShapeList().get(0), logView);
					remove.execute();
					model.getUndoStack().offerLast(remove);
				}
			}
			unselectShapes();
		}
	}

	public void modifyShape() {
		countSelectedShapes();
		if (model.getSelectedShapeList().size() == 1) {
			if (model.getSelectedShapeList().get(0) instanceof Point) {
				Point newPoint = ModifyShapesDialogs.modifyPointDialog((Point)model.getSelectedShapeList().get(0));
					if (newPoint != null) {
						CommandUpdatePoint updatePoint = new CommandUpdatePoint((Point)model.getSelectedShapeList().get(0), newPoint, logView);
						updatePoint.execute();
						model.getUndoStack().offerLast(updatePoint);
					}
			} else if (model.getSelectedShapeList().get(0) instanceof Circle) {
				Circle newCircle = ModifyShapesDialogs.modifyCircleDialog((Circle) model.getSelectedShapeList().get(0));
					if (newCircle != null) {
						CommandUpdateCircle updateCircle = new CommandUpdateCircle((Circle)model.getSelectedShapeList().get(0), newCircle, logView);
						updateCircle.execute();
						model.getUndoStack().offerLast(updateCircle);
					}
			} else if (model.getSelectedShapeList().get(0) instanceof Rectangle) {
				Rectangle newRectangle = ModifyShapesDialogs.modifyRectangleDialog((Rectangle) model.getSelectedShapeList().get(0));
				if (newRectangle != null) {
					CommandUpdateRectangle updateRectangle = new CommandUpdateRectangle((Rectangle)model.getSelectedShapeList().get(0), newRectangle, logView);
					updateRectangle.execute();
					model.getUndoStack().offerLast(updateRectangle);
				}
			} else if (model.getSelectedShapeList().get(0) instanceof Square) {
				Square newSquare = ModifyShapesDialogs.modifySquareDialog((Square) model.getSelectedShapeList().get(0));
				if (newSquare != null) {
					CommandUpdateSquare updateSquare = new CommandUpdateSquare((Square)model.getSelectedShapeList().get(0), newSquare, logView);
					updateSquare.execute();
					model.getUndoStack().offerLast(updateSquare);
				}
			} else if (model.getSelectedShapeList().get(0) instanceof Line) {
				Line newLine = ModifyShapesDialogs.modifyLineDialog((Line) model.getSelectedShapeList().get(0));
				if (newLine != null) {
					CommandUpdateLine updateLine = new CommandUpdateLine((Line)model.getSelectedShapeList().get(0), newLine, logView);
					updateLine.execute();
					model.getUndoStack().offerLast(updateLine);
				}
			} else if (model.getSelectedShapeList().get(0) instanceof HexagonAdapter) {
				HexagonAdapter newHexagonAdapter = ModifyShapesDialogs.modifyHexagonAdapterDialog((HexagonAdapter)model.getSelectedShapeList().get(0));
				if (newHexagonAdapter != null) {
					CommandUpdateHexagonAdapter updateHexagonAdapter = 
							new CommandUpdateHexagonAdapter((HexagonAdapter)model.getSelectedShapeList().get(0), newHexagonAdapter, logView);
					updateHexagonAdapter.execute();
					model.getUndoStack().offerLast(updateHexagonAdapter);
				}
			}
		} else {
			DialogMethods.showErrorMessage("You can modify only 1 shape!");
		}
	}
	
	public void saveLog() {
		System.out.println("save log");
		
		JFileChooser chooser = new JFileChooser();
		int answer = chooser.showSaveDialog(null);
		
		if (answer == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			String path = file.getAbsolutePath();
			SaveManager saveManager = new SaveManager(new SaveLog());
			saveManager.save(frame, file);
		}
	}
	
	public void saveDrawing() {
		System.out.println("save drawing");
		
		JFileChooser chooser = new JFileChooser();
		int answer = chooser.showSaveDialog(null);
		
		if (answer == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			String path = file.getAbsolutePath();
			SaveManager saveManager = new SaveManager(new SaveDrawing());
			saveManager.save(frame, file);
		}
	}
	
	public void openDrawing() throws ClassNotFoundException {
		System.out.println("open drawing");
		
		JFileChooser chooser = new JFileChooser();
		int answer = chooser.showOpenDialog(null);
		
		if (answer == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String fileName = file.getPath(); 
			
			try {
		         FileInputStream fileIn = new FileInputStream(fileName);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         model.getShapeList().addAll((ArrayList<Shape>)in.readObject());
		         frame.getView().repaint();
		         in.close();
		         fileIn.close();
		      } catch (IOException i) {
		         i.printStackTrace();
		         return;
		      } catch (ClassNotFoundException c) {
		          System.out.println("Class not found");
		          c.printStackTrace();
		          return;
		       }
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

	public LogView getLogView() {
		return logView;
	}
	
}
