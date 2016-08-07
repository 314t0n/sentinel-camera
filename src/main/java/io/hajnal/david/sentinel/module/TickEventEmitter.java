package io.hajnal.david.sentinel.module;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TickEventEmitter implements EventEmitter {

	private static final Logger LOGGER = LoggerFactory.getLogger(TickEventEmitter.class);

	private static final int SECOND_IN_MILLIS = 1000;
	private CallTask callTask;
	private int tickPerSecond;
	private final Timer timer;

	public TickEventEmitter(int tickPerSecond) {
		this.setTickPerSecond(tickPerSecond);
		timer = new Timer();
	}

	public void onEvent(Callable<Void> callable) {
		callTask = new CallTask(callable);
	}

	@Override
	public void start() {
		try {
			timer.scheduleAtFixedRate(callTask, 1000, SECOND_IN_MILLIS / tickPerSecond);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public void stop() {
		timer.cancel();
	}

	public void setTickPerSecond(int tickPerSecond) {
		this.tickPerSecond = tickPerSecond;
	}

	private class CallTask extends TimerTask {
		private final Callable<Void> callable;

		CallTask(Callable<Void> callable) {
			this.callable = callable;
		}

		@Override
		public void run() {
			try {
				callable.call();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
