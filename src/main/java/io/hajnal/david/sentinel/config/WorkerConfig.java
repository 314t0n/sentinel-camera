package io.hajnal.david.sentinel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.worker.ThreadWorker;
import io.hajnal.david.sentinel.worker.strategy.AbstractWorkerStrategy;

@Configuration
@ComponentScan(basePackageClasses = { ThreadWorker.class })
public class WorkerConfig {
	
	@Bean
	@Autowired
	public ThreadWorker imagelogWorker(AbstractWorkerStrategy imagelogStrategy){
		ThreadWorker worker = new ThreadWorker(imagelogStrategy);
		return worker;
	}
	
	@Bean
	@Autowired
	public ThreadWorker motionDetectWorker(AbstractWorkerStrategy motiondDetectStrategy){
		ThreadWorker worker = new ThreadWorker(motiondDetectStrategy);
		return worker;
	}

}
