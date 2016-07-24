package io.hajnal.david.sentinel.module;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.opencv.core.Mat;
import org.springframework.test.context.ContextConfiguration;

import io.hajnal.david.sentinel.config.ModuleConfig;
import io.hajnal.david.sentinel.util.Camera;
import io.hajnal.david.sentinel.util.Frame;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ModuleConfig.class)
public class TestModuleExecutor {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@InjectMocks
	private ModuleExecutor underTest;

	@Mock
	private Camera camera;

	@Test
	public void addedModuleExecuteCalledWhenCallMethodCalled() throws Exception {
		// Given
		SentinelModule module = Mockito.mock(SentinelModule.class);
		Frame frame = Mockito.mock(Frame.class);
		when(module.isExecute()).thenReturn(true);
		when(camera.getFrame()).thenReturn(frame);
		// When
		underTest.addModule("test", module);
		underTest.call();
		// Then
		verify(module, times(1)).execute(frame);
		verify(camera, times(1)).getFrame();
	}

	@Test
	public void addModuleShouldThrowExceptionWhenModuleAlreadyAdded() throws Exception {
		// Given
		SentinelModule module = Mockito.mock(SentinelModule.class);
		exception.expect(RuntimeException.class);
		exception.expectMessage(ModuleExecutor.MODULE_ALREADY_ADDED);
		// When
		underTest.addModule("test", module);
		underTest.addModule("test", module);
		underTest.call();
		// Then
	}

	@Test
	public void removedModuleExecuteShouldNotCalledWhenCallMethodCalled() throws Exception {
		// Given
		SentinelModule module = Mockito.mock(SentinelModule.class);
		// When
		underTest.addModule("test", module);
		underTest.removeModule("test");
		underTest.call();
		// Then
		verify(module, times(0)).execute(null);
	}

	@Test
	public void disabledModuleExecuteShouldNotCalledWhenCallMethodCalled() throws Exception {
		// Given
		SentinelModule module = Mockito.mock(SentinelModule.class);
		// When
		underTest.addModule("test", module);
		underTest.disableModule("test");
		underTest.call();
		// Then
		verify(module, times(0)).execute(null);
		verify(camera, times(0)).getFrame();
	}
	

	@Test
	public void disabledModuleExecuteShouldNotCalledWhenCallMethodCalledAndMultipleModulesAdded() throws Exception {
		// Given
		SentinelModule module1 = Mockito.mock(SentinelModule.class);
		SentinelModule module2 = Mockito.mock(SentinelModule.class);
		Frame frame = Mockito.mock(Frame.class);
		when(module1.isExecute()).thenReturn(false);
		when(module2.isExecute()).thenReturn(true);
		when(camera.getFrame()).thenReturn(frame);
		// When
		underTest.addModule("test1", module1);
		underTest.addModule("test2", module2);
		underTest.disableModule("test1");
		underTest.call();
		// Then
		verify(module1, times(0)).execute(null);
		verify(module2, times(1)).execute(frame);
		verify(camera, times(1)).getFrame();
	}

	@Test
	public void addedMultipleModulesExecuteCalledWhenCallMethodCalled() throws Exception {
		// Given
		SentinelModule module1 = Mockito.mock(SentinelModule.class);
		SentinelModule module2 = Mockito.mock(SentinelModule.class);
		SentinelModule module3 = Mockito.mock(SentinelModule.class);
		when(module1.isExecute()).thenReturn(true);
		when(module2.isExecute()).thenReturn(true);
		when(module3.isExecute()).thenReturn(true);
		// When
		underTest.addModule("test1", module1);
		underTest.addModule("test2", module2);
		underTest.addModule("test3", module3);
		underTest.call();
		// Then
		verify(module1, times(1)).execute(null);
		verify(module2, times(1)).execute(null);
		verify(module3, times(1)).execute(null);
	}
}
