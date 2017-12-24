package mvc.model;

import java.util.ArrayList;

import shapes.Shape;


public class DrawingModel {
	private ArrayList<Shape> shapeList = new ArrayList<>();
	
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

}
