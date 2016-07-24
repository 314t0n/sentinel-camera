package io.hajnal.david.sentinel.module;

import java.util.concurrent.Callable;

public interface EventEmitter extends Runner {
	void onEvent(Callable<Void> callable);
}
