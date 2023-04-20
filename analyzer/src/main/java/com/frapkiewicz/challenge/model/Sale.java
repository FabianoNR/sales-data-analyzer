package com.frapkiewicz.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Sale implements ReportData{
	private String id;
	private Long saleId;
	private List<SaleItem> saleItems;
	private String salesname;
	
	public Sale() {
		saleItems = new ArrayList<SaleItem>();	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public List<SaleItem> getSaleItems() {
		return saleItems;
	}

	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}

	public String getSalesname() {
		return salesname;
	}

	public void setSalesname(String salesname) {
		this.salesname = salesname;
	}
	
	public Double getSalesValue() {
		return saleItems.stream()
				.map(SaleItem::getTotalSalesValue)
				.reduce(0.0, Double::sum); 
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", saleId=" + saleId + ", saleItems=" + saleItems + ", salesname=" + salesname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((saleId == null) ? 0 : saleId.hashCode());
		result = prime * result + ((saleItems == null) ? 0 : saleItems.hashCode());
		result = prime * result + ((salesname == null) ? 0 : salesname.hashCode());
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
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saleId == null) {
			if (other.saleId != null)
				return false;
		} else if (!saleId.equals(other.saleId))
			return false;
		if (saleItems == null) {
			if (other.saleItems != null)
				return false;
		} else if (!saleItems.equals(other.saleItems))
			return false;
		if (salesname == null) {
			return other.salesname == null;
		} else return salesname.equals(other.salesname);
	}
}
