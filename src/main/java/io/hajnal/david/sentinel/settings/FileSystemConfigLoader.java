package io.hajnal.david.sentinel.settings;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileSystemConfigLoader implements SystemConfigLoader {

	private static final String CONFIG_FILE_PATH = "src/resources/config.json";

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemConfigLoader.class);

	private ObjectMapper mapper;
	
	public FileSystemConfigLoader() {
		mapper = new ObjectMapper();
	}

	@Override
	public SystemConfig load() {
		try {
			return mapper.readValue(new File(CONFIG_FILE_PATH), SystemConfig.class);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void save(SystemConfig config) {
		try {
			mapper.writeValue(new File(CONFIG_FILE_PATH), config);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
