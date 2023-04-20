package com.frapkiewicz.challenge.model;

public class SaleItem {
	private Long idItem;
	private Integer quantity;
	private Double price;
	
	public SaleItem() {
	}
	
	public SaleItem(Long idItem, Integer quantity, Double price) {
		setIdItem(idItem);
		setQuantity(quantity);
		setPrice(price);
	}
	
	public Double getTotalSalesValue() {
		return quantity * price;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long id) {
		this.idItem = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		SaleItem other = (SaleItem) obj;
		if (idItem == null) {
			if (other.idItem != null)
				return false;
		} else if (!idItem.equals(other.idItem))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			return other.quantity == null;
		} else return quantity.equals(other.quantity);
	}

	@Override
	public String toString() {
		return "SaleItem [idItem=" + idItem + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
}
