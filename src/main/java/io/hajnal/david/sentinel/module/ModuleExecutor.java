package io.hajnal.david.sentinel.module;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Camera;
import io.hajnal.david.sentinel.util.Frame;

public class ModuleExecutor implements ModuleHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleExecutor.class);

	public static final String MODULE_ALREADY_ADDED = "Module already added!";
	private Map<String, SentinelModule> modules;

	private Camera camera;

	public ModuleExecutor(Camera camera) {
		this.modules = new HashMap<>();
		this.camera = camera;
	}

	@Override
	public Void call() throws Exception {
		applyTickEventToModules() ;
		if(hasToTakePicture()) {
			executeModulesWithFrame(camera.getFrame());			
		}
		return null;
	}

	private void executeModulesWithFrame(Frame frame) {
		modules.entrySet()
		.stream()
		.filter(isModuleExecute())
		.forEach(entry -> {
			entry.getValue().execute(frame);
		});
	}
	
	private void applyTickEventToModules(){
		modules.entrySet()
			.stream()
			.forEach(applyTickEventToModule());
	}

	private boolean hasToTakePicture(){
		return modules.entrySet()
			.stream()
			.filter(isModuleExecute())
			.count() > 0;
	}
	
	private Consumer<? super Entry<String, SentinelModule>> applyTickEventToModule() {
		return entry -> entry.getValue().tickEvent();
	}

	private Predicate<? super Entry<String, SentinelModule>> isModuleExecute() {
		return entry -> entry.getValue().isExecute();
			
	}	
	
	@Override
	public void addModule(String moduleId, SentinelModule module) {
		if (modules.containsKey(moduleId)) {
			throw new RuntimeException(MODULE_ALREADY_ADDED);
		}
		modules.put(moduleId, module);
	}

	@Override
	public void removeModule(String moduleId) {
		if (modules.containsKey(moduleId)) {
			modules.remove(moduleId);
		}
	}

	@Override
	public void disableModule(String moduleId) {
		if (modules.containsKey(moduleId)) {
			modules.get(moduleId)
					.setActive(false);
		}
	}

}
