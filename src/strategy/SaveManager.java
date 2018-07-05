package strategy;

import java.io.File;

import drawingFrame.DrawingFrame;

public class SaveManager implements SaveStrategy {
	
	private SaveStrategy saveStrategy;
	

	public SaveManager(SaveStrategy saveStrategy) {
		this.saveStrategy = saveStrategy;
	}

	@Override
	public void save(DrawingFrame drawingFrame, File file) {
		saveStrategy.save(drawingFrame, file);
	}

}
