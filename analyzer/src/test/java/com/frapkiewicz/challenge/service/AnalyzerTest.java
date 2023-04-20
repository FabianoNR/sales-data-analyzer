package com.frapkiewicz.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.frapkiewicz.challenge.report.SalesReport;
import com.frapkiewicz.challenge.report.SalesReportResultImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnalyzerTest {
	
	private Analyzer analyzer;
	
	private List<String> rowsOfFile;
	
	@BeforeEach
	void setUp() throws Exception {
		analyzer = new Analyzer();
		rowsOfFile = new ArrayList<String>();
		rowsOfFile.add("001ç1234567891234çPedroç50000");
		rowsOfFile.add("001ç3245678865434çPauloç40000.99");
		rowsOfFile.add("002ç2345675434544345çJose da SilvaçRural");
		rowsOfFile.add("002ç2345675433444345çEduardo PereiraçRural");
		rowsOfFile.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
		rowsOfFile.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");		
	}

	@AfterEach
	void tearDown() throws Exception {
		rowsOfFile.clear();
	}

	@Test
	void testValidInputFileAnalysis() {				
		SalesReportResultImp results = new SalesReportResultImp();
		results.setNumberOfCustomer(2);
		results.setNumberOfSalesman(2);
		results.setIdMostExpensiveSale(10L);
		results.setWorstSalesman("Paulo");
		
		SalesReport report = analyzer.analyze(rowsOfFile);
		
		assertTrue(report.hasResults());
		assertEquals(results, report.getResults());
	}
	
	@Test
	void testAnalysisOfEmptyInputFile() {
		rowsOfFile.clear();
		
		SalesReport report = analyzer.analyze(rowsOfFile);
		
		assertFalse(report.hasResults());
		assertEquals(new SalesReportResultImp(), report.getResults());
	}
	
	@Test
	void testTotallyInvalidInputFileAnalysis() {
		rowsOfFile.clear();
		rowsOfFile.add("jkjskdj;;;dkjkjsd");
		rowsOfFile.add("----------------");
		rowsOfFile.add("!!sakjsi###poaskoji$as");
		rowsOfFile.add("  dsds sd fdfdff dfd");
				
		SalesReport report = analyzer.analyze(rowsOfFile);
		
		assertFalse(report.hasResults());
		assertEquals(new SalesReportResultImp(), report.getResults());
	}
	
	@Test
	void testAnalysisOfInputFileWithSomeInvalidRow() {
		rowsOfFile.add("jkjskdj;;;dkjkjsd");
		Collections.shuffle(rowsOfFile);	
		
		SalesReportResultImp results = new SalesReportResultImp();
		results.setNumberOfCustomer(2);
		results.setNumberOfSalesman(2);
		results.setIdMostExpensiveSale(10L);
		results.setWorstSalesman("Paulo");
		
		SalesReport report = analyzer.analyze(rowsOfFile);
		
		assertTrue(report.hasResults());
		assertEquals(results, report.getResults());
	}
}
