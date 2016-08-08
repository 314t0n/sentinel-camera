package io.hajnal.david.sentinel.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class TickEventModuleRunner implements Runner {

	private final ModuleHandler moduleHandler;
	private final TickEventEmitter eventEmitter;

	public TickEventModuleRunner(ModuleHandler moduleRunner, TickEventEmitter eventEmitter) {
		this.moduleHandler = moduleRunner;
		this.eventEmitter = eventEmitter;
	}

	@Override
	public void start() {
		eventEmitter.onEvent(moduleHandler);
		eventEmitter.start();
	}

	@Override
	public void stop() {
		eventEmitter.stop();
	}

}
