package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import drawingFrame.DrawingFrame;
import mvc.model.DrawingModel;

public class SaveDrawing implements SaveStrategy {

	public SaveDrawing() {
	
	}

	@Override
	public void save(DrawingFrame drawingFrame, File file)  {
		DrawingModel model = drawingFrame.getDrawingController().getModel();
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(model.getShapeList());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
