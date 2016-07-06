package io.hajnal.david.sentinel.worker.strategy;

import org.opencv.core.Mat;
import org.springframework.stereotype.Component;

import io.hajnal.david.sentinel.worker.Worker;

@Component
public abstract class AbstractWorkerStrategy implements Worker {

	public abstract void execute(Mat frame);

}
