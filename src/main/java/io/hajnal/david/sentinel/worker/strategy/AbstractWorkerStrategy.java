package io.hajnal.david.sentinel.worker.strategy;

import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.Worker;

public abstract class AbstractWorkerStrategy implements Worker {

	public abstract void execute(Frame frame);

}
