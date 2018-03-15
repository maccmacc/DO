package shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape{
	private Color surfaceColor = Color.WHITE;
	
	public abstract void fill(Graphics g);

	public Color getSurfaceColor() {
		return surfaceColor;
	}

	public void setSurfaceColor(Color surfaceColor) {
		this.surfaceColor = surfaceColor;
	}

	

}
