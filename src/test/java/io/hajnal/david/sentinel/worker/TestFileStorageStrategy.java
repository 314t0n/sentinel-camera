package io.hajnal.david.sentinel.worker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import io.hajnal.david.sentinel.config.WorkerStrategyConfig;
import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.util.Storage;
import io.hajnal.david.sentinel.worker.strategy.FileStorageWorkerStrategy;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = WorkerStrategyConfig.class)
public class TestFileStorageStrategy {

	@Mock
	private FileStorageWorkerStrategy underTest;

	@Mock
	private Storage storage;
	
	@Before
	public void setUp(){
		underTest = new FileStorageWorkerStrategy(storage);
	}

	@Test
	public void saveImageCalledWhenExcuteIsCalled() {
		// Given
		Frame frame = Mockito.mock(Frame.class);
		LocalDateTime timestamp = LocalDateTime.of(2016, 7, 6, 10, 10, 10, 10);
		when(frame.getFrame()).thenReturn(null);
		when(frame.getTimestamp()).thenReturn(timestamp);
		// When
		underTest.execute(frame);
		// Then
		verify(storage, times(1)).saveImage(null, timestamp);
	}
	
	@Test(expected = NullPointerException.class)
	public void saveImageShouldThrowNullPointerExceptionWhenFrameIsNull() {
		// Given
		// When
		underTest.execute(null);
		// Then
	}
}
