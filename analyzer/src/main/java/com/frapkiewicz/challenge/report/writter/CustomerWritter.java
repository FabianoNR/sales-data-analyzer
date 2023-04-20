package com.frapkiewicz.challenge.report.writter;

import com.frapkiewicz.challenge.parser.CustomerParser;
import com.frapkiewicz.challenge.report.SalesReport;

public class CustomerWritter implements ReportUpdater{

	@Override
	public void updateReport(SalesReport report, String rowData) {
		new CustomerParser().tryParse(rowData)
		.ifPresent(customer -> report.add(customer));
	}
}
