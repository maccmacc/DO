package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Moveable{
	private int x;
	private int y;


	public Point(){

	}
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, Color color){
		this(x, y);
		setColor(color);
	}	

	public String toString(){
		return "(" + this.x + ","+this.y+")";			
	}

	public boolean equals(Object obj){
		if(obj instanceof Point){
			Point tmp=(Point)obj;
			if(this.x==tmp.getX() && this.y==tmp.getY())
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public void moveTo(int newX, int newY){
		x = newX;
		setY(newY);
	}
	public void moveFor(int newX, int newY){
		x += newX;
		y = y + newY;
	}
	public double distance(Point t2){
		double dx = x - t2.getX();
		double dy = y - t2.y;
		double result = Math.sqrt(dx*dx + dy*dy);
		return result;
	}
	public boolean contains(int x, int y){
		Point clickSpot = new Point(x, y);
		if(clickSpot.distance(this)<=2)
			return true;
		else
			return false;
	}
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x-3, y-3, 6, 6);
	}
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		if(isSelected())
			selected(g);
	}
	
	public int compareTo(Object o) {
		Point zero = new Point(0,0);
		Point newPoint  = (Point) o;
		return (int) (this.distance(zero) - newPoint.distance(zero));
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int newY){
		y = newY;
	}
	




}
