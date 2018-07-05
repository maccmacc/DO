package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import drawingFrame.DrawingFrame;
import mvc.controller.ButtonController;

public class SaveLog implements SaveStrategy {

	public SaveLog() {
		
	}

	@Override
	public void save(DrawingFrame drawingFrame, File file) {
			try {
			FileWriter fileWriter = new FileWriter(file);
			for(int i = 0; i < drawingFrame.getLogView().getDlm().size(); i++)
			{
				fileWriter.write(drawingFrame.getLogView().getDlm().getElementAt(i) + System.getProperty("line.separator"));
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
