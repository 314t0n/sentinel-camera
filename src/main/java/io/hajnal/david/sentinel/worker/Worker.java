package io.hajnal.david.sentinel.worker;

import io.hajnal.david.sentinel.util.Frame;

public interface Worker {

	void execute(Frame frame);
}
