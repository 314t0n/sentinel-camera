package io.hajnal.david.sentinel.communication.Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClientSocket implements ClientSocket {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClientSocket.class);

	private Socket socket;
	private PrintWriter out;
	private String host;
	private int port;

	public SimpleClientSocket(String host, int port) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
		this.connect();
	}

	public void send(String message) {
		out.println(message);
		out.flush();
	}

	@Override
	public void close() {
		try {
			socket.close();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	@Override
	public void connect() throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutputStream());
	}
}
