package mvc.view;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import mvc.model.DrawingModel;
import shapes.Shape;

public class DrawingView extends JPanel {
	DrawingModel model;

	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getShapeList().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
		repaint();
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
