package com.chippo.LoginWeb.model;

public class ProperSkus {
	private String skuId;
	private String propPath;

	public ProperSkus(String skuId, String propPath) {
		super();
		this.skuId = skuId;
		this.propPath = propPath;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getPropPath() {
		return propPath;
	}

	public void setPropPath(String propPath) {
		this.propPath = propPath;
	}

}
