package com.frapkiewicz.challenge.parser;

import com.frapkiewicz.challenge.model.Salesman;

public class SalesmanParser extends ReportDataParser<Salesman> {

	private static final String REGEX_SALESMAN_ID = "001";
	private static final String REGEX_CPF = "\\d{13}";
	private static final String REGEX_NAME = "[A-Z][a-z]*";
	private static final String REGEX_SALARY = "\\d[0-9]*+(\\.[0-9]{2}+)?";
	
	private static final String SALESMAN_REGEX = 
			REGEX_SALESMAN_ID + 
			REGEX_DELIMITER + 
			REGEX_CPF + REGEX_DELIMITER + 
			REGEX_NAME + REGEX_DELIMITER + 
			REGEX_SALARY;
	
	public SalesmanParser() {
		super(SALESMAN_REGEX);
	}
	
	@Override
	protected Salesman parse(String value) {
		String[] elements = value.split(REGEX_DELIMITER);
		
		Salesman salesman = new Salesman();
		salesman.setId(elements[0]);
		salesman.setCpf(elements[1]);
		salesman.setName(elements[2]);
		salesman.setSalary(Double.valueOf(elements[3]));
		
		return salesman;
	}
}
