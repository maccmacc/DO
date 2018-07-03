package mvc.view;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import shapes.Shape;

public class LogView extends JPanel {
	
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	JList logList = new JList();
	
	public LogView() {

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		add(logList);
		
		scrollPane.setViewportView(logList);
		logList.setModel(dlm);
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public JList getLogList() {
		return logList;
	}

}
