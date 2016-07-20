package io.hajnal.david.sentinel.communication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.hajnal.david.sentinel.communication.message.Message;
import io.hajnal.david.sentinel.communication.socket.AbstractSocketFactory;
import io.hajnal.david.sentinel.communication.socket.ClientSocket;
import io.hajnal.david.sentinel.config.CommunicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommunicationConfig.class)
public class TestSocketConnection {

	private static final int PORT = 9999;
	private static final String HOST = "127.0.0.1";
	private static final String TEST_MESSAGE = "echo";

	@Autowired
	private ApplicationContext context;

	private SocketConnection underTest;

	private AbstractSocketFactory mockFactory;

	@Before
	public void setUp() {
		underTest = new SocketConnection(HOST, PORT);
		mockFactory = Mockito.mock(AbstractSocketFactory.class);
		underTest.setSocketFactory(mockFactory);
	}

	@Test
	public void clientSocketSendCalledWhenSendMessageCalled() throws UnknownHostException, IOException {
		// Given
		@SuppressWarnings("unchecked")
		Message<String> message = (Message<String>) Mockito.mock(Message.class);
		ClientSocket mockSocket = Mockito.mock(ClientSocket.class);
		when(mockFactory.createSocket(HOST, PORT)).thenReturn(mockSocket);
		when(message.format()).thenReturn(TEST_MESSAGE);
		// When
		underTest.sendMessage(message);
		// Then
		verify(mockSocket, times(1)).send(TEST_MESSAGE);
		verify(message, times(1)).format();
		verify(mockFactory, times(1)).createSocket(HOST, PORT);
	}
}
