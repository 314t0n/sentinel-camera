package io.hajnal.david.sentinel.communication.Socket;

import java.io.IOException;
import java.net.UnknownHostException;

public interface ClientSocket extends AutoCloseable {

	void send(String message);
	void connect()  throws UnknownHostException, IOException;
	
}
