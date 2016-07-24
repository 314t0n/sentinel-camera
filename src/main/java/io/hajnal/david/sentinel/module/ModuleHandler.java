package io.hajnal.david.sentinel.module;

public interface ModuleHandler {
	void addModule(String moduleId, SentinelModule module);
	void disableModule(String moduleId);
	void removeModule(String moduleId);
}
