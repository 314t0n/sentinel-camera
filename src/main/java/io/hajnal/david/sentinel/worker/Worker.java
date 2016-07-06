package io.hajnal.david.sentinel.worker;

import org.opencv.core.Mat;

public interface Worker {

	void execute(Mat frame);
}
