package com.frapkiewicz.challenge.parser;

import java.util.ArrayList;
import java.util.List;

import com.frapkiewicz.challenge.model.Sale;
import com.frapkiewicz.challenge.model.SaleItem;

public class SaleParser extends ReportDataParser<Sale> {
	private static final String REGEX_SALES_ID = "003";
	private static final String REGEX_SALE_ID = "\\d{2}";
	private static final String REGEX_DELIMITER = "ç";
	private static final String REGEX_NAME = "[A-Z][a-z]*";
	private static final String REGEX_SALARY = "\\d[0-9]*+(\\.[0-9]{2}+)?";
	private static final String REGEX_ITEM = "\\d[0-9]*\\-\\d[0-9]*\\-"+ REGEX_SALARY;
	private static final String REGEX_LIST_ITEM = "\\["+ REGEX_ITEM +"(,"+ REGEX_ITEM +")*\\]";

	private static final String SALE_REGEX =
			REGEX_SALES_ID + REGEX_DELIMITER +
			REGEX_SALE_ID + REGEX_DELIMITER +
			REGEX_LIST_ITEM + REGEX_DELIMITER +
			REGEX_NAME;

	
	public SaleParser() {
		super(SALE_REGEX);
	}


	@Override
	protected Sale parse(String data) {
		String[] elements = data.split(REGEX_DELIMITER);
		
		Sale sale = new Sale();
		sale.setId(elements[0]);
		sale.setSaleId(Long.valueOf(elements[1]));
		sale.setSaleItems(parseSaleItems(elements[2]));
		sale.setSalesname(elements[3]);
		
		return sale;
	}
	
	private List<SaleItem> parseSaleItems(String reportData) {
		String itemConcated = reportData.substring(1, reportData.length()-1);
		String[] items = itemConcated.split(",");
		
		List<SaleItem> saleItemList = new ArrayList<SaleItem>();
		
		for(String item : items) {
			String[] elements = item.split("-");
			
			SaleItem saleItem = new SaleItem();
			saleItem.setIdItem(Long.valueOf(elements[0]));
			saleItem.setQuantity(Integer.valueOf(elements[1]));
			saleItem.setPrice(Double.valueOf(elements[2]));
			
			saleItemList.add(saleItem);
		}
		
		return saleItemList;
	}

}
