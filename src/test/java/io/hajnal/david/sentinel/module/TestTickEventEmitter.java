package io.hajnal.david.sentinel.module;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestTickEventEmitter {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final int SECOND_IN_MILLIS = 1000;
	private static final int FRAMES_PER_SECOND = 30;
	private TickEventEmitter underTest;
	private Timer timer; 

	@Before
	public void setup() {
		underTest = new TickEventEmitter(FRAMES_PER_SECOND);
		timer = new Timer();
	}

	@After
	public void tearDown() {
		underTest.stop();
	}

	@Test
	public void startShouldThrowRuntimeExceptionWhenNoCallableSet() throws Exception {
		// Given
		exception.expect(RuntimeException.class);
		// When
		underTest.start();
		// Then
	}

	@Test
	public void callableCallMethodCalledMultipleTimesWhenStartMethodCalled() throws Exception {
		// Given
		@SuppressWarnings("unchecked")
		Callable<Void> callable = Mockito.mock(Callable.class);
		underTest.onEvent(callable);
		// When
		underTest.start();
		schedule(new StopTask(underTest), FRAMES_PER_SECOND * 3);
		Thread.sleep(FRAMES_PER_SECOND * 3);
		// Then
		verify(callable, atLeast(3)).call();
	}

	@Test
	public void callableCallMethodCalledWhenStartMethodCalled() throws Exception {
		// Given
		@SuppressWarnings("unchecked")
		Callable<Void> callable = Mockito.mock(Callable.class);
		underTest.onEvent(callable);
		// When
		underTest.start();
		schedule(new StopTask(underTest), FRAMES_PER_SECOND - 1);
		Thread.sleep(FRAMES_PER_SECOND);
		// Then
		verify(callable, times(1)).call();
	}

	@Test
	public void callableCallMethodCalledExactlyGivenFramesPerSceondWhenAfterOneSecondThatStartMethodCalled() throws Exception {
		// Given
		@SuppressWarnings("unchecked")
		Callable<Void> callable = Mockito.mock(Callable.class);
		underTest.onEvent(callable);
		// When
		underTest.start();
		schedule(new StopTask(underTest), SECOND_IN_MILLIS);
		Thread.sleep(SECOND_IN_MILLIS);
		// Then
		verify(callable, atLeast(FRAMES_PER_SECOND)).call();
	}

	private long schedule(TimerTask task, long delayMillis) {
		long scheduledRunMillis = System.currentTimeMillis() + delayMillis;
		Date scheduledRunTime = new Date(scheduledRunMillis);
		timer.schedule(task, scheduledRunTime);
		return scheduledRunMillis;
	}

	private class StopTask extends TimerTask {
		private final TickEventEmitter emitter;

		StopTask(TickEventEmitter emitter) {
			this.emitter = emitter;
		}

		@Override
		public void run() {
			emitter.stop();
		}
	}
}
