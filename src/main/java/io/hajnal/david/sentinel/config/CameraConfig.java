package io.hajnal.david.sentinel.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.hajnal.david.sentinel.util.Camera;
import io.hajnal.david.sentinel.util.OpenCVCameraImpl;

@Configuration
@ComponentScan(basePackageClasses = { Camera.class, OpenCVCameraImpl.class })
public class CameraConfig {

	@Bean
	public Camera camera(){
		return new OpenCVCameraImpl();
	}
}
