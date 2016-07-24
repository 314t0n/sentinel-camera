package io.hajnal.david.sentinel.module;

public interface ModuleRunner extends Runner {
	void addModule(String moduleId, SentinelModule module);
}
