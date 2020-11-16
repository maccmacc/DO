package shapes.hexagon;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import shapes.Moveable;
import shapes.SurfaceShape;

public class HexagonAdapter extends SurfaceShape implements Moveable{

	private Hexagon hexagon;
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Hexagon hexagon, Color color) {
		this(hexagon);
		setColor(color);
	}

	public HexagonAdapter(Hexagon hexagon, Color colorOuter, Color colorInner) {
		this(hexagon, colorOuter);
		setSurfaceColor(colorInner);
	}
	
	public String toString() {
		return "Hexagon:(" + hexagon.getX() + "," + hexagon.getY() + ");r=" + hexagon.getR() + ";outer color=" + this.hexagon.getBorderColor().getRGB() 
				+ ";inner color=" + this.hexagon.getAreaColor().getRGB();
	}
 
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter temp = ((HexagonAdapter) obj);
			if(hexagon.getX() == temp.getHexagon().getX() && hexagon.getY()==temp.getHexagon().getY() && hexagon.getR() == temp.getHexagon().getR())
			return true;
			else
				return false;
		} else
			return false;
	}
	
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof HexagonAdapter) {
			return hexagon.getR() - ((HexagonAdapter) obj).getHexagon().getR();
		}
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getSurfaceColor());
		g.fillRect(hexagon.getX()+1, hexagon.getY()+1, hexagon.getR()-1, hexagon.getR()-1);	
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		hexagon.setX(hexagon.getX() + x);
		hexagon.setY(hexagon.getY() + y);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public boolean isSelected() {
		return hexagon.isSelected();
	}
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected); 
	}
	
	@Override
	public void setColor(Color color) {
		this.hexagon.setBorderColor(color);
	}
	
	@Override
	public void setSurfaceColor(Color surfaceColor) {
		this.hexagon.setAreaColor(surfaceColor);
	}
	
	public Color getColor() {
		return this.hexagon.getBorderColor();
	}
	
	public Color getSurfaceColor() {
		return this.hexagon.getAreaColor();
	}

}
