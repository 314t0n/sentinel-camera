package io.hajnal.david.sentinel.module;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import io.hajnal.david.sentinel.config.WorkerConfig;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = WorkerConfig.class)
public class TestTickEventModule {

	private static final int LARGE_INT = 200_000_000 + 200_000_000;
	private static final int FRAMES = 5;

	private TickEventModule underTest;

	@Before
	public void setup() {
		underTest = new TickEventModule(FRAMES);
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
