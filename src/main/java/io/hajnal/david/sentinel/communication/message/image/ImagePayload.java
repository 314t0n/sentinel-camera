package io.hajnal.david.sentinel.communication.message.image;

import java.time.LocalDateTime;

import io.hajnal.david.sentinel.communication.message.Payload;

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
