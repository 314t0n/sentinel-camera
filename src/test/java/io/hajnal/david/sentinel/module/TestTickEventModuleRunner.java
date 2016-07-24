package io.hajnal.david.sentinel.module;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import io.hajnal.david.sentinel.config.ModuleConfig;
import io.hajnal.david.sentinel.util.Camera;
import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.Worker;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ModuleConfig.class)
public class TestTickEventModuleRunner {

	private TickEventModuleRunner underTest;

	@Mock
	private ModuleHandler moduleHandler;

	@Mock
	private TickEventEmitter eventEmitter;

	@Before
	public void setUp() {
		underTest = new TickEventModuleRunner(moduleHandler, eventEmitter);
	}

	@After
	public void tearDown() {
		underTest.stop();
	}

	@Test
	public void eventEmitterStartMethodCalledWhenStartCalled() {
		// Given
		// When
		underTest.start();
		// Then
		verify(eventEmitter, times(1)).start();
	}
	
	@Test
	public void eventEmitterStopMethodCalledWhenStopCalled() {
		// Given
		// When
		underTest.stop();
		// Then
		verify(eventEmitter, times(1)).stop();
	}
}
