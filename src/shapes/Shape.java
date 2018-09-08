package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Comparable, Serializable{
	private Color color = Color.BLACK;
	private boolean selected;
	
	public Shape() {
		
	}
	public Shape(Color color){
		this.color = color;
	}
	
	public abstract void draw(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	
	
}
