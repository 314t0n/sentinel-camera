package io.hajnal.david.sentinel.communication.message.image;

import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import io.hajnal.david.sentinel.communication.message.AbstractMessageFactory;
import io.hajnal.david.sentinel.communication.message.Message;
import io.hajnal.david.sentinel.util.Frame;

public class ImageMessageFactoryImpl extends AbstractMessageFactory<Message<ImagePayload>, ImagePayload> {

	public final String imageType;
	public final String namespaceId;

	public ImageMessageFactoryImpl(String imageType, String namespaceId) {
		this.imageType = imageType;
		this.namespaceId = namespaceId;
	}

	@Override
	public Message<ImagePayload> createMessage(Frame frame) {
		ImagePayload payload = new ImagePayload(frame.getTimestamp(), bytesFromFrame(frame));
		return new Message<ImagePayload>(namespaceId, payload);
	}

	protected byte[] bytesFromFrame(Frame frame) {
		MatOfByte buffer = new MatOfByte();
		Imgcodecs.imencode(imageType, frame.getFrame(), buffer);
		int size = (int) buffer.total() * buffer.channels();
		byte[] imageBytes = new byte[size];
		buffer.get(0, 0, imageBytes);
		return imageBytes;
	}

}
