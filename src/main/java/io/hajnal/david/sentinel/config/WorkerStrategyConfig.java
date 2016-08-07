package io.hajnal.david.sentinel.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.hajnal.david.sentinel.util.FileStorage;
import io.hajnal.david.sentinel.util.Storage;
import io.hajnal.david.sentinel.worker.strategy.AbstractWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.DecoratedWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.FileStorageWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.MotionDetectWorkerStrategy;

@Configuration
@ComponentScan(basePackageClasses = { DecoratedWorkerStrategy.class, FileStorageWorkerStrategy.class, FileStorage.class })
public class WorkerStrategyConfig {

	@Bean
	@Qualifier("motiondDetectStrategy")
	@Primary
	public AbstractWorkerStrategy motiondDetectStrategy() {
		MotionDetectWorkerStrategy motiondDetectStrategy = new MotionDetectWorkerStrategy();
		motiondDetectStrategy.addStrategy(new FileStorageWorkerStrategy(fileStorage()));
		// todo notifyserver strategy
		return motiondDetectStrategy;
	}

	@Bean
	@Qualifier("imagelogStrategy")

	public AbstractWorkerStrategy imagelogStrategy() {
		DecoratedWorkerStrategy imagelogStrategy = new DecoratedWorkerStrategy();
		imagelogStrategy.addStrategy(new FileStorageWorkerStrategy(fileStorage()));
		// todo notifyserver strategy
		return imagelogStrategy;
	}

	@Bean
	public Storage fileStorage() {
		return new FileStorage("D://log/");
	}
}
