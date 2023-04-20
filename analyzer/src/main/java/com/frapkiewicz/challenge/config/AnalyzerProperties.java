package com.frapkiewicz.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AnalyzerProperties {
	
	@Value("${input.extension}")
	private String extensionIn;

	@Value("${input.path}")
	private String inputPath;
	
	@Value("${output.extension}")
	private String extensionOut;
	
	@Value("${output.path}")
	private String outputPath;
	
	@Value("${base-path.use.homepath}")
	private Boolean useHomePath;
	
	@Value("${base-path.use}")
	private String basePath;

	public String getExtensionIn() {
		return extensionIn;
	}

	public void setExtensionIn(String extensionIn) {
		this.extensionIn = extensionIn;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getExtensionOut() {
		return extensionOut;
	}

	public void setExtensionOut(String extensionOut) {
		this.extensionOut = extensionOut;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public Boolean getUseHomePath() {
		return useHomePath;
	}

	public void setUseHomePath(Boolean useHomePath) {
		this.useHomePath = useHomePath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
