package io.hajnal.david.sentinel.settings;

public class ModuleSettings {

	private boolean status;
	private boolean storeImageOnDevice;
	private int storeOnDeviceInDays;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isStoreImageOnDevice() {
		return storeImageOnDevice;
	}

	public void setStoreImageOnDevice(boolean storeImageOnDevice) {
		this.storeImageOnDevice = storeImageOnDevice;
	}

	public int getStoreOnDeviceInDays() {
		return storeOnDeviceInDays;
	}

	public void setStoreOnDeviceInDays(int storeOnDeviceInDays) {
		this.storeOnDeviceInDays = storeOnDeviceInDays;
	}

}
