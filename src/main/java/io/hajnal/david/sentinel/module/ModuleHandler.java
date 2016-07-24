package io.hajnal.david.sentinel.module;

import java.util.concurrent.Callable;

public interface ModuleHandler extends Callable<Void> {
	void addModule(String moduleId, SentinelModule module);
	void disableModule(String moduleId);
	void removeModule(String moduleId);
}
