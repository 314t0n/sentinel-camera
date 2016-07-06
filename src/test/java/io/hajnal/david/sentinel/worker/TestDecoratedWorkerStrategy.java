package io.hajnal.david.sentinel.worker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.hajnal.david.sentinel.config.WorkerStrategyConfig;
import io.hajnal.david.sentinel.worker.strategy.AbstractWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.DecoratedWorkerStrategy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WorkerStrategyConfig.class)
public class TestDecoratedWorkerStrategy {

	@Autowired
	private DecoratedWorkerStrategy underTest;

	@Test
	public void strategyExecuteIsCalledWhenOMultipleStrategyAdded() {
		// Given
		AbstractWorkerStrategy additionalStrategy = Mockito.mock(AbstractWorkerStrategy.class);
		underTest.addStrategy(additionalStrategy);
		Mockito.doNothing().when(additionalStrategy).execute(null);
		
		AbstractWorkerStrategy otherStrategy = Mockito.mock(AbstractWorkerStrategy.class);
		underTest.addStrategy(otherStrategy);
		Mockito.doNothing().when(otherStrategy).execute(null);
		// When
		underTest.execute(null);
		// Then
		verify(additionalStrategy, times(1)).execute(null);
		verify(otherStrategy, times(1)).execute(null);
	}

	@Test
	public void strategyExecuteIsCalledWhenOneStrategyAdded() {
		// Given
		AbstractWorkerStrategy additionalStrategy = Mockito.mock(AbstractWorkerStrategy.class);
		underTest.addStrategy(additionalStrategy);
		Mockito.doNothing().when(additionalStrategy).execute(null);
		// When
		underTest.execute(null);
		// Then
		verify(additionalStrategy, times(1)).execute(null);
	}

}
