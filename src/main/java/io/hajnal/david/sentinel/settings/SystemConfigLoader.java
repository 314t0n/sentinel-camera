package io.hajnal.david.sentinel.settings;

public interface SystemConfigLoader {

	SystemConfig load();
	void save(SystemConfig config);
	
}
