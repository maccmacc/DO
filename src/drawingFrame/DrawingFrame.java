package drawingFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import mvc.controller.DrawingController;
import mvc.view.DrawingView;


public class DrawingFrame extends JFrame{
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	public DrawingFrame() {
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.onPointAdded(e);
			}
		});
	}
	
	
	public DrawingView getView() {
		return view;
	}
	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
}
