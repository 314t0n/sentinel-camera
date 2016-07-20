package io.hajnal.david.sentinel.communication.message;

import java.time.LocalDateTime;

public class ImagePayload extends Payload {

	private byte[] image;

	public ImagePayload(LocalDateTime timestamp, byte[] image) {
		super(timestamp);
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

}
