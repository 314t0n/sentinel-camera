package io.hajnal.david.sentinel.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.ThreadWorker;
import io.hajnal.david.sentinel.worker.Worker;

@Component
public class TickEventModule implements SentinelModule {

	private static final Logger LOGGER = LoggerFactory.getLogger(TickEventModule.class);

	public static final int DEFAULT_FPS = 30;
	private boolean active;
	private int tick;
	private int numberOfSkippedFrames;

	@Autowired
	private Worker worker;

	public TickEventModule() {
		this(DEFAULT_FPS);
	}

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
		if (active) {
			tick++;
		}
	}

	@Override
	public void execute(Frame frame) {
		worker.execute(frame);
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public void setNumberOfSkippedFrames(int numberOfSkippedFrames) {
		this.numberOfSkippedFrames = numberOfSkippedFrames;
	}
	
}
