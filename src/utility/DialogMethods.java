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
	
	public static void showErrorMessage(String error) {
		JOptionPane.showMessageDialog(null, error, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showNotificationMessage(String notification) {
		JOptionPane.showMessageDialog(null, notification, "Notification!", JOptionPane.WARNING_MESSAGE);
	}
	
}
