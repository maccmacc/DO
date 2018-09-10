package mvc.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import drawingFrame.DrawingFrame;
import hexagon.Hexagon;
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
import utility.DecodeLog;
import utility.DialogMethods;
import utility.ModifyShapesDialogs;
import zAxis.BringToBack;
import zAxis.BringToFront;
import zAxis.ToBack;
import zAxis.ToFront;

public class ButtonController {
	private DrawingModel model;
	private DrawingFrame frame;
	private LogView logView;
	private int countLogLine = 0;

	public ButtonController(DrawingModel model, DrawingFrame frame, LogView logView) {
		this.model = model;
		this.frame = frame;
		this.logView = logView;
	}

	public void onUndoButtonClicked() {
		if (!model.getUndoStack().isEmpty()) {
			frame.getButtonView().getBtnRedo().setEnabled(true);
			Command previous = model.getUndoStack().pollLast();
			model.getRedoStack().offerLast(previous);
			previous.unexecute();
		} else {
			frame.getButtonView().getBtnUndo().setEnabled(false);
		}
	}

	public void onRedoButtonClicked() {
		if (!model.getRedoStack().isEmpty()) {
			Command previous = model.getRedoStack().pollLast();
			model.getUndoStack().offerLast(previous);
			previous.execute();
		} else {
			frame.getButtonView().getBtnRedo().setEnabled(false);
		}
	}

	public void onSelectButtonClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		goThroughShapesList(x, y);
	}

	public void unselectShapes() {
		countSelectedShapes();
		for (Shape shape : model.getSelectedShapeList()) {
			shape.setSelected(false);
			logView.getDlm().addElement("Unselect:" + shape.toString());
		}
		model.notifyAllObservers();
	}

	public boolean goThroughShapesList(int x, int y) {
		for (int i = model.getShapeList().size() - 1; i >= 0; i--) {
			if (model.getShapeList().get(i).contains(x, y)) {
				model.getShapeList().get(i).setSelected(true);
				countSelectedShapes();
				model.notifyAllObservers();
				logView.getDlm().addElement("Select:" + model.getShapeList().get(i).toString());
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
		
		if(DialogMethods.askUserToConfirm("Are you sure that you want do load a drawing? Your current drawing will be lost.")) {
			makeNewDrawing();
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
	}
	
	public void openLog() {
		if (DialogMethods.askUserToConfirm("Are you sure that you want do load log? Your current drawing will be lost.")) {
			makeNewDrawing();
			JFileChooser chooser = new JFileChooser();
			int answer = chooser.showOpenDialog(null);
			String line = null;
			
			if (answer == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile(); 
				
				try {
					FileReader reader = new FileReader(file);
					BufferedReader buffer = new BufferedReader(reader);
					
					while ((line = buffer.readLine()) != null) {
						model.getLogList().add(line);
					}	
					System.out.println(model.getLogList());
					logView.getDlm().addElement("Log is imported!");
					buffer.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void drawFromLog() {
		if (countLogLine >= model.getLogList().size()) {
			System.out.println("end of log");
			DialogMethods.showNotificationMessage("Drawing is complete!");
			return;
		}
		String logLine = model.getLogList().get(countLogLine);
		if (countLogLine < model.getLogList().size()) {
			countLogLine++;
		}
		String[] parts = logLine.split(";");
		
		int x = Integer.parseInt(parts[0].substring(parts[0].indexOf("(")+1, parts[0].indexOf(",")));
		int y = Integer.parseInt(parts[0].substring(parts[0].indexOf(",")+1, parts[0].indexOf(")")));
		
		if (logLine.contains("Point")) {
			String[] color = parts[1].split("=");
			Point point = new Point(x, y);
			point.setColor(Color.decode(color[1]));
			DecodeLog.decodePoint(point, parts[0], frame, model, logView);
		} else if (logLine.contains("Circle")) {
			String[] r = parts[1].split("=");
			String[] outerColor = parts[2].split("=");
			String[] innerColor = parts[3].split("=");
			Circle circle = new Circle(new Point(x,y), Integer.parseInt(r[1]), Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			DecodeLog.decodeCircle(circle, parts[0], frame, model, logView);
		} else if (logLine.contains("Square")) {
			String[] outerColor = parts[2].split("=");
			String[] innerColor = parts[3].split("=");
			String[] side = parts[1].split("=");
			Square square = new Square(new Point(x,y), Integer.parseInt(side[1]), Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			DecodeLog.decodeSquare(square, parts[0], frame, model, logView);
		} else if (logLine.contains("Rectangle")) {
			String[] outerColor = parts[3].split("=");
			String[] innerColor = parts[4].split("=");
			String[] height = parts[1].split("=");
			String[] width = parts[2].split("=");
			Rectangle rectangle = new Rectangle(new Point(x,y), Integer.parseInt(height[1]), Integer.parseInt(width[1]), Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			DecodeLog.decodeRectangle(rectangle, parts[0], frame, model, logView);
		} else if (logLine.contains("Hexagon")) {
			String[] outerColor = parts[2].split("=");
			String[] innerColor = parts[3].split("=");
			String[] r = parts[1].split("=");
			Hexagon hexagon = new Hexagon(x, y, Integer.parseInt(r[1]));
			HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon, Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			DecodeLog.decodeHexagon(hexagonAdapter, parts[0], frame, model, logView);
		} else if (logLine.contains("Line")) {
			int endPointX = Integer.parseInt(parts[1].substring(parts[1].indexOf("(")+1, parts[1].indexOf(",")));
			int endPointY = Integer.parseInt(parts[1].substring(parts[1].indexOf(",")+1, parts[1].indexOf(")")));
			String[] color = parts[2].split("=");
			Line line = new Line(new Point(x, y), new Point(endPointX, endPointY), Color.decode(color[1]));
			DecodeLog.decodeLine(line, parts[0], frame, model, logView);
		}
		
	}
	
	public void bringToFront() {
		if(countSelectedShapes() == 0) {
			return;
		}
		Shape selectedShape = model.getSelectedShapeList().get(0);
		BringToFront bringToFront = new BringToFront(model, frame, selectedShape);
		model.getUndoStack().offerLast(bringToFront);
		bringToFront.execute();
	}
	
	public void bringToBack() {
		if(countSelectedShapes() == 0) {
			return;
		}
		Shape selectedShape = model.getSelectedShapeList().get(0);
		BringToBack bringToBack = new BringToBack(model, frame, selectedShape);
		model.getUndoStack().offerLast(bringToBack);
		bringToBack.execute();
	}
	
	public void toFront() {
		if(countSelectedShapes() == 0) {
			return;
		}
		
		Shape selectedShape = model.getSelectedShapeList().get(0);
		if (model.getShapeList().indexOf(selectedShape) == model.getShapeList().size() - 1) {
			return;
		}
	
		ToFront toFront= new ToFront(model, frame, selectedShape);
		model.getUndoStack().offerLast(toFront);
		toFront.execute();
	}
	
	public void toBack() {
		if(countSelectedShapes() == 0) {
			return;
		}
		Shape selectedShape = model.getSelectedShapeList().get(0);
		if (model.getShapeList().indexOf(selectedShape) == 0) {
			return;
		}
		ToBack toBack = new ToBack(model, frame, selectedShape);
		model.getUndoStack().offerLast(toBack);
		toBack.execute();
	}
	
	public void makeNewDrawing() {
		model.getShapeList().clear();
		model.getUndoStack().clear();
		model.getRedoStack().clear();
		frame.getDrawingView().repaint();
		frame.getLogView().getDlm().clear();
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
