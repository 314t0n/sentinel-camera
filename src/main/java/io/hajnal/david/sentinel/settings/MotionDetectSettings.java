package io.hajnal.david.sentinel.settings;

public class MotionDetectSettings extends ModuleSettings {

	private int mogHistory;
	private int threshold;

	public int getMogHistory() {
		return mogHistory;
	}

	public void setMogHistory(int mogHistory) {
		this.mogHistory = mogHistory;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

}
