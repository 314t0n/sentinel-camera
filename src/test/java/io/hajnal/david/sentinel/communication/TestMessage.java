package io.hajnal.david.sentinel.communication;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.hajnal.david.sentinel.communication.message.Message;
import io.hajnal.david.sentinel.config.WorkerConfig;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = WorkerConfig.class)
public class TestMessage {
	private static final String MESSAGE = "{\"id\":\"123\",\"payload\":\"ooiiuu\"}";
	
	@Test
	public void formatShouldReturnCorrectJSONStringWhenPayloadIsString() throws JsonProcessingException {
		// Given
		Message<String> message = new Message<>("123", "ooiiuu");
		// When
		String result = message.format();
		// Then
		Assert.assertEquals(MESSAGE, result);
	}
	
	@Test
	public void ofShouldReturnMessageWhenInputIsSimpleJSON() throws IOException {
		// Given
		Message<String> message = new Message<>("123", "ooiiuu");
		// When
		Message<String> result = Message.of(MESSAGE);
		// Then
		Assert.assertEquals(message, result);
	}
}
