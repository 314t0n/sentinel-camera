package io.hajnal.david.sentinel.settings;

public class SystemConfig {

	private int cameraId;
	private boolean standalone;
	private String deviceId;
	private String host;
	private int port;
	private int fps;

	private ImagelogSettings imagelogSettings;
	private MotionDetectSettings motionDetectSettings;

	public int getCameraId() {
		return cameraId;
	}

	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}

	public boolean isStandalone() {
		return standalone;
	}

	public void setStandalone(boolean standalone) {
		this.standalone = standalone;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public ImagelogSettings getImagelogSettings() {
		return imagelogSettings;
	}

	public void setImagelogSettings(ImagelogSettings imagelogSettings) {
		this.imagelogSettings = imagelogSettings;
	}

	public MotionDetectSettings getMotionDetectSettings() {
		return motionDetectSettings;
	}

	public void setMotionDetectSettings(MotionDetectSettings motionDetectSettings) {
		this.motionDetectSettings = motionDetectSettings;
	}

	
}
