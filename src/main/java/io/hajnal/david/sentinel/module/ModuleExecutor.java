package io.hajnal.david.sentinel.module;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Camera;

@Component
public class ModuleExecutor implements ModuleHandler {

	public static final String MODULE_ALREADY_ADDED = "Module already added!";
	private Map<String, SentinelModule> modules;

	private Camera camera;

	@Autowired
	public ModuleExecutor(Camera camera) {
		this.modules = new HashMap<>();
		this.camera = camera;
	}

	@Override
	public Void call() throws Exception {
		modules.entrySet()
				.stream()
				.filter(entry -> entry.getValue().isExecute())
				.forEach(entry -> {
					entry.getValue().execute(camera.getFrame());
				});
		return null;
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
			modules.get(moduleId).setActive(false);
		}
	}

}
