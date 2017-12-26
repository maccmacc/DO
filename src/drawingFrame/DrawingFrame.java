package drawingFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import mvc.controller.ButtonController;
import mvc.controller.DrawingController;
import mvc.view.ButtonView;
import mvc.view.DrawingView;


public class DrawingFrame extends JFrame{
	private DrawingView drawingView = new DrawingView();
	private DrawingController drawingController;
	private ButtonView buttonView = new ButtonView();
	private ButtonController buttonController;
	
	public DrawingFrame() {
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(buttonView, BorderLayout.NORTH);
		getContentPane().add(drawingView, BorderLayout.CENTER);
		drawingView.setBackground(Color.WHITE);
		drawingView.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				drawingController.onPointAdded(e);
			}
		});
		buttonView.getBtnUndo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.onUndoButtonClicked(e);
			}
		});
		buttonView.getBtnRedo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.onRedoButtonClicked(e);
			}
		});
		
	}
	
	
	public DrawingView getView() {
		return drawingView;
	}
	public void setDrawingController(DrawingController controller) {
		this.drawingController = controller;
	}

	public void setButtonController(ButtonController buttonController) {
		this.buttonController = buttonController;
	}
	
}
