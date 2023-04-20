package com.frapkiewicz.challenge.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.frapkiewicz.challenge.report.SalesReportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frapkiewicz.challenge.config.AnalyzerProperties;

@Component
public class Repository {

	private final Path pathDataIn;
	private final Path pathDataOut;
	private final String extensionIn;
	private final String extensionOut;
	
	@Autowired
	public Repository(AnalyzerProperties properties) throws IOException {
		String homePath;
		if( properties.getUseHomePath() )
			homePath = System.getProperty("user.home");
		else
			homePath = properties.getBasePath();
			
		extensionIn = properties.getExtensionIn();	
		extensionOut = properties.getExtensionOut();
		pathDataIn = Paths.get(homePath + properties.getInputPath());
		pathDataOut = Paths.get(homePath + properties.getOutputPath());
		
		if(!Files.exists(pathDataIn))
			Files.createDirectories(pathDataIn);
		
		if(!Files.exists(pathDataOut))
			Files.createDirectories(pathDataOut);
	}
	
	
	public List<String> getSalesData() {
		List<String> files = new ArrayList<String>();
		
		getAllDataFilePath().parallelStream().forEach( cutAndPastInto(files) );
		
		return files;
	}

	public void save(SalesReportResult report){
		Path fullPathDataOut = Paths.get( pathDataOut + "/" + report.getTimestamp() + extensionOut);

		try {

			Files.write(fullPathDataOut, report.getSummary().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> getAllDataFilePath() {
		try(Stream<Path> walk = Files.walk(pathDataIn)){
			 return walk.filter(Files::isRegularFile)
					 .filter(fullFileName -> fullFileName.toString().endsWith(extensionIn))
					 .map(Path::toString)
					 .collect(Collectors.toList());
		}catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
	
	private Consumer<String> cutAndPastInto(List<String> files){
		return filePathText -> {
			try {
				Path filePath = Paths.get(filePathText);
				files.addAll( Files.readAllLines( filePath ) );
				Files.delete(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}
