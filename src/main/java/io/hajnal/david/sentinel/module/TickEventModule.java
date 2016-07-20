package io.hajnal.david.sentinel.module;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.Worker;

@Component
public class TickEventModule implements SentinelModule {

	private boolean active;
	private int tick;
	private int numberOfSkippedFrames;

	@Autowired
	private Worker worker;

	public TickEventModule(int numberOfSkippedFrames) {
		this.numberOfSkippedFrames = numberOfSkippedFrames;
		this.active = true;
	}

	private boolean isTriggered() {
		return tick > 0 && tick % numberOfSkippedFrames == 0;
	}

	@Override
	public void setActive(boolean newState) {
		active = newState;
	}

	@Override
	public boolean isExecute() {
		return active && isTriggered();
	}

	@Override
	public void tickEvent() {
		tick++;
	}

	@Override
	public void execute(Frame frame) {
		worker.execute(frame);
	}

}
