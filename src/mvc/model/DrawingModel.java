package mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import shapes.Command;
import shapes.Shape;
import shapes.observer.ButtonObserver;
import shapes.observer.Observer;
import shapes.observer.Subject;


public class DrawingModel implements Subject, Serializable {
	
	static final long serialVersionUID = 1L;
	
	private ArrayList<Shape> shapeList = new ArrayList<>();
	private Deque<Command> undoStack = new LinkedList<>();
	private Deque<Command> redoStack = new LinkedList<>();
	private ArrayList<Shape> selectedShapeList = new ArrayList<>();
	private ArrayList<Observer> observerList = new ArrayList<>();
	private ArrayList<String> logList = new ArrayList<>();
	
	public void addShape(Shape s) {
		shapeList.add(s);
	}
	
	public void removeShape(Shape s) {
		shapeList.remove(s);
	}
	
	@Override
	public void addObserver(Observer ob) {
		observerList.add(ob);
	}

	@Override
	public void deleteObserver(Observer ob) {
		observerList.remove(ob);
	}

	@Override
	public void notifyAllObservers() {
		for (Observer observer : observerList) {
			observer.update(this.selectedShapeList.size());
		}
	}

	public void setSelectedShapeList(ArrayList<Shape> selectedShapeList) {
		this.selectedShapeList = selectedShapeList;
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

	public ArrayList<Shape> getSelectedShapeList() {
		return selectedShapeList;
	}

	public ArrayList<String> getLogList() {
		return logList;
	}

}
