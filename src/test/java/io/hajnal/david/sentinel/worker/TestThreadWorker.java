package io.hajnal.david.sentinel.worker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import io.hajnal.david.sentinel.config.WorkerConfig;
import io.hajnal.david.sentinel.worker.strategy.AbstractWorkerStrategy;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = WorkerConfig.class)
public class TestThreadWorker {

	@InjectMocks
	private ThreadWorker underTest;

	@Mock
	private AbstractWorkerStrategy strategy;

	@Test
	public void strategyExecuteIsCalledWhenExecuteIsCalled() {
		// Given

		// When
		underTest.execute(null);
		// Then
		verify(strategy, times(1)).execute(null);
	}

}
