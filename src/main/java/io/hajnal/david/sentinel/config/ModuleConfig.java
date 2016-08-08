package io.hajnal.david.sentinel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import io.hajnal.david.sentinel.module.ModuleExecutor;
import io.hajnal.david.sentinel.module.ModuleHandler;
import io.hajnal.david.sentinel.module.TickEventEmitter;
import io.hajnal.david.sentinel.module.TickEventModule;
import io.hajnal.david.sentinel.module.TickEventModuleRunner;
import io.hajnal.david.sentinel.settings.FileSystemConfigLoader;
import io.hajnal.david.sentinel.util.Camera;
import io.hajnal.david.sentinel.worker.Worker;

@Configuration
@ComponentScan(basePackageClasses = { ModuleExecutor.class })
public class ModuleConfig {
	
	@Autowired Camera camera;
	@Autowired TickEventModule imagelogModule;
	@Autowired TickEventModule motionDetectModule;
	@Bean
	@Autowired
	public TickEventModuleRunner moduleRunner(ModuleHandler moduleExecutor, TickEventEmitter eventEmitter) {
		return new TickEventModuleRunner(moduleExecutor, eventEmitter);
	}

	@Bean
	public TickEventEmitter eventEmitter() {
		return new TickEventEmitter(30);
	}

	@Bean
	@Autowired
	public ModuleExecutor moduleExecutor() {
		ModuleExecutor moduleExecutor = new ModuleExecutor(camera);
		imagelogModule.setNumberOfSkippedFrames(30*5); //every five seconds
		motionDetectModule.setNumberOfSkippedFrames(3); // every frame
		moduleExecutor.addModule("imagelog", imagelogModule);
		moduleExecutor.addModule("motionDetect", motionDetectModule);
		return moduleExecutor;
	}

	@Bean
	@Autowired
	public TickEventModule imagelogModule(Worker imagelogWorker) {
		TickEventModule module = new TickEventModule();
		module.setWorker(imagelogWorker);
		return module;
	}
	
	@Bean
	@Autowired
	public TickEventModule motionDetectModule(Worker motionDetectWorker) {
		TickEventModule module = new TickEventModule();
		module.setWorker(motionDetectWorker);
		return module;
	}

//	@Bean
//	@Scope("prototype")
//	public TickEventModule tickEventModule(int numberOfSkippedFrames) {
//		TickEventModule module = new TickEventModule(numberOfSkippedFrames);
//		return module;
//	}

}
