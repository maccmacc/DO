package strategy;


import java.io.File;

import drawingFrame.DrawingFrame;

public interface SaveStrategy {
	public void save(DrawingFrame drawingFrame, File file);
}
