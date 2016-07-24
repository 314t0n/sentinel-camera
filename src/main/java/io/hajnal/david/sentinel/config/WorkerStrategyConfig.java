package io.hajnal.david.sentinel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.util.FileStorage;
import io.hajnal.david.sentinel.util.Storage;
import io.hajnal.david.sentinel.worker.strategy.DecoratedWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.FileStorageWorkerStrategy;

@Configuration
@ComponentScan(basePackageClasses = { 
		DecoratedWorkerStrategy.class, FileStorageWorkerStrategy.class, FileStorage.class })
public class WorkerStrategyConfig {

	 @Bean
	 public Storage fileStorage(){
		 return new FileStorage("/");
	 }
}
