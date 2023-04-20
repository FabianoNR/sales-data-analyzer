package com.frapkiewicz.challenge.report;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalesReportResultImp implements SalesReportResult {
	
	private Integer numberOfSalesman;
	private Integer numberOfCustomer;
	private Long idMostExpensiveSale;
	private String worstSalesman;
	private final String summary;
	private final String timestamp;

	public SalesReportResultImp() {
		setNumberOfSalesman(0);
		setNumberOfCustomer(0);
		setIdMostExpensiveSale(0L);
		setWorstSalesman("");
		
		timestamp = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
		
		summary = "Quantidade de clientes no arquivo de entrada: {0}\n" +
				"Quantidade de vendedores no arquivo de entrada: {1}\n" +
				"ID da venda mais cara: {2}\n" +
				"O pior vendedor: {3}";
	}
	
	@Override
	public String getSummary() {
		return MessageFormat.format(summary, 
				numberOfSalesman,
				numberOfCustomer,
				idMostExpensiveSale.equals(0L) ? "": idMostExpensiveSale,
				worstSalesman
				);
	}

	@Override
	public String getTimestamp() {
		return timestamp;
	}


	public Integer getNumberOfSalesman() {
		return numberOfSalesman;
	}

	public void setNumberOfSalesman(Integer numberOfSalesman) {
		this.numberOfSalesman = numberOfSalesman;
	}

	public Integer getNumberOfCustomer() {
		return numberOfCustomer;
	}

	public void setNumberOfCustomer(Integer numberOfCustomer) {
		this.numberOfCustomer = numberOfCustomer;
	}

	public Long getIdMostExpensiveSale() {
		return idMostExpensiveSale;
	}

	public void setIdMostExpensiveSale(Long idMostExpensiveSale) {
		this.idMostExpensiveSale = idMostExpensiveSale;
	}

	public String getWorstSalesman() {
		return worstSalesman;
	}

	public void setWorstSalesman(String worstSalesman) {
		this.worstSalesman = worstSalesman;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMostExpensiveSale == null) ? 0 : idMostExpensiveSale.hashCode());
		result = prime * result + ((numberOfCustomer == null) ? 0 : numberOfCustomer.hashCode());
		result = prime * result + ((numberOfSalesman == null) ? 0 : numberOfSalesman.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((worstSalesman == null) ? 0 : worstSalesman.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesReportResultImp other = (SalesReportResultImp) obj;
		if (idMostExpensiveSale == null) {
			if (other.idMostExpensiveSale != null)
				return false;
		} else if (!idMostExpensiveSale.equals(other.idMostExpensiveSale))
			return false;
		if (numberOfCustomer == null) {
			if (other.numberOfCustomer != null)
				return false;
		} else if (!numberOfCustomer.equals(other.numberOfCustomer))
			return false;
		if (numberOfSalesman == null) {
			if (other.numberOfSalesman != null)
				return false;
		} else if (!numberOfSalesman.equals(other.numberOfSalesman))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (worstSalesman == null) {
			return other.worstSalesman == null;
		} else return worstSalesman.equals(other.worstSalesman);
	}
	
}
