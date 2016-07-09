package io.hajnal.david.sentinel.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class FileStorage implements Storage {

	public static final String FILENAME_PREFIX = "log_";
	public static final String TIMESTAMP_FORMAT = "yyyy_MM_dd_HH_mm_ss";
	public static final String FILETYPE = ".jpg";

	private String path;

	public FileStorage(String path) {
		setPath(path);
	}

	public void setPath(String path) {
		this.path = path;
		if (!path.endsWith("/")) {
			this.path += "/";
		}
	}

	@Override
	public void saveImage(Mat frame, LocalDateTime timeStamp) {
		Imgcodecs.imwrite(getFileNameFromTimestamp(timeStamp), frame);		
	}

	public String getFileNameFromTimestamp(LocalDateTime timeStamp) {
		StringBuilder sb = new StringBuilder();
		sb.append(path);
		sb.append(FILENAME_PREFIX);
		sb.append(timeStamp.format(DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT)));
		sb.append(FILETYPE);
		return sb.toString();
	}

	@Override
	public Mat readImage(LocalDateTime timeStamp) {
		return Imgcodecs.imread(getFileNameFromTimestamp(timeStamp));		
		
	}

	@Override
	public Mat readImage(String name) {
		return Imgcodecs.imread(name);		
	}

}
