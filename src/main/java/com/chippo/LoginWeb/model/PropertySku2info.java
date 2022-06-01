package com.chippo.LoginWeb.model;

public class PropertySku2info {

	private String idSku2info;
	private String quantity;
	private String priceText;

	public PropertySku2info(String idSku2info, String quantity, String priceText) {
		super();
		this.idSku2info = idSku2info;
		this.quantity = quantity;
		this.priceText = priceText;
	}

	public String getIdSku2info() {
		return idSku2info;
	}

	public void setIdSku2info(String idSku2info) {
		this.idSku2info = idSku2info;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPriceText() {
		return priceText;
	}

	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}

}
