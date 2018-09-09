package shapes.rectangle;

import java.awt.Color;
import java.awt.Graphics;

import shapes.line.Line;
import shapes.point.Point;
import shapes.square.Square;


public class Rectangle extends Square{
	private int width;

	public Rectangle(){

	}
	public Rectangle(Point upperLeftPoint, int height, int width){
		super(upperLeftPoint, height);
		this.width = width;
	}
	public Rectangle(Point upperLeftPoint, int height, int width, Color color){
		this(upperLeftPoint, height, width);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color colorOuter, Color colorInner){
		this(upperLeftPoint, height, width, colorOuter);
		setSurfaceColor(colorInner);
	}
	public Line diagonal(){
		return new Line(upperLeftPoint, new Point(upperLeftPoint.getX() + getSideLength(),upperLeftPoint.getY() + width));
	}
	public Point center(){
		return diagonal().middlePointOfLine();
	}
	public boolean equals(Object obj){
		if(obj instanceof Rectangle){
			Rectangle tmp=(Rectangle) obj;
			if(this.upperLeftPoint.equals(tmp.upperLeftPoint) && this.width==tmp.width && this.getSideLength()==tmp.getSideLength())
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public String toString(){
		return "Rectangle:("+upperLeftPoint.getX() + "," + upperLeftPoint.getY() + ");height=" + width + 
				 ";width="+getSideLength() + ";outer color=" + getColor().getRGB() 
					+ ";inner color=" + getSurfaceColor().getRGB();
	}

	public int perimeter(){
		return 2 * (width + getSideLength());
	}
	public int surface(){
		return width * getSideLength();
	}
	public boolean contains(int x, int y) {
		if(this.getUpperLeftPoint().getX()<=x 
				&& x<=(this.getUpperLeftPoint().getX() + sideLength)
				&& this.getUpperLeftPoint().getY()<=y 
				&& y<=(this.getUpperLeftPoint().getY() + width))
			return true;
		else 
			return false;
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getUpperLeftPoint(), new Point(getUpperLeftPoint().getX()+sideLength, getUpperLeftPoint().getY())).selected(g);
		new Line(getUpperLeftPoint(), new Point(getUpperLeftPoint().getX(), getUpperLeftPoint().getY()+width)).selected(g);
		new Line(new Point(getUpperLeftPoint().getX()+sideLength, getUpperLeftPoint().getY()), diagonal().getEndPoint()).selected(g);
		new Line(new Point(getUpperLeftPoint().getX(), getUpperLeftPoint().getY()+width), diagonal().getEndPoint()).selected(g);
	}
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), sideLength, width);
		fill(g);
		if(isSelected())
			selected(g);
	}
	public void fill(Graphics g){
		g.setColor(getSurfaceColor());
		g.fillRect(upperLeftPoint.getX()+1, upperLeftPoint.getY()+1, sideLength-1, width-1);
		
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

}
