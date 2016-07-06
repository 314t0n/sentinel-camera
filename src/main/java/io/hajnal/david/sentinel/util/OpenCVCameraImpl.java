package io.hajnal.david.sentinel.util;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.springframework.stereotype.Component;

@Component
public class OpenCVCameraImpl implements Camera, AutoCloseable {

	private int cameraId;
	private VideoCapture camera;

	public OpenCVCameraImpl(int cameraId) {
		this.cameraId = cameraId;
	}

	@Override
	public void close() throws Exception {
		if (camera != null) {
			camera.release();
		}
		camera = null;
	}

	@Override
	public void open() {
		camera = new VideoCapture(cameraId);
	}

	public Mat getFrame() {
		if (!camera.isOpened()) {
			open();
		}
		Mat frame = new Mat();
		camera.read(frame);
		return frame;
	}

}
