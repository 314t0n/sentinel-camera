package io.hajnal.david.sentinel.worker.strategy;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.util.Frame;

public class DecoratedWorkerStrategy extends AbstractWorkerStrategy {

	private List<AbstractWorkerStrategy> strategies;

	public DecoratedWorkerStrategy() {
		strategies = new ArrayList<>();
	}

	@Override
	public void execute(Frame frame) {
		strategies.forEach(s -> s.execute(frame));
	}

	public void addStrategy(AbstractWorkerStrategy strategy) {
		strategies.add(strategy);
	}

}
