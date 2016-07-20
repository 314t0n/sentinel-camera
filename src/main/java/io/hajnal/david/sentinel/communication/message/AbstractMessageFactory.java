package io.hajnal.david.sentinel.communication.message;

import io.hajnal.david.sentinel.util.Frame;

public abstract class AbstractMessageFactory<T extends Message<E>, E> {

	public abstract T createMessage(Frame frame);
	
}
