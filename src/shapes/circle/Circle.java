package shapes.circle;

import java.awt.Color;
import java.awt.Graphics;

import shapes.Moveable;
import shapes.SurfaceShape;
import shapes.line.Line;
import shapes.point.Point;


public class Circle extends SurfaceShape implements Moveable{
	private Point center;
	private int r;
	
	public Circle(){

	}
	public Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}
	public Circle(Point center, int r, Color color){
		this(center, r);
		setColor(color);
	}
	public Circle(Point center, int r, Color colorOuter, Color colorInner) {
		this(center, r, colorOuter);
		setSurfaceColor(colorInner);
	}
	
	public String toString(){
		return "Circle:(" + center.getX() + "," + center.getY() + "), r=" + r + ", outer color=" + getColor().getRGB() 
				+ ", inner color=" + getSurfaceColor().getRGB();
	}
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, r*2);
		fill(g);
		if(isSelected())
			selected(g);
	}
	public boolean contains(int x, int y){
		Point clickPoint = new Point(x, y);
		if(clickPoint.distance(center)<=r)
			return true;
		else
			return false;
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX()+r, center.getY())).selected(g);
	
	}
	public void fill(Graphics g){
		g.setColor(getSurfaceColor());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, r*2-2);
		
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Circle)
		{
			Circle tmp=(Circle)obj;
			if(this.center.equals(tmp.center) && this.r==tmp.r)
				return true;
			else
				return false;
		}
		else
			return false;	
	}
	

	public void moveTo(int x, int y){
		center.setX(x);
		center.setY(y);
	}
	public void moveFor(int x, int y){
		center.setX(center.getX()+x);
		center.setY(center.getY()+y);
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
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}

}
