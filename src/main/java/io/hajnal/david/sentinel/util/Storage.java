package io.hajnal.david.sentinel.util;

import java.time.LocalDateTime;

import org.opencv.core.Mat;
import org.springframework.stereotype.Component;

@Component
public interface Storage {
	
	void saveImage(Mat frame, LocalDateTime timestamp);
	Mat readImage(LocalDateTime timestamp);
	Mat readImage(String name);
	
}
