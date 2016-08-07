package io.hajnal.david.sentinel.worker.strategy;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hajnal.david.sentinel.util.Frame;
import io.hajnal.david.sentinel.worker.ThreadWorker;

public class MotionDetectWorkerStrategy extends DecoratedWorkerStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(MotionDetectWorkerStrategy.class);

	private BackgroundSubtractorMOG2 backgroundSubtractorMOG2;
	private Mat generatedMask;

	private int numberOfLearningFrames;
	private int lengthOfHistoryFrames;
	private int threshold;

	private Mat structuringElement;

	public MotionDetectWorkerStrategy() {
		super();
		lengthOfHistoryFrames = 240;
		numberOfLearningFrames = 0;
		threshold = 5000;
		generatedMask = new Mat();
		
		// history – Length of the history.
		// varThreshold – Threshold on the squared Mahalanobis distance between
		// the pixel and the model to decide whether a pixel is well described
		// by the background model. This parameter does not affect the
		// background update.
		// detectShadows – If true, the algorithm will detect shadows and mark
		// them. It decreases the speed a bit, so if you do not need this
		// feature, set the parameter to false.

		backgroundSubtractorMOG2 = Video.createBackgroundSubtractorMOG2(lengthOfHistoryFrames, 20, false);
		
		structuringElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
	}

	@Override
	public void execute(Frame frame) {
		applyMaskToFrame(frame.getFrame());
		if (isLearningFinished()) {
			motionDetect(frame);
		} else {
			increaseLearningFrames();
		}
	}

	private void motionDetect(Frame frame) {
		if (checkThreshold(getFilteredFrame(generatedMask))) {
			super.execute(frame);
		}
	}

	private boolean checkThreshold(Mat frame) {
		Mat grayFrame = new Mat();
		Core.extractChannel(frame, grayFrame, 0);
		return Core.countNonZero(grayFrame) > threshold;
	}

	private Mat getFilteredFrame(Mat sourceFrame) {
		Mat destionationFrame = new Mat(sourceFrame.rows(), sourceFrame.cols(), sourceFrame.type());		
		Imgproc.morphologyEx(sourceFrame, destionationFrame, Imgproc.MORPH_OPEN, structuringElement);
		return destionationFrame;
	}

	private void applyMaskToFrame(Mat frame) {
		backgroundSubtractorMOG2.apply(frame, generatedMask, 1.0 / lengthOfHistoryFrames);
	}

	private void increaseLearningFrames() {
		numberOfLearningFrames++;
	}

	private boolean isLearningFinished() {
		return numberOfLearningFrames == lengthOfHistoryFrames;
	}

}
