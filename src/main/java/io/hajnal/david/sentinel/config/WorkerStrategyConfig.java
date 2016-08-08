package io.hajnal.david.sentinel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.util.FileStorage;
import io.hajnal.david.sentinel.util.Storage;
import io.hajnal.david.sentinel.worker.strategy.AbstractWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.DecoratedWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.FileStorageWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.MotionDetectWorkerStrategy;

@Configuration
@ComponentScan(basePackageClasses = { DecoratedWorkerStrategy.class, FileStorageWorkerStrategy.class })
public class WorkerStrategyConfig {

	@Bean	
	public AbstractWorkerStrategy motiondDetectStrategy() {
		MotionDetectWorkerStrategy motiondDetectStrategy = new MotionDetectWorkerStrategy();
		motiondDetectStrategy.addStrategy(motionDetectFileWorkerStrategy());
		// todo notifyserver strategy
		return motiondDetectStrategy;
	}

	@Bean
	public AbstractWorkerStrategy imagelogStrategy() {
		DecoratedWorkerStrategy imagelogStrategy = new DecoratedWorkerStrategy();
		imagelogStrategy.addStrategy(imagelogFileWorkerStrategy());
		// todo notifyserver strategy
		return imagelogStrategy;
	}
	
	@Bean
	public AbstractWorkerStrategy motionDetectFileWorkerStrategy() {
		return new FileStorageWorkerStrategy(motionDetectFileStorage());
	}
	
	@Bean
	public AbstractWorkerStrategy imagelogFileWorkerStrategy() {
		return new FileStorageWorkerStrategy(imagelogFileStorage());
	}

	@Bean
	public Storage motionDetectFileStorage() {
		return new FileStorage("D://log/", "md");
	}
	
	@Bean
	public Storage imagelogFileStorage() {
		return new FileStorage("D://log/", "log");
	}
}
