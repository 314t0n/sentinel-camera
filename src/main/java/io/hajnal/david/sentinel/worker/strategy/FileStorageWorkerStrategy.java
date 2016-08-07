package io.hajnal.david.sentinel.worker.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.util.Storage;

@Component
public class FileStorageWorkerStrategy extends AbstractWorkerStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageWorkerStrategy.class);

	private Storage storage;

	@Autowired
	public FileStorageWorkerStrategy(Storage storage) {
		this.storage = storage;
	}

	@Override
	public void execute(Frame frame) {
//		try {
			storage.saveImage(frame.getFrame(), frame.getTimestamp());
//		} catch (Exception ex) {
//			LOGGER.error(ex.getMessage(), ex);
//			throw new RuntimeException(ex.getMessage());
//		}
	}

}
