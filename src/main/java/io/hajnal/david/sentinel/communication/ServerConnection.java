package io.hajnal.david.sentinel.communication;

import io.hajnal.david.sentinel.communication.message.Message;

public interface ServerConnection {

	void sendMessage(Message<?> message);
	
}
