package io.hajnal.david.sentinel.communication.message;

import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import io.hajnal.david.sentinel.util.Frame;

public class ImageMessageFactoryImpl extends AbstractMessageFactory<Message<ImagePayload>, ImagePayload> {

	public static final String IMAGE_TYPE = ".png";
	public static final String NAMESPACE_ID = "imagelog";

	@Override
	public Message<ImagePayload> createMessage(Frame frame) {
		ImagePayload payload = new ImagePayload(frame.getTimestamp(), bytesFromFrame(frame));
		return new Message<ImagePayload>(NAMESPACE_ID, payload);
	}

	protected byte[] bytesFromFrame(Frame frame) {
		MatOfByte buffer = new MatOfByte();
		Imgcodecs.imencode(IMAGE_TYPE, frame.getFrame(), buffer);
		int size = (int) buffer.total() * buffer.channels();
		byte[] imageBytes = new byte[size];
		buffer.get(0, 0, imageBytes);
		return imageBytes;
	}

}
