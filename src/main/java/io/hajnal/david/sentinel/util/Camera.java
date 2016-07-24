package io.hajnal.david.sentinel.util;

import org.opencv.core.Mat;

public interface Camera {
	
	void open();
	Mat getRawFrame();
	Frame getFrame();

}
