package mvc.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import shapes.point.CommandRemovePoint;
import shapes.point.CommandUpdatePoint;
import shapes.point.Point;
import shapes.rectangle.CommandRemoveRectangle;
import shapes.rectangle.CommandUpdateRectangle;
import shapes.rectangle.Rectangle;
import shapes.square.CommandRemoveSquare;
import shapes.square.CommandUpdateSquare;
import shapes.square.Square;
import strategy.DecodeLog;
import strategy.SaveDrawing;
import strategy.SaveLog;
import strategy.SaveManager;
import zAxis.BringToBack;
import zAxis.BringToFront;
import zAxis.ToBack;
import zAxis.ToFront;

public class ButtonController {
	private DrawingModel model;
	private DrawingFrame frame;
	private LogView logView;
	private DecodeLog decodeLog;
	private int countLogLine = 0;

	public ButtonController(DrawingModel model, DrawingFrame frame, LogView logView, DecodeLog decodeLog) {
		this.model = model;
		this.frame = frame;
		this.logView = logView;
		this.decodeLog = decodeLog;
	}

	public void onUndoButtonClicked() {
		if (!model.getUndoStack().isEmpty()) {
			frame.getButtonView().getBtnRedo().setEnabled(true);
			Command previous = model.getUndoStack().pollLast();
			model.getRedoStack().offerLast(previous);
			previous.unexecute();
			model.notifyAllObservers();
		}
	}

