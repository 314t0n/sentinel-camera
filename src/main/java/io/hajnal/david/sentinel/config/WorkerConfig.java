package io.hajnal.david.sentinel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.worker.ThreadWorker;

@Configuration
@ComponentScan(basePackageClasses = { ThreadWorker.class })
public class WorkerConfig {

}
