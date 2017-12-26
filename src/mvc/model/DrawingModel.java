package mvc.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import shapes.Command;
import shapes.Shape;


public class DrawingModel {
	private ArrayList<Shape> shapeList = new ArrayList<>();
	private Deque<Command> undoStack = new LinkedList<>();
	private Deque<Command> redoStack = new LinkedList<>();
	
	public void addShape(Shape s) {
		shapeList.add(s);
	}
	
	public void removeShape(Shape s) {
		shapeList.remove(s);
	}
	
	public Shape getShape(int i) {
		return shapeList.get(i);
	}

	public ArrayList<Shape> getShapeList() {
		return shapeList;
	}

	public Deque<Command> getUndoStack() {
		return undoStack;
	}

	public Deque<Command> getRedoStack() {
		return redoStack;
	}

}
