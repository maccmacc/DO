package shapes.line;

import java.awt.Color;
import java.awt.Graphics;

import shapes.Shape;
import shapes.point.Point;

public class Line extends Shape{
	private Point startPoint;
	private Point endPoint;


	public Line(){

	}
	public Line(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	public Line(Point startPoint, Point endPoint,Color color){
		super(color);
		this.startPoint = startPoint;
		this.endPoint = endPoint;

	}
	public Point middlePointOfLine(){
		int middleX = (startPoint.getX() + endPoint.getX()) / 2;
		int middleY = (startPoint.getY() + endPoint.getY()) / 2;
		return new Point(middleX, middleY);
	}
	public String toString(){
		return "Line:("+startPoint.getX()+";" +startPoint.getY()+");(" + endPoint.getX()+";"+ endPoint.getY() + ");color=" +
				getColor().getRGB();
	}

	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line tmp=(Line)obj;
			if(this.startPoint.equals(tmp.startPoint) && this.endPoint.equals(tmp.endPoint))
				return true;
			else
				return false;
		}
		else
			return false;
	}


	public double length(){
		return startPoint.distance(endPoint);
	}

	public void moveTo(int x, int y){
		startPoint.setX(startPoint.getX()+x);
		startPoint.setY(startPoint.getY()+y);
		endPoint.setX(endPoint.getX()+x);
		endPoint.setY(endPoint.getY()+y);
	}
	public boolean contains(int x, int y){
		Point clickSpot = new Point(x, y);
		if(clickSpot.distance(clickSpot)+clickSpot.distance(endPoint)-this.length()<=0.5)
			return true;
		 else 
			return false;		
	}
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		middlePointOfLine().selected(g);
	}
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(isSelected())
			selected(g);
	}

	public int compareTo(Object o) {
		if(o instanceof Line){
			Line tmp = (Line) o;
			return (int)this.length() - (int)tmp.length();
		}
		else
			return 0;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}


}
