package com.frapkiewicz.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.frapkiewicz.challenge.report.SalesReport;
import com.frapkiewicz.challenge.report.SalesReportImp;
import org.springframework.stereotype.Component;

import com.frapkiewicz.challenge.report.writter.CustomerWritter;
import com.frapkiewicz.challenge.report.writter.ReportUpdater;
import com.frapkiewicz.challenge.report.writter.SaleUpdater;
import com.frapkiewicz.challenge.report.writter.SalesmanUpdater;

@Component
public class Analyzer {
	
	private final List<ReportUpdater> updaterList;
	
	public Analyzer() {
		updaterList = new ArrayList<ReportUpdater>();
		attach(new SalesmanUpdater());
		attach(new CustomerWritter());
		attach(new SaleUpdater());
	}
	
	public void attach(ReportUpdater writter) {
		updaterList.add(writter);		
	}
	
	public void dettach(ReportUpdater writter) {
		updaterList.remove(writter);		
	}
	
	public SalesReport analyze(List<String> rowsOfFile) {
		SalesReport report = new SalesReportImp();
		rowsOfFile.forEach( row -> notifyAllUpdaters(row, report) );
		return report;
	}
	
	public void notifyAllUpdaters(String newData, SalesReport report) {
		updaterList.stream()
			.parallel()
			.forEach( updater -> {
				updater.updateReport(report, newData);
			});
	}
}
