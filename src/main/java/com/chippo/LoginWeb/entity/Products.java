package com.chippo.LoginWeb.entity;



import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
public class Products {

	@Id
//	@GeneratedValue(strategy =  GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Indexed(unique = false)
	@Field(value = "nameProduct")
	private String nameProduct;
	
	@Indexed(unique = false)
	@Field(value = "price")
	private String price;
	
	@Indexed(unique = false)
	@Field(value = "fileName")
	private List<String> fileName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getnameProduct() {
		return nameProduct;
	}

	public void setnameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
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

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	

}
