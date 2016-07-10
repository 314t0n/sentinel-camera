package io.hajnal.david.sentinel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.communication.Socket.AbstractSocketFactory;
import io.hajnal.david.sentinel.communication.Socket.SocketFactory;

@Configuration
@ComponentScan(basePackages = "io.hajnal.david.sentinel.communication.*")
public class CommunicationConfig {

	@Bean
	public AbstractSocketFactory getSocketFactory() {
		return new SocketFactory();
	}

}
