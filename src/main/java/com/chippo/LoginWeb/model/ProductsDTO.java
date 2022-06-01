package com.chippo.LoginWeb.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductsDTO {

	private int id;
	private String idpro;
	private String nameProduct;
	private String price;
	private MultipartFile multipartFile;
	private String fileName;
	private List<String> links;
	private String shopName;
	private String methodProductString;
	private List<String> methodSellString;
	private List<PropertyProps> propertyProps;
	private List<ProperSkus> properSkus;
	private List<PropertySku2info> propertySku2infos;

	public List<PropertySku2info> getPropertySku2infos() {
		return propertySku2infos;
	}

	public void setPropertySku2infos(List<PropertySku2info> propertySku2infos) {
		this.propertySku2infos = propertySku2infos;
	}

	public List<ProperSkus> getProperSkus() {
		return properSkus;
	}

	public void setProperSkus(List<ProperSkus> properSkus) {
		this.properSkus = properSkus;
	}

	public List<PropertyProps> getPropertyProps() {
		return propertyProps;
	}

	public void setPropertyProps(List<PropertyProps> propertyProps) {
		this.propertyProps = propertyProps;
	}

	public String getIdpro() {
		return idpro;
	}

	public void setIdpro(String idpro) {
		this.idpro = idpro;
	}

	public String getMethodProductString() {
		return methodProductString;
	}

	public void setMethodProductString(String methodProductString) {
		this.methodProductString = methodProductString;
	}

	public List<String> getMethodSellString() {
		return methodSellString;
	}

	public void setMethodSellString(List<String> methodSellString) {
		this.methodSellString = methodSellString;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

}
