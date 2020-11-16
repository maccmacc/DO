package drawingFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import mvc.view.ButtonViewLeft;
import mvc.view.ButtonViewRight;
import mvc.view.DrawingView;
import mvc.view.LogView;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.Component;

public class DrawingFrame extends JFrame {
  private DrawingView drawingView = new DrawingView();
  private DrawingController drawingController;
  private ButtonView buttonView = new ButtonView();
  private ButtonViewLeft buttonViewLeft = new ButtonViewLeft();
  private ButtonViewRight buttonViewRight = new ButtonViewRight();
  private ButtonController buttonController;
  private LogView logView;

  public DrawingFrame() {
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    getContentPane().setLayout(new BorderLayout(0, 0));
    buttonView.setAlignmentY(0.0f);
    getContentPane().add(buttonView, BorderLayout.NORTH);
    GridBagLayout gridBagLayout = (GridBagLayout) buttonViewLeft.getLayout();
    gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
    gridBagLayout.columnWidths = new int[] {200};
    buttonViewLeft.setMinimumSize(new Dimension(344, 50));
    getContentPane().add(buttonViewLeft, BorderLayout.WEST);
    getContentPane().add(buttonViewRight, BorderLayout.EAST);
    getContentPane().add(drawingView, BorderLayout.CENTER);
    drawingView.setBackground(Color.WHITE);
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
        buttonViewRight.getBtnBringToFront().addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            buttonController.bringToFront();
          }
        });
        buttonViewRight.getBtnBringToBack().addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            buttonController.bringToBack();
          }
        });
        buttonViewRight.getBtnToFront().addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            buttonController.toFront();
          }
        });
        buttonViewRight.getBtnToBack().addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            buttonController.toBack();
          }
        }); 
    drawingView.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        if (!buttonView.getTglbtnSelect().isSelected())
          drawingController.checkShape(e);
        else
          buttonController.onSelectButtonClicked(e);
      }
    });
    buttonViewLeft.getBtnSaveLog().addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        buttonController.saveLog();
      }
    });
    buttonViewLeft.getBtnSaveDrawing().addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        buttonController.saveDrawing();
      }
    });
    buttonViewLeft.getBtnOpenDrawing().addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        try {
          buttonController.openDrawing();
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }
      }
    });
    buttonViewLeft.getBtnOpenLog().addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        buttonController.openLog();
      }
    });
    buttonViewLeft.getBtnDrawFromLog().addMouseListener(new MouseAdapter() {
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
    logView.setLayout(new CardLayout(0, 0));
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

public ButtonViewRight getButtonViewRight() {
	return buttonViewRight;
}

}
