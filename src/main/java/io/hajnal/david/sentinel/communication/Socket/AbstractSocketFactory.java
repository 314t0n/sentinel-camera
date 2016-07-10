package io.hajnal.david.sentinel.communication.Socket;

import java.io.IOException;
import java.net.UnknownHostException;

public abstract class AbstractSocketFactory {

	public abstract ClientSocket createSocket(String host, int port) throws UnknownHostException, IOException;
}
