package io.hajnal.david.sentinel.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.strategy.AbstractWorkerStrategy;

public class ThreadWorker implements Worker {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadWorker.class);

	private long timeoutMillis;

	private AbstractWorkerStrategy workerStrategy;

	private ExecutorService executor;

	public ThreadWorker(AbstractWorkerStrategy workerStrategy) {
		this.workerStrategy = workerStrategy;
		executor = Executors.newFixedThreadPool(2);
	}

	@Override
	public void execute(Frame frame) {
		try {
			LOGGER.debug("execute" + Thread.currentThread().getName());
			setTimeout(executor);
			executor.submit(() -> {
				workerStrategy.execute(frame);
			});
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void setTimeout(ExecutorService executor) throws InterruptedException {
		if (timeoutMillis > 0) {
			executor.awaitTermination(timeoutMillis, TimeUnit.MILLISECONDS);
		}
	}

	public void setTimeoutMillis(long timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}

}
