package io.hajnal.david.sentinel;

import org.opencv.core.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.hajnal.david.sentinel.config.ModuleConfig;
import io.hajnal.david.sentinel.module.Runner;
import io.hajnal.david.sentinel.module.TickEventModuleRunner;

public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOGGER.debug("Sentinel v2.1");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try {

			ApplicationContext context = new AnnotationConfigApplicationContext("io.hajnal.david.sentinel.config");

			Runner runner = context.getBean("moduleRunner", TickEventModuleRunner.class);
			
			runner.start();
			
			LOGGER.debug("Sentinel started");

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} 
	}
}
