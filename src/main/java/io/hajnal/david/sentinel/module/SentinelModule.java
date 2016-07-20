package io.hajnal.david.sentinel.module;

import io.hajnal.david.sentinel.util.Frame;

public interface SentinelModule {
	
	void execute(Frame frame);
	boolean isExecute();
	void tickEvent();
	void setActive(boolean active);

}
