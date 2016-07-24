package io.hajnal.david.sentinel;

import java.io.IOException;
import java.util.Base64;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import io.hajnal.david.sentinel.communication.message.Message;
import io.hajnal.david.sentinel.communication.socket.ClientSocket;
import io.hajnal.david.sentinel.communication.socket.SimpleClientSocket;
import io.hajnal.david.sentinel.util.OpenCVCameraImpl;

public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public class Csomag {
		public byte[] adat;
	}

	public static void main(String[] args) {
		LOGGER.debug("Sentinel v2.1");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try (OpenCVCameraImpl camera = new OpenCVCameraImpl(0)) {
			camera.open();
			for (int i = 0; i < 600; i++) {
				final int v = i;
				final Mat frame = camera.getRawFrame();
 
				MatOfByte buffer = new MatOfByte();
				Imgcodecs.imencode(".png", frame, buffer);

				int size = (int) buffer.total() * buffer.channels();
				byte[] data = new byte[size];
				buffer.get(0, 0, data);

				StringBuilder sb = new StringBuilder();

				// sb.append("data:image/png;base64,");
				// sb.append(new String(Base64.getEncoder().encode(data)));

				Csomag cs = new App().new Csomag();
				cs.adat = data;

				// Message<String> message = new Message<>("image",
				// sb.toString());

				Message<Csomag> message = new Message<>("image", cs);
				// System.out.println( sb.toString());

				new Thread() {
					public void run() {
						// Imgcodecs.imwrite("./test_" + v + ".jpg", frame);
						ClientSocket socket = null;
						try {
							socket = new SimpleClientSocket("127.0.0.1", 9000);
							socket.send(message.format());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							try {
								socket.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
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
