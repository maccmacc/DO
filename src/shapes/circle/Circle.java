package shapes.circle;

import java.awt.Color;
import java.awt.Graphics;

import shapes.Moveable;
import shapes.SurfaceShape;
import shapes.line.Line;
import shapes.point.Point;


public class Circle extends SurfaceShape implements Moveable{
	private Point centre;
	private int r;
	
	public Circle(){

	}
	public Circle(Point centre, int r){
		this.centre = centre;
		this.r = r;
	}
	public Circle(Point centre, int r, Color color){
		this(centre, r);
		setColor(color);
	}
	
	public String toString(){
		return "centre " + centre + ", radius=" + r;
	}
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawOval(centre.getX()-r, centre.getY()-r, 2*r, r*2);
		if(isSelected())
			selected(g);
	}
	public boolean contains(int x, int y){
		Point clickPoint = new Point(x, y);
		if(clickPoint.distance(centre)<=r)
			return true;
		else
			return false;
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(centre.getX(), centre.getY()-r), new Point(centre.getX(), centre.getY() + r)).selected(g);
		new Line(new Point(centre.getX()-r, centre.getY()), new Point(centre.getX()+r, centre.getY())).selected(g);
	
	}
	public void fill(Graphics g){
		g.setColor(getSurfaceColor());
		g.fillOval(centre.getX()-r+1, centre.getY()-r+1, 2*r-2, r*2-2);
		
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Circle)
		{
			Circle tmp=(Circle)obj;
			if(this.centre.equals(tmp.centre) && this.r==tmp.r)
				return true;
			else
				return false;
		}
		else
			return false;	
	}
	

	public void moveTo(int x, int y){
		centre.setX(x);
		centre.setY(y);
	}
	public void moveFor(int x, int y){
		centre.setX(centre.getX()+x);
		centre.setY(centre.getY()+y);
	}
	public double surface(){
		return r * r * Math.PI;
	}
	public double perimeter(){
		return 2 * r * Math.PI;
	}
	public int compareTo(Object o) {
		if(o instanceof Circle){
			Circle tmp  = (Circle) o;
			return this.r - tmp.r;
		}
		else
			return 0;
	}
	public Point getCentre() {
		return centre;
	}
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}

}
