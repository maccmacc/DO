package shapes.square;

import java.awt.Color;
import java.awt.Graphics;

import shapes.Moveable;
import shapes.SurfaceShape;
import shapes.line.Line;
import shapes.point.Point;

public class Square extends SurfaceShape implements Moveable{
	protected Point upperLeftPoint;
	protected int sideLength;
	

	public Square() {

	}

	public Square(Point upperLeftPoint, int sideLength) {
		this.upperLeftPoint = upperLeftPoint;
		this.sideLength = sideLength;
	}

	public Square(Point upperLeftPoint, int sideLength, Color color) {
		this(upperLeftPoint, sideLength);
		setColor(color);
	}
	
	public Square(Point upperLeftPoint, int sideLength, Color colorOuter, Color colorInner) {
		this(upperLeftPoint, sideLength, colorOuter);
		setSurfaceColor(colorInner);
	}
	 
	public Line diagonal(){
		return new Line(upperLeftPoint, new Point(upperLeftPoint.getX() + sideLength,upperLeftPoint.getY() + sideLength));
	}

	public Point center(){
		return diagonal().middlePointOfLine();
	}
	public String toString() {
		return "Square:(" + upperLeftPoint.getX() + "," + upperLeftPoint.getY() + "), side=" + sideLength + 
				 ", outer color=" + getColor().getRGB() + ", inner color=" + getSurfaceColor().getRGB();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square tmp = (Square) obj;
			if (this.upperLeftPoint.equals(tmp.upperLeftPoint) && this.sideLength == tmp.sideLength)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public void moveTo(int x, int y) {
		upperLeftPoint.setX(x);
		upperLeftPoint.setY(y);
	}

	public void moveFor(int x, int y) {
		upperLeftPoint.setX(upperLeftPoint.getX() + x);
		upperLeftPoint.setY(upperLeftPoint.getY() + y);
	}

	public int perimeter() {
		return 4 * sideLength;
	}

	public int surface() {
		return sideLength * sideLength;
	}
	public boolean contains(int x, int y) {
		if(this.getUpperLeftPoint().getX()<=x 
				&& x<=(this.getUpperLeftPoint().getX() + sideLength)
				&& this.getUpperLeftPoint().getY()<=y 
				&& y<=(this.getUpperLeftPoint().getY() + sideLength))
			return true;
		else 
			return false;
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getUpperLeftPoint(), new Point(getUpperLeftPoint().getX()+sideLength, getUpperLeftPoint().getY())).selected(g);
		new Line(getUpperLeftPoint(), new Point(getUpperLeftPoint().getX(), getUpperLeftPoint().getY()+sideLength)).selected(g);
		new Line(new Point(getUpperLeftPoint().getX()+sideLength, getUpperLeftPoint().getY()), diagonal().getEndPoint()).selected(g);
		new Line(new Point(getUpperLeftPoint().getX(), getUpperLeftPoint().getY()+sideLength), diagonal().getEndPoint()).selected(g);

	}
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), sideLength, sideLength);
		fill(g);
		if(isSelected())
			selected(g);
	}
	public void fill(Graphics g){
		g.setColor(getSurfaceColor());
		g.fillRect(upperLeftPoint.getX()+1, upperLeftPoint.getY()+1, sideLength-1, sideLength-1);
	}
	public int compareTo(Object o) {
		if(o instanceof Square){
			Square tmp  = (Square) o;
			return this.surface() - tmp.surface();
		}
		else 
			return 0;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}



}
