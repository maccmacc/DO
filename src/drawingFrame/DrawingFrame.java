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
import mvc.view.LogView;
import utility.ModifyShapesDialogs;

import javax.swing.JButton;
import javax.swing.JPanel;


public class DrawingFrame extends JFrame{
	private DrawingView drawingView = new DrawingView();
	private DrawingController drawingController;
	private ButtonView buttonView = new ButtonView();
	private ButtonController buttonController;
	private LogView logView;

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
					drawingController.checkShape(e);
				else
					buttonController.onSelectButtonClicked(e);
			}
		});
		buttonView.getBtnUndo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.onUndoButtonClicked();
			}
		});
		buttonView.getBtnRedo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.onRedoButtonClicked();
			}
		});
		buttonView.getTglbtnSelect().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(buttonView.getTglbtnSelect().isSelected())
					buttonController.unselectShapes();
			}
		});
	
		buttonView.getBtnDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonController.deleteButtonClickedHandler();
			}
		});
		buttonView.getBtnUpdate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonController.modifyShape();
			}
		});
		buttonView.getBtnOuterColor().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.chooseOuterColor(buttonView.getBtnOuterColor().getBackground());
			}
		});
		buttonView.getBtnInnerColor().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.chooseInnerColor(buttonView.getBtnInnerColor().getBackground());
			}
		});
		buttonView.getBtnSaveLog().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.saveLog();
			}
		});
		buttonView.getBtnSaveDrawing().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.saveDrawing();
			}
		});
		buttonView.getBtnOpenDrawing().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					buttonController.openDrawing();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonView.getBtnOpenLog().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.openLog();
			}
		});
		buttonView.getBtnDrawFromLog().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonController.drawFromLog();
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

	public void setLogView(LogView logView) {
		this.logView = logView;
		getContentPane().add(logView, BorderLayout.SOUTH);
	}

	public LogView getLogView() {
		return logView;
	}


	public DrawingController getDrawingController() {
		return drawingController;
	}


	public ButtonController getButtonController() {
		return buttonController;
	}


	public DrawingView getDrawingView() {
		return drawingView;
	}
	
}
