package com.frapkiewicz.challenge.report.writter;

import com.frapkiewicz.challenge.report.SalesReport;

public interface ReportUpdater {
	
	void updateReport(SalesReport report, String rowData);
}
