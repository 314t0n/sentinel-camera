package io.hajnal.david.sentinel.module;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.Worker;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class TestTickEventModuleRunner {
	
	private TickEventModuleRunner underTest; 

	@Before
	public void setup() {
		underTest = new TickEventModuleRunner();
	}
	
	@After
	public void tearDown(){
		underTest.stop();
	}
	
	@Test
	public void startShouldCallModuleExecuteMethodWhenOneModuleAdded(){
		// Given
		SentinelModule module = Mockito.mock(SentinelModule.class);
		// When
		underTest.addModule("test", module);
		underTest.start();
		// Then
		verify(module, atLeast(1)).execute(null);
	}
	
	@Test
	public void startShouldCallModuleExecuteMethodWhenMultipleModuleAdded(){
		// Given
		SentinelModule module1 = Mockito.mock(SentinelModule.class);
		SentinelModule module2 = Mockito.mock(SentinelModule.class);
		SentinelModule module3 = Mockito.mock(SentinelModule.class);
		// When
		underTest.addModule("test1", module1);
		underTest.addModule("test2", module2);
		underTest.addModule("test3", module3);
		underTest.start();
		// Then
		verify(module1, atLeast(1)).execute(null);
		verify(module2, atLeast(1)).execute(null);
		verify(module3, atLeast(1)).execute(null);
	}
}
