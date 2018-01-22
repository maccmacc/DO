package utility;

import javax.swing.JOptionPane;

public class DialogMethods {
	public static boolean askUserToConfirm(String question) {
		int response = JOptionPane.showConfirmDialog(null, question, "Confirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		switch (response) {
		case JOptionPane.YES_OPTION:
			return true;
		default:
			return false;
		}
	}
}