	public void onRedoButtonClicked() {
		if (!model.getRedoStack().isEmpty()) {
			Command previous = model.getRedoStack().pollLast();
			model.getUndoStack().offerLast(previous);
			previous.execute();
			model.notifyAllObservers();
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
		countSelectedShapes();
		model.notifyAllObservers();
	}

	public boolean goThroughShapesList(int x, int y) {
		for (int i = model.getShapeList().size() - 1; i >= 0; i--) {
			if (model.getShapeList().get(i).contains(x, y) && !model.getShapeList().get(i).isSelected()) {
				model.getShapeList().get(i).setSelected(true);
				countSelectedShapes();
				model.notifyAllObservers();
				logView.getDlm().addElement("Select:" + model.getShapeList().get(i).toString() + ";click=(" + x + "," + y + ")");
				return true;
			} else if (model.getShapeList().get(i).contains(x, y) && model.getShapeList().get(i).isSelected()) {
				model.getShapeList().get(i).setSelected(false);
				countSelectedShapes();
				model.notifyAllObservers();
				logView.getDlm().addElement("Unselect:" + model.getShapeList().get(i).toString());
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
		return model.getSelectedShapeList().size();
	}

	public void deleteButtonClickedHandler() {
		countSelectedShapes();
		if (!model.getShapeList().isEmpty()) {
			if (model.getSelectedShapeList().size() == 1) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this shape?", "Confirm", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == 0) {
						deleteShapes();
					}
			} else if (model.getSelectedShapeList().size() > 1) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete multiple shapes?", "Confirm", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == 0)
					deleteShapes();
			}
			else if (model.getSelectedShapeList().size() == 0)
				JOptionPane.showMessageDialog(null, "You have to select shape", "Error!", JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Shape list is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void deleteShapes() {
			for (int i = 0; i <= model.getSelectedShapeList().size() - 1; i++) {
				if (model.getSelectedShapeList().get(i) instanceof Point) {
					CommandRemovePoint remove = new CommandRemovePoint(model, (Point) model.getSelectedShapeList().get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
					logView.getDlm().addElement("Delete:" + model.getSelectedShapeList().get(i).toString());
				} else if (model.getSelectedShapeList().get(i) instanceof Line) {
					CommandRemoveLine remove = new CommandRemoveLine(model, (Line) model.getSelectedShapeList().get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
					logView.getDlm().addElement("Delete:" + model.getSelectedShapeList().get(i).toString());
				} else if (model.getSelectedShapeList().get(i) instanceof Circle) {
					CommandRemoveCircle remove = new CommandRemoveCircle(model, (Circle) model.getSelectedShapeList().get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
					logView.getDlm().addElement("Delete:" + model.getSelectedShapeList().get(i).toString());
				} else if (model.getSelectedShapeList().get(i) instanceof Rectangle) {
					CommandRemoveRectangle remove = new CommandRemoveRectangle(model, (Rectangle) model.getSelectedShapeList().get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
					logView.getDlm().addElement("Delete:" + model.getSelectedShapeList().get(i).toString());
				} else if (model.getSelectedShapeList().get(i) instanceof Square) {
					CommandRemoveSquare remove = new CommandRemoveSquare(model, (Square) model.getSelectedShapeList().get(i));
					remove.execute();
					model.getUndoStack().offerLast(remove);
					logView.getDlm().addElement("Delete:" + model.getSelectedShapeList().get(i).toString());
				} else if (model.getSelectedShapeList().get(0) instanceof HexagonAdapter) {
					Command remove = new CommandRemoveHexagonAdapter(model, (HexagonAdapter) model.getSelectedShapeList().get(0));
					remove.execute();
					model.getUndoStack().offerLast(remove);
					logView.getDlm().addElement("Delete:" + model.getSelectedShapeList().get(i).toString());
				}
			}
			unselectShapes();
		}

	public void modifyShape() {
		countSelectedShapes();
		if (model.getSelectedShapeList().size() == 1) {
			if (model.getSelectedShapeList().get(0) instanceof Point) {
				Point point = (Point)model.getSelectedShapeList().get(0);
				JTextField xCoordinate = new JTextField();
				JTextField yCoordinate = new JTextField();
				JButton btnNewColor = new JButton();
				xCoordinate.setText(Integer.toString(point.getX()));
				yCoordinate.setText(Integer.toString(point.getY()));
				btnNewColor.setBackground(point.getColor());
				btnNewColor.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColor.setBackground(openColorChooser(btnNewColor.getBackground()));
					}
				});
				final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinate,
						new JLabel("Enter new y coordinate:"), yCoordinate,
						new JLabel("Choose new color:"), btnNewColor};
				if (JOptionPane.showConfirmDialog(null, components, "Modify point",
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					if (!xCoordinate.getText().isEmpty() || !yCoordinate.getText().isEmpty()) {
						try {
							int tmpX = Integer.parseInt(xCoordinate.getText());
							int tmpY = Integer.parseInt(yCoordinate.getText());
							if (tmpX > 0 && tmpY > 0)
								point = new Point(tmpX, tmpY, btnNewColor.getBackground());
							else
								JOptionPane.showMessageDialog(null, "Coordinates must be greater than 0!", "Error!", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Coordinates must be numbers!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Coordinates must not be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (point != null) {
						CommandUpdatePoint updatePoint = new CommandUpdatePoint((Point)model.getSelectedShapeList().get(0), point);
						updatePoint.execute();
						model.getUndoStack().offerLast(updatePoint);
						logView.getDlm().addElement("Update:" + point.toString());
				}
			} else if (model.getSelectedShapeList().get(0) instanceof Circle) {
				Circle circle = (Circle)model.getSelectedShapeList().get(0);
				JTextField xCoordinateCenter = new JTextField();
				JTextField yCoordinateCenter = new JTextField();
				JTextField r = new JTextField();
				JButton btnNewColorOuter = new JButton();
				JButton btnNewColorInner = new JButton();
				xCoordinateCenter.setText(Integer.toString(circle.getCenter().getX()));
				yCoordinateCenter.setText(Integer.toString(circle.getCenter().getY()));
				r.setText(Integer.toString(circle.getR()));
				btnNewColorOuter.setBackground(circle.getColor());
				btnNewColorOuter.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorOuter.setBackground(openColorChooser(btnNewColorOuter.getBackground()));
					}
				});
				btnNewColorInner.setBackground(circle.getSurfaceColor());
				btnNewColorInner.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorInner.setBackground(openColorChooser(btnNewColorInner.getBackground()));
					}
				});
				final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateCenter,
						new JLabel("Enter new y coordinate:"), yCoordinateCenter, new JLabel("Enter new perimeter:"), r,
						new JLabel("Choose new outer color:"), btnNewColorOuter,
						new JLabel("Choose new inner color:"), btnNewColorInner};
				if (JOptionPane.showConfirmDialog(null, components, "Modify circle",
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					if (!xCoordinateCenter.getText().isEmpty() || !yCoordinateCenter.getText().isEmpty() || !r.getText().isEmpty()) {
						try {
							int tmpX = Integer.parseInt(xCoordinateCenter.getText());
							int tmpY = Integer.parseInt(yCoordinateCenter.getText());
							int tmpR = Integer.parseInt(r.getText());
							if (tmpX > 0 && tmpY > 0 && tmpR > 0)
								circle = new Circle(new Point(tmpX, tmpY), tmpR, btnNewColorOuter.getBackground(), btnNewColorInner.getBackground());
							else
								JOptionPane.showMessageDialog(null, "Coordinates and perimeter must be greater than 0!", "Error!", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Coordinates and perimeter must be numbers!", "Error!",JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Coordinates and perimeter must not be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (circle != null) {
					CommandUpdateCircle updateCircle = new CommandUpdateCircle((Circle)model.getSelectedShapeList().get(0), circle);
					updateCircle.execute();
					model.getUndoStack().offerLast(updateCircle);
					logView.getDlm().addElement("Update:" + circle.toString());
				}
			} else if (model.getSelectedShapeList().get(0) instanceof Rectangle) {
				Rectangle rectangle = (Rectangle)model.getSelectedShapeList().get(0);
				JTextField xCoordinateUpperLeft = new JTextField();
				JTextField yCoordinateUpperLeft = new JTextField();
				JTextField width = new JTextField();
				JTextField height = new JTextField();
				JButton btnNewColorOuter = new JButton();
				JButton btnNewColorInner = new JButton();
				xCoordinateUpperLeft.setText(Integer.toString(rectangle.getUpperLeftPoint().getX()));
				yCoordinateUpperLeft.setText(Integer.toString(rectangle.getUpperLeftPoint().getY()));
				width.setText(Integer.toString(rectangle.getSideLength()));
				height.setText(Integer.toString(rectangle.getWidth()));
				btnNewColorOuter.setBackground(rectangle.getColor());
				btnNewColorOuter.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorOuter.setBackground(openColorChooser(btnNewColorOuter.getBackground()));
					}
				});
				btnNewColorInner.setBackground(rectangle.getSurfaceColor());
				btnNewColorInner.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorInner.setBackground(openColorChooser(btnNewColorInner.getBackground()));
					}
				});
				final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateUpperLeft,
						new JLabel("Enter new y coordinate:"), yCoordinateUpperLeft, new JLabel("Enter new width:"), width,
						new JLabel("Enter new height:"), height, new JLabel("Choose new outer color:"), btnNewColorOuter,
						new JLabel("Choose new inner color:"), btnNewColorInner};
				if (JOptionPane.showConfirmDialog(null, components, "Modify rectangle",
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					if (!xCoordinateUpperLeft.getText().isEmpty() || !yCoordinateUpperLeft.getText().isEmpty() || !width.getText().isEmpty()
							|| !height.getText().isEmpty()) {
						try {
							int tmpX = Integer.parseInt(xCoordinateUpperLeft.getText());
							int tmpY = Integer.parseInt(yCoordinateUpperLeft.getText());
							int tmpWidth = Integer.parseInt(width.getText());
							int tmpHeight = Integer.parseInt(height.getText());
							if (tmpX > 0 && tmpY > 0 && tmpWidth > 0 && tmpHeight > 0)
								rectangle = new Rectangle(new Point(tmpX, tmpY), tmpWidth, tmpHeight, btnNewColorOuter.getBackground(),
										btnNewColorInner.getBackground());
							else
								JOptionPane.showMessageDialog(null, "Coordinates, width and height must be greater than 0!", "Error!", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Coordinates, width and height must be numbers!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Coordinates, width and height must not be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (rectangle != null) {
					CommandUpdateRectangle updateRectangle = new CommandUpdateRectangle((Rectangle)model.getSelectedShapeList().get(0), rectangle);
					updateRectangle.execute();
					model.getUndoStack().offerLast(updateRectangle);
					logView.getDlm().addElement("Update:" + rectangle.toString());
				}
			} else if (model.getSelectedShapeList().get(0) instanceof Square) {
				Square square = (Square)model.getSelectedShapeList().get(0);
				JTextField xCoordinateUpperLeft = new JTextField();
				JTextField yCoordinateUpperLeft = new JTextField();
				JTextField sideLength = new JTextField();
				JButton btnNewColorOuter = new JButton();
				JButton btnNewColorInner = new JButton();
				xCoordinateUpperLeft.setText(Integer.toString(square.getUpperLeftPoint().getX()));
				yCoordinateUpperLeft.setText(Integer.toString(square.getUpperLeftPoint().getY()));
				sideLength.setText(Integer.toString(square.getSideLength()));
				btnNewColorOuter.setBackground(square.getColor());
				btnNewColorOuter.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorOuter.setBackground(openColorChooser(btnNewColorOuter.getBackground()));
					}
				});
				btnNewColorInner.setBackground(square.getSurfaceColor());
				btnNewColorInner.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorInner.setBackground(openColorChooser(btnNewColorInner.getBackground()));
					}
				});
				final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateUpperLeft,
						new JLabel("Enter new y coordinate:"), yCoordinateUpperLeft, new JLabel("Enter new side length:"), sideLength,
						new JLabel("Choose new outer color:"), btnNewColorOuter, new JLabel("Choose new inner color:"), btnNewColorInner};
				if (JOptionPane.showConfirmDialog(null, components, "Modify square",
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					if (!xCoordinateUpperLeft.getText().isEmpty() || !yCoordinateUpperLeft.getText().isEmpty() || !sideLength.getText().isEmpty()) {
						try {
							int tmpX = Integer.parseInt(xCoordinateUpperLeft.getText());
							int tmpY = Integer.parseInt(yCoordinateUpperLeft.getText());
							int tmpSide = Integer.parseInt(sideLength.getText());
							if (tmpX > 0 && tmpY > 0 && tmpSide > 0)
								square = new Square(new Point(tmpX, tmpY), tmpSide,
										btnNewColorOuter.getBackground(), btnNewColorInner.getBackground());
							else
								JOptionPane.showMessageDialog(null, "Coordinates and side length must be greater than 0!", "Error!", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Coordinates and side length must be numbers!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Coordinates and side length must not be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (square != null) {
					CommandUpdateSquare updateSquare = new CommandUpdateSquare((Square)model.getSelectedShapeList().get(0), square);
					updateSquare.execute();
					model.getUndoStack().offerLast(updateSquare);
					logView.getDlm().addElement("Update:" + square.toString());
				}
			} else if (model.getSelectedShapeList().get(0) instanceof Line) {
				Line line = (Line)model.getSelectedShapeList().get(0);
				JTextField xCoordinateStartPoint = new JTextField();
				JTextField yCoordinateStartPoint = new JTextField();
				JTextField xCoordinateEndPoint = new JTextField();
				JTextField yCoordinateEndPoint = new JTextField();
				JButton btnNewColor = new JButton();
				btnNewColor.setBackground(line.getColor());
				btnNewColor.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColor.setBackground(openColorChooser(btnNewColor.getBackground()));
					}
				});
				xCoordinateStartPoint.setText(Integer.toString(line.getStartPoint().getX()));
				yCoordinateStartPoint.setText(Integer.toString(line.getStartPoint().getY()));
				xCoordinateEndPoint.setText(Integer.toString(line.getEndPoint().getX()));
				yCoordinateEndPoint.setText(Integer.toString(line.getEndPoint().getY()));
				
				final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate of start point:"), xCoordinateStartPoint,
						new JLabel("Enter new y coordinate of start point:"), yCoordinateStartPoint,
						new JLabel("Enter new x coordinate of end point:"), xCoordinateEndPoint, 
						new JLabel("Enter new y coordinate of end point:"), yCoordinateEndPoint,
						new JLabel("Choose new color:"), btnNewColor};
						
				if (JOptionPane.showConfirmDialog(null, components, "Modify line",
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					if (!xCoordinateStartPoint.getText().isEmpty() || !yCoordinateStartPoint.getText().isEmpty() || 
							!xCoordinateEndPoint.getText().isEmpty() || !yCoordinateEndPoint.getText().isEmpty()) {
						try {
							int tmpStartX = Integer.parseInt(xCoordinateStartPoint.getText());
							int tmpStartY = Integer.parseInt(yCoordinateStartPoint.getText());
							int tmpEndX = Integer.parseInt(xCoordinateEndPoint.getText());
							int tmpEndY = Integer.parseInt(yCoordinateEndPoint.getText());
							if (tmpStartX > 0 && tmpStartY > 0 && tmpEndX > 0 && tmpEndY > 0)
								line = new Line(new Point(tmpStartX, tmpStartY), new Point(tmpEndX, tmpEndY),  btnNewColor.getBackground());
							else
								JOptionPane.showMessageDialog(null, "Coordinates must be greater than 0!", "Error!", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Coordinates must be numbers!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Coordinates must not be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (line != null) {
					CommandUpdateLine updateLine = new CommandUpdateLine((Line)model.getSelectedShapeList().get(0), line);
					updateLine.execute();
					model.getUndoStack().offerLast(updateLine);
					logView.getDlm().addElement("Update:" + line.toString());
				}
			} else if (model.getSelectedShapeList().get(0) instanceof HexagonAdapter) {
				HexagonAdapter hexagonAdapter = (HexagonAdapter)model.getSelectedShapeList().get(0);
				JTextField xCoordinateCenter = new JTextField();
				JTextField yCoordinateCenter = new JTextField();
				JTextField r = new JTextField();
				JButton btnNewColorOuter = new JButton();
				JButton btnNewColorInner = new JButton();
				xCoordinateCenter.setText(Integer.toString(hexagonAdapter.getHexagon().getX()));
				yCoordinateCenter.setText(Integer.toString(hexagonAdapter.getHexagon().getY()));
				r.setText(Integer.toString(hexagonAdapter.getHexagon().getR()));
				btnNewColorOuter.setBackground(hexagonAdapter.getHexagon().getBorderColor());
				btnNewColorOuter.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorOuter.setBackground(openColorChooser(btnNewColorOuter.getBackground()));
					}
				});
				btnNewColorInner.setBackground(hexagonAdapter.getHexagon().getAreaColor());
				btnNewColorInner.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						btnNewColorInner.setBackground(openColorChooser(btnNewColorInner.getBackground()));
					}
				});
				final JComponent[] components = new JComponent[] { new JLabel("Enter new x coordinate:"), xCoordinateCenter,
						new JLabel("Enter new y coordinate:"), yCoordinateCenter, new JLabel("Enter new perimeter:"), r,
						new JLabel("Choose new outer color:"), btnNewColorOuter,
						new JLabel("Choose new inner color:"), btnNewColorInner};
				if (JOptionPane.showConfirmDialog(null, components, "Modify hexagon",
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					if (!xCoordinateCenter.getText().isEmpty() || !yCoordinateCenter.getText().isEmpty() || !r.getText().isEmpty()) {
						try {
							int tmpX = Integer.parseInt(xCoordinateCenter.getText());
							int tmpY = Integer.parseInt(yCoordinateCenter.getText());
							int tmpR = Integer.parseInt(r.getText());
							if (tmpX > 0 && tmpY > 0 && tmpR > 0) {
								Hexagon hexagon = new Hexagon(tmpX, tmpY, tmpR);

								hexagonAdapter = new HexagonAdapter(hexagon);
								hexagonAdapter.setColor(btnNewColorOuter.getBackground());
								hexagonAdapter.setSurfaceColor(btnNewColorInner.getBackground());
							} else
								JOptionPane.showMessageDialog(null, "Coordinates and perimeter must be greater than 0!", "Error!", JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Coordinates and perimeter must be numbers!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Coordinates and perimeter must not be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (hexagonAdapter != null) {
					CommandUpdateHexagonAdapter updateHexagonAdapter = 
							new CommandUpdateHexagonAdapter((HexagonAdapter)model.getSelectedShapeList().get(0), hexagonAdapter);
					updateHexagonAdapter.execute();
					model.getUndoStack().offerLast(updateHexagonAdapter);
					logView.getDlm().addElement("Update:" + hexagonAdapter.toString());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "You can modify only 1 shape!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void saveLog() {
		JFileChooser chooser = new JFileChooser();
		int answer = chooser.showSaveDialog(null);
		
		if (answer == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			SaveManager saveManager = new SaveManager(new SaveLog());
			saveManager.save(frame, file);
		}
	}
	
	public void saveDrawing() {
		JFileChooser chooser = new JFileChooser();
		int answer = chooser.showSaveDialog(null);
		
		if (answer == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			SaveManager saveManager = new SaveManager(new SaveDrawing());
			saveManager.save(frame, file);
		}
	}
	
	public void openDrawing() throws ClassNotFoundException {
		if (JOptionPane.showConfirmDialog(null, "Are you sure that you want do load a drawing? Your current drawing will be lost.", "Confirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == 0){
			makeNewDrawing();
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("*.bin", "bin"));
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
		if (JOptionPane.showConfirmDialog(null, "Are you sure that you want do load a drawing? Your current drawing will be lost.", "Confirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == 0) {
			makeNewDrawing();
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
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
			JOptionPane.showMessageDialog(null, "Drawing is complete!", "Notification!", JOptionPane.WARNING_MESSAGE);
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
			decodeLog.decodePoint(point, parts, frame, model, logView);
		} else if (logLine.contains("Circle")) {
			String[] r = parts[1].split("=");
			String[] outerColor = parts[2].split("=");
			String[] innerColor = parts[3].split("=");
			Circle circle = new Circle(new Point(x,y), Integer.parseInt(r[1]), Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			decodeLog.decodeCircle(circle, parts, frame, model, logView);
		} else if (logLine.contains("Square")) {
			String[] outerColor = parts[2].split("=");
			String[] innerColor = parts[3].split("=");
			String[] side = parts[1].split("=");
			Square square = new Square(new Point(x,y), Integer.parseInt(side[1]), Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			decodeLog.decodeSquare(square, parts, frame, model, logView);
		} else if (logLine.contains("Rectangle")) {
			String[] outerColor = parts[3].split("=");
			String[] innerColor = parts[4].split("=");
			String[] height = parts[1].split("=");
			String[] width = parts[2].split("=");
			Rectangle rectangle = new Rectangle(new Point(x,y), Integer.parseInt(height[1]), Integer.parseInt(width[1]), Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			decodeLog.decodeRectangle(rectangle, parts, frame, model, logView);
		} else if (logLine.contains("Hexagon")) {
			String[] outerColor = parts[2].split("=");
			String[] innerColor = parts[3].split("=");
			String[] r = parts[1].split("=");
			Hexagon hexagon = new Hexagon(x, y, Integer.parseInt(r[1]));
			HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon, Color.decode(outerColor[1]), Color.decode(innerColor[1]));
			decodeLog.decodeHexagon(hexagonAdapter, parts, frame, model, logView);
		} else if (logLine.contains("Line")) {
			int endPointX = Integer.parseInt(parts[1].substring(parts[1].indexOf("(")+1, parts[1].indexOf(",")));
			int endPointY = Integer.parseInt(parts[1].substring(parts[1].indexOf(",")+1, parts[1].indexOf(")")));
			String[] color = parts[2].split("=");
			Line line = new Line(new Point(x, y), new Point(endPointX, endPointY), Color.decode(color[1]));
			decodeLog.decodeLine(line, parts, frame, model, logView);
		}
	}
	
	public void bringToFront() {
		if(countSelectedShapes() != 1) {
			return;
		}
		Shape selectedShape = model.getSelectedShapeList().get(0);
		BringToFront bringToFront = new BringToFront(model, frame, selectedShape);
		model.getUndoStack().offerLast(bringToFront);
		bringToFront.execute();
	}
	
	public void bringToBack() {
		if(countSelectedShapes() != 1) {
			return;
		}
		Shape selectedShape = model.getSelectedShapeList().get(0);
		BringToBack bringToBack = new BringToBack(model, frame, selectedShape);
		model.getUndoStack().offerLast(bringToBack);
		bringToBack.execute();
	}
	
	public void toFront() {
		if(countSelectedShapes() != 1) {
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
		if(countSelectedShapes() != 1) {
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
		Color tmpColor = JColorChooser.showDialog(null, "Choose color", previousColor);
		if (tmpColor != null) {
			frame.getButtonView().getBtnOuterColor().setBackground(tmpColor);
		}
	}
	
	public void chooseInnerColor(Color previousColor) {
		Color tmpColor = JColorChooser.showDialog(null, "Choose color", previousColor);
		if (tmpColor != null) {
			frame.getButtonView().getBtnInnerColor().setBackground(tmpColor);
		}
	}
	
	public Color openColorChooser(Color previousColor) {
		Color tmpColor = JColorChooser.showDialog(null, "Choose color", previousColor);
		
		if (tmpColor != null) {
			return tmpColor;
		}
		
		return previousColor;
	}
	

	public LogView getLogView() {
		return logView;
	}
	
}
