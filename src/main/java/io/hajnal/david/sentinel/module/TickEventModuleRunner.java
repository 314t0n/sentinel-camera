package io.hajnal.david.sentinel.module;

import java.util.HashMap;
import java.util.Map;

public class TickEventModuleRunner implements ModuleRunner {

	private Map<String, SentinelModule> modules;

	public TickEventModuleRunner() {
		this.modules = new HashMap<>();
	}

	@Override
	public void start() {
		modules.forEach((id, module) -> {
			module.execute(null);
		});
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addModule(String moduleId, SentinelModule module) {
		modules.put(moduleId, module);
	}

}
