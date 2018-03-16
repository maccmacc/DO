package drawingFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import mvc.controller.ButtonController;
import mvc.controller.DrawingController;
import mvc.view.ButtonView;
import mvc.view.DrawingView;
import javax.swing.JButton;


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
				if (!buttonView.getTglbtnSelect().isSelected())
					drawingController.onPointAdded(e);
				else
					buttonController.onSelectButtonClicked(e);
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
		buttonView.getTglbtnSelect().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(buttonView.getTglbtnSelect().isSelected())
					buttonController.unselectShapes();
			}
		});
		buttonView.getBtnDelete().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.deleteButtonClickedHandler();
			}
		});
		buttonView.getBtnUpdate().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.modifyShape();
			}
		});
		buttonView.getCmbShapes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //probala sam sa ovim listenerom msm da bi on trebao da radi
				drawingController.checkShape(); // ovde bi trebalo da prosledim e da bi posle u toj metodi prosledjivala drugim metodama
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


	public ButtonView getButtonView() {
		return buttonView;
	}
	
}
