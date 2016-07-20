package io.hajnal.david.sentinel.worker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import io.hajnal.david.sentinel.communication.ServerConnection;
import io.hajnal.david.sentinel.communication.message.AbstractMessageFactory;
import io.hajnal.david.sentinel.communication.message.Message;
import io.hajnal.david.sentinel.config.WorkerStrategyConfig;
import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.strategy.NotifyServerWorkerStrategy;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = WorkerStrategyConfig.class)
public class TestNotifyServerStrategy {
	
	@InjectMocks
	private NotifyServerWorkerStrategy<Message<String>, String> underTest;

	@Mock
	private ServerConnection connection;
	
	@Mock
	private AbstractMessageFactory<Message<String>, String> messageFactory;

	@Test
	public void createMessageCalledWhenExcuteIsCalled() {
		// Given
		@SuppressWarnings({ "unchecked" })
		Message<String> message = (Message<String>) Mockito.mock(Message.class);
		Frame frame = Mockito.mock(Frame.class);
		when(messageFactory.createMessage(frame)).thenReturn(message);
		// When
		underTest.execute(frame);
		// Then
		verify(messageFactory, times(1)).createMessage(frame);
		verify(connection, times(1)).sendMessage(message);
	}
	
	@Test
	public void saveImageCalledWhenExcuteIsCalled() {
		// Given
		@SuppressWarnings("unchecked")
		Frame frame = Mockito.mock(Frame.class);
		LocalDateTime timestamp = LocalDateTime.of(2016, 7, 6, 10, 10, 10, 10);
		when(frame.getFrame()).thenReturn(null);
		when(frame.getTimestamp()).thenReturn(timestamp);
		// When
		underTest.execute(frame);
		// Then
		verify(connection, times(1)).sendMessage(null);
	}

	@Test(expected = NullPointerException.class)
	public void saveImageShouldThrowNullPointerExceptionWhenFrameIsNull() {
		// Given
		// When
		underTest.execute(null);
		// Then
	}
}
