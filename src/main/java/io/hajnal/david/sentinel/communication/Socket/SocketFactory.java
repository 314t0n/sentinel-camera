package io.hajnal.david.sentinel.communication.Socket;

import java.io.IOException;
import java.net.UnknownHostException;

public class SocketFactory extends AbstractSocketFactory {

	@Override
	public ClientSocket createSocket(String host, int port) throws UnknownHostException, IOException {
		return new SimpleClientSocket(host, port);
	}

}
