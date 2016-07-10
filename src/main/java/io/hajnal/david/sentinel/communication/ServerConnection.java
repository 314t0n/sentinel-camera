package io.hajnal.david.sentinel.communication;

public interface ServerConnection {

	void sendMessage(Message<?> message);
	
}
