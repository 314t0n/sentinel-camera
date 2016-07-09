package io.hajnal.david.sentinel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.util.Storage;
import io.hajnal.david.sentinel.worker.ThreadWorker;
import io.hajnal.david.sentinel.worker.strategy.DecoratedWorkerStrategy;
import io.hajnal.david.sentinel.worker.strategy.FileStorageWorkerStrategy;

@Configuration
@ComponentScan(basePackageClasses = { 
		DecoratedWorkerStrategy.class, FileStorageWorkerStrategy.class, Storage.class })
public class WorkerStrategyConfig {

}
