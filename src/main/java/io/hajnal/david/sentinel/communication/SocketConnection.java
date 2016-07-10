package io.hajnal.david.sentinel.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.communication.Socket.AbstractSocketFactory;
import io.hajnal.david.sentinel.communication.Socket.ClientSocket;

@Component
public class SocketConnection implements ServerConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(SocketConnection.class);

	private String host;
	private int port;

	private AbstractSocketFactory socketFactory;

	@Autowired
	public SocketConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Autowired
	public void setSocketFactory(AbstractSocketFactory socketFactory) {
		this.socketFactory = socketFactory;
	}

	@Override
	public void sendMessage(Message<?> message) {
		try (ClientSocket socket = socketFactory.createSocket(host, port)) {
			socket.send(message.format());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
