package io.hajnal.david.sentinel.communication.message;

import java.time.LocalDateTime;

public class Payload {

	protected LocalDateTime timestamp;

	public Payload(LocalDateTime timestamp) {
		super();
		this.timestamp = timestamp;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
