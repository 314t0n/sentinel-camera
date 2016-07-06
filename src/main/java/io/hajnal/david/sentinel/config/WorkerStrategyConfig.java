package io.hajnal.david.sentinel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.worker.ThreadWorker;
import io.hajnal.david.sentinel.worker.strategy.DecoratedWorkerStrategy;

@Configuration
@ComponentScan(basePackageClasses = { DecoratedWorkerStrategy.class })
public class WorkerStrategyConfig {

}
