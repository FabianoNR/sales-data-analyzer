package com.frapkiewicz.challenge.report;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.frapkiewicz.challenge.model.Customer;
import com.frapkiewicz.challenge.model.Sale;
import com.frapkiewicz.challenge.model.Salesman;

@Component
public class SalesReportImp implements SalesReport {

	private final List<Salesman> salesmanList;
	private final List<Customer> customerList;
	private final List<Sale> saleList;
	private final SalesReportResultImp salesResult;
	
	
	public SalesReportImp() {
		salesmanList = new ArrayList<Salesman>();
		customerList = new ArrayList<Customer>();
		saleList = new ArrayList<Sale>();
		salesResult = new SalesReportResultImp();
	}
	
	@Override
	public boolean hasResults() {
		return !(salesmanList.isEmpty() && customerList.isEmpty() && saleList.isEmpty());
	}
	
	@Override
	public void add(Salesman salesman) {
		salesmanList.add(salesman);
	}

	@Override
	public void add(Customer customer) {
		customerList.add(customer);
	}

	@Override
	public void add(Sale sale) {
		saleList.add(sale);
	}
	
	@Override
	public SalesReportResult getResults() {
		salesResult.setNumberOfSalesman(salesmanList.size());
		salesResult.setNumberOfCustomer(customerList.size());
		salesResult.setIdMostExpensiveSale(findTheMostExpansiveSaleId());
		salesResult.setWorstSalesman(findSalesmanNameWithLessSalesFrequency());
		
		return salesResult;				
	}
	
	private Long findTheMostExpansiveSaleId() {
		return saleList.stream()
				.sorted(Comparator.comparing(Sale::getSalesValue)
						.reversed())
				.collect(Collectors.toList())
				.stream()
				.map(Sale::getSaleId)
				.findFirst()
				.orElse(0L);
	}
	
	private String findSalesmanNameWithLessSalesFrequency() {
		return saleList.stream()
				.collect(Collectors.groupingBy(Sale::getSalesname , Collectors.counting()))
				.keySet()
				.stream()
				.findFirst()
				.orElse("");
	}

	@Override
	public String toString() {
		return "SalesReportImp [salesmanList=" + salesmanList + ", customerList=" + customerList + ", saleList="
				+ saleList + "]";
	}
}
