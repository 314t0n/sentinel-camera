package io.hajnal.david.sentinel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.OpenCVCameraImpl;

public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOGGER.debug("Sentinel v2.1");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try (OpenCVCameraImpl camera = new OpenCVCameraImpl(0)) {
			camera.open();
			for (int i = 0; i < 60; i++) {
				final int v = i;
				final Mat frame = camera.getFrame();

				new Thread() {
					public void run() {
						Imgcodecs.imwrite("./test_" + v + ".jpg", frame);
					}
				}.start();

				Thread.sleep(1000 / 60);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			LOGGER.debug("Sentinel finished");
		}
	}
}
