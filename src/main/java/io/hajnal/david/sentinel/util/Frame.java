package io.hajnal.david.sentinel.util;

import java.time.LocalDateTime;

import org.opencv.core.Mat;

public class Frame {

	private LocalDateTime timestamp;
	private Mat frame;

	public Frame(LocalDateTime timestamp, Mat frame) {
		this.timestamp = timestamp;
		this.frame = frame;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Mat getFrame() {
		return frame;
	}

}
