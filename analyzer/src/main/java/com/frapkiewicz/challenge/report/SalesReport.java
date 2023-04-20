package com.frapkiewicz.challenge.report;

import com.frapkiewicz.challenge.model.Customer;
import com.frapkiewicz.challenge.model.Sale;
import com.frapkiewicz.challenge.model.Salesman;

public interface SalesReport {
	void add(Salesman data);
	void add(Customer data);
	void add(Sale data);
	SalesReportResult getResults();
	boolean hasResults();
}
