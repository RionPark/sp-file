package com.sp.file.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Value("${download.file-path}")
	private String filePath;
	@Value("${download.resource-url}")
	private String resourceUrl;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// http://localhost/file/1.png
		registry.addResourceHandler(resourceUrl) // /file/** 
		.addResourceLocations(filePath); // c:/dev/upload/1.png
	}
}

