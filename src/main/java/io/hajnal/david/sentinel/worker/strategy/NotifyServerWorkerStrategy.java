package io.hajnal.david.sentinel.worker.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.hajnal.david.sentinel.communication.ServerConnection;
import io.hajnal.david.sentinel.communication.message.AbstractMessageFactory;
import io.hajnal.david.sentinel.communication.message.Message;
import io.hajnal.david.sentinel.util.Frame;

public class NotifyServerWorkerStrategy<T extends Message<E>, E> extends AbstractWorkerStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotifyServerWorkerStrategy.class);

	private AbstractMessageFactory<T, E> messageFactory;

	private ServerConnection connection;

	public NotifyServerWorkerStrategy(AbstractMessageFactory<T, E> messageFactory, ServerConnection connection) {
		super();
		this.messageFactory = messageFactory;
		this.connection = connection;
	}

	@Override
	public void execute(Frame frame) {
		connection.sendMessage(messageFactory.createMessage(frame));
	}

}
