package io.hajnal.david.sentinel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.module.ModuleExecutor;
import io.hajnal.david.sentinel.worker.ThreadWorker;

@Configuration
@ComponentScan(basePackageClasses = { ModuleExecutor.class })
public class ModuleConfig {

}
