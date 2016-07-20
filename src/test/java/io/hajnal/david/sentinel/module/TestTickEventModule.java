package io.hajnal.david.sentinel.module;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.Worker;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class TestTickEventModule {

	private static final int LARGE_INT = 200_000_000 + 200_000_000;
	private static final int FRAMES = 5;

	private TickEventModule underTest;
	private Worker worker;

	@Before
	public void setup() {
		underTest = new TickEventModule(FRAMES);
		worker = Mockito.mock(Worker.class);
		underTest.setWorker(worker);
	}
	
	@Test
	public void workerExcetuteShouldCalledWhenFrameNumberEqualsCounter() {
		// Given
		for (int i = 0; i < FRAMES; i++) {
			underTest.tickEvent();
		}
		// When
		underTest.execute(null);
		// Then
		verify(worker, times(1)).execute(null);
	}
	
	@Test
	public void workerExcetuteShouldCalledWithFrameWhenFrameNumberEqualsCounter() {
		// Given
		for (int i = 0; i < FRAMES; i++) {
			underTest.tickEvent();
		}
		Frame frame = Mockito.mock(Frame.class);
		// When
		underTest.execute(frame);
		// Then
		verify(worker, times(1)).execute(frame);
	}
	

	@Test
	public void isExcetuteShouldReturnFalseWhenModuleWasNotActiveWhileTickEventHappened() {
		// Given
		underTest.setActive(false);
		for (int i = 0; i < FRAMES; i++) {
			underTest.tickEvent();
		}
		underTest.setActive(true);
		// When
		boolean result = underTest.isExecute();
		// Then
		Assert.assertFalse(result);
	}


	@Test
	public void isExcetuteShouldReturnFalseWhenModuleIsNotActive() {
		// Given
		underTest.setActive(false);
		for (int i = 0; i < FRAMES; i++) {
			underTest.tickEvent();
		}
		// When
		boolean result = underTest.isExecute();
		// Then
		Assert.assertFalse(result);
	}

	@Test
	public void isExcetuteShouldAlwaysReturnTrueWhenSkippedFramesNumberIsOne() {
		// Given
		TickEventModule underTest = new TickEventModule(1);
		// When
		boolean result = true;
		for (int i = 0; i <= 30; i++) {
			underTest.tickEvent();
			result &= underTest.isExecute();
		}
		// Then
		Assert.assertTrue(result);
	}

	@Test
	public void isExcetuteShouldReturnTrueWhenFrameNumberEqualsCounter() {
		// Given
		for (int i = LARGE_INT; i < LARGE_INT + FRAMES; i++) {
			underTest.tickEvent();
		}
		// When
		boolean result = underTest.isExecute();
		// Then
		Assert.assertTrue(result);
	}

	@Test
	public void isExcetuteShouldReturnFalseWhenFrameNumberLessThanCounter() {
		// Given
		for (int i = LARGE_INT; i < LARGE_INT + FRAMES - 1; i++) {
			underTest.tickEvent();
		}
		// When
		boolean result = underTest.isExecute();
		// Then
		Assert.assertFalse(result);
	}

	@Test
	public void isExcetuteShouldReturnFalseWhenFrameNumberLargerThanCounter() {
		// Given
		for (int i = LARGE_INT; i <= LARGE_INT + FRAMES + 1; i++) {
			underTest.tickEvent();
		}
		// When
		boolean result = underTest.isExecute();
		// Then
		Assert.assertFalse(result);
	}

	@Test
	public void isExcetuteShouldReturnFalseWhenTickEventNeverCalled() {
		// Given

		// When
		boolean result = underTest.isExecute();
		// Then
		Assert.assertFalse(result);
	}

}
