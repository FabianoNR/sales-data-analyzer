package com.frapkiewicz.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.frapkiewicz.challenge.report.SalesReportResultImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import com.frapkiewicz.challenge.config.AnalyzerProperties;

class RepositoryTest {

	private AnalyzerProperties properties;
	
	@BeforeEach
	void setUp() throws Exception {
		properties = new AnalyzerProperties();
		properties.setExtensionIn(".dat");
		properties.setExtensionOut(".done.dat");
		properties.setInputPath("/data/in");
		properties.setOutputPath("/data/out");
		properties.setUseHomePath(false);
		properties.setBasePath(Paths.get("src","test","resources").toString());
	}

	@AfterEach
	void tearDown() throws Exception {
		properties = null;
	}

	@Test
	void testDirectoryCreationWhenItDoesntExist() throws IOException {
		Path path = Paths.get(properties.getBasePath() + "/data");
		
		FileSystemUtils.deleteRecursively(path);
		
		assertFalse(Files.exists(path));
		
		new Repository(properties);
		
		assertTrue(Files.exists(path));
	}
	
	@Test
	void testGetSalesDataFromInputFile() throws IOException {
		String pathTestFiles = properties.getBasePath() + "/test-files";
		String pathInputFiles = properties.getBasePath() + properties.getInputPath();

		List<String> contentOfFiles = Arrays.asList(
				"001ç1234567891234çPedroç50000", 
				"001ç3245678865434çPauloç40000.99", 
				"002ç2345675434544345çJose da SilvaçRural", 
				"002ç2345675433444345çEduardo PereiraçRural",
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", 
				"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
		
		Repository repository = new Repository(properties);
		
		FileSystemUtils.copyRecursively( 
				new File(pathTestFiles), 
				new File(pathInputFiles));

		assertEquals(repository.getSalesData(), contentOfFiles);
	}
	
	@Test
	void testWhenThereAreNoInputFiles() throws IOException {
		Repository repository = new Repository(properties);
		
		List<String> salesData = repository.getSalesData();
		
		assertTrue(salesData.isEmpty());
	}
	
	void testSaveReportResult() throws IOException {		
		SalesReportResultImp reportResult = new SalesReportResultImp();
		reportResult.setIdMostExpensiveSale(10L);
		reportResult.setNumberOfCustomer(3);
		reportResult.setNumberOfSalesman(2);
		reportResult.setWorstSalesman("fabiano");
		
		String pathBase = properties.getBasePath() + properties.getOutputPath();
		
		Path path = Paths.get(pathBase);
		FileSystemUtils.deleteRecursively(path);
		
		assertFalse(Files.exists(path));
		
		Repository repository = new Repository(properties);
		repository.save(reportResult);
		
		path = Paths.get(pathBase + "/" + reportResult.getTimestamp());
		assertTrue(Files.exists(path));
	}

}
